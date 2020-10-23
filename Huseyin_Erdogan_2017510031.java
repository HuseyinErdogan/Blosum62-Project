import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Huseyin_Erdogan_2017510031 {
	private static 	int[][] Blosum62 = {
			{ 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0},
			{-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3},
			{-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3},
			{-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3},
			{ 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1},
			{-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2},
			{-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2},
			{ 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3},
			{-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3},
			{-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3},
			{-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1},
			{-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2},
			{-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1},
			{-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1},
			{-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2},
			{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2},
			{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0},
			{-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3},
			{-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1},
			{ 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4}};
           
	
 	public static void main(String[] args) throws IOException{
	              


	     
 	int gapPenalty = -5;

 	File file = new File("input.txt"); 
 	  
    BufferedReader br = new BufferedReader(new FileReader(file)); 
 	  	
 	String x = br.readLine();
 	String y = br.readLine();
 	
 	char[] xArray = x.toCharArray();
 	char[] yArray = y.toCharArray();
 	
 	int[][] mainMatrix = new int[y.length()+1][x.length()+1];


 	String[][] tempXResult = new String[y.length()+1][x.length()+1];
 	String[][] tempYResult = new String[y.length()+1][x.length()+1];
 	for (int i = 0; i < y.length()+1; i++) {
		for (int j = 0; j < x.length()+1; j++) {
			tempYResult[i][j]="";
			tempXResult[i][j]="";
		}
	}
 	int tempPen=0;
 	for (int i = 0; i < x.length()+1; i++) {
 		mainMatrix[0][i]=tempPen;
 		if (i!=0) {
 			tempYResult[0][i]=tempYResult[0][i-1]+"-";
 			tempXResult[0][i]=tempXResult[0][i-1]+xArray[i-1];
		}
 		tempPen+=gapPenalty;
	}
 	tempPen=0;
 	for (int i = 0; i < y.length()+1; i++) {
 		mainMatrix[i][0]=tempPen;
 		if (i!=0) {
 			tempXResult[i][0]=tempXResult[i-1][0]+"-";
 			tempYResult[i][0]=tempYResult[i-1][0]+yArray[i-1];
		}
 		tempPen+=gapPenalty;
	}
 	
 	
 	for (int i = 1; i < y.length()+1; i++) {
 		int tempDiag=0, tempLeft=0, tempUp=0;
		for (int j = 1; j < x.length()+1; j++) {
			tempDiag= mainMatrix[i-1][j-1] + getDistance(xArray[j-1],yArray[i-1]);
			tempLeft= mainMatrix[i][j-1] + gapPenalty;
			tempUp= mainMatrix[i-1][j] + gapPenalty;
			
			if (tempDiag >= tempLeft && tempDiag >= tempUp) {
				mainMatrix[i][j]=tempDiag;
				tempYResult[i][j]=tempYResult[i-1][j-1]+yArray[i-1];
				tempXResult[i][j]=tempXResult[i-1][j-1]+xArray[j-1];
			}
			else if (tempLeft > tempDiag && tempLeft >= tempUp) {
				mainMatrix[i][j]=tempLeft;
				tempYResult[i][j]=tempYResult[i][j-1]+"-";
				tempXResult[i][j]=tempXResult[i][j-1]+xArray[j-1];
			}
			else if (tempUp > tempDiag && tempUp > tempLeft) {
				mainMatrix[i][j]=tempUp;
				tempXResult[i][j]=tempXResult[i-1][j]+"-";
				tempYResult[i][j]=tempYResult[i-1][j]+yArray[i-1];
			}

		}
	}

 	System.out.println("Alignment Score= "+mainMatrix[y.length()][x.length()]);
 	System.out.println("Best Alignment: ");
 	System.out.println(tempXResult[y.length()][x.length()]);
 	System.out.println(tempYResult[y.length()][x.length()]);
 	
 	
	
	 }
	 private static int getIndex(char a) {

	    	switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
		    	case 'A': return 0;
		    	case 'R': return 1;
		    	case 'N': return 2;
		    	case 'D': return 3;
		    	case 'C': return 4;
		    	case 'Q': return 5;
		    	case 'E': return 6;
		    	case 'G': return 7;
		    	case 'H': return 8;
		    	case 'I': return 9;
		    	case 'L': return 10;
		    	case 'K': return 11;
		    	case 'M': return 12;
		    	case 'F': return 13;
		    	case 'P': return 14;
		    	case 'S': return 15;
		    	case 'T': return 16;
		    	case 'W': return 17;
		    	case 'Y': return 18;
		    	case 'V': return 19;
		    	default: return 0;
	    	}
	    }
	    
	    private static int getDistance(int i, int j) {
	    	return Blosum62[i][j];
	    }

	    public static int getDistance(char a1, char a2) {
	    	return getDistance(getIndex(a1), getIndex(a2));  	
	    }

}

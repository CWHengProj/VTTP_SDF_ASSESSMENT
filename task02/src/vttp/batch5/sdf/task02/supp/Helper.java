package vttp.batch5.sdf.task02.supp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public int turnCount=0;
    public char[][] getGameGrid(String fileDir) throws IOException {
		System.out.println("Board:");
		//read the text files
		//make the data usable
		File file = new File(fileDir);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		//print out the TTT board
		String currLine ="";
        char[][] gameGrid = new char[3][3];
        for (int row =0; row<3; row++){
            currLine=br.readLine();
            char[] c =currLine.toCharArray();
            for (int col=0;col<3;col++){
                System.out.print(c[col]);
                gameGrid[row][col]= c[col];
            }
            System.out.println();
        }

		br.close();
		System.out.println("--------------------------------\n");
        return gameGrid;
    }
    public int scoreChecker(char[][] gameGrid) {
        //check if player won
        for (int i=0; i<3; i++){
            if (gameGrid[i][0]==gameGrid[i][1] &&gameGrid[i][1]==gameGrid[i][2]){
                if(gameGrid[i][0]== 'X'){
                    return 1;
                }
            }
        }
        for (int j=0; j<3; j++){
            if (gameGrid[0][j]==gameGrid[1][j] &&gameGrid[1][j]==gameGrid[2][j]){
                if(gameGrid[0][j]== 'X'){
                    return 1;
            } }
        }
        if (gameGrid[0][0]==gameGrid[1][1] &&gameGrid[1][1]==gameGrid[2][2]){
            if(gameGrid[0][0]== 'X'){
                return 1;   
            }
        }
        if (gameGrid[0][2]==gameGrid[1][1] &&gameGrid[1][1]==gameGrid[2][0]){
            if(gameGrid[0][2]== 'X'){
                return 1;         
            }
        } 
        //cases where O wins in a move turn
        char[][] tempGrid = gameGrid;

        //check through all the win conditions again and return 0
        for (int l=0; l<3; l++){
            int oCount =0;
            if (tempGrid[l][0]=='O'){
                oCount++;
            }
            if (tempGrid[l][1]=='O'){
                oCount++;
            }
            if (tempGrid[l][2]=='O'){
                oCount++;
            }
            if (oCount>1){
                return -1;
            }
        }
        for (int k=0; k<3; k++){
            int oCount=0;
            if (tempGrid[0][k]=='O'){
                oCount++;
            }
            if (tempGrid[1][k]=='O'){
                oCount++;
            }
            if (tempGrid[2][k]=='O'){
                oCount++;
            }
            if (oCount>1){
                return -1;
            }

        }
        int dCount=0;
        if (tempGrid[0][0]=='O'){
            dCount++;
        }
        if (tempGrid[1][1]=='O'){
            dCount++;
        }
        if (tempGrid[2][2]=='O'){
            dCount++;
        }
        if (dCount>1){
            return -1;
        }
        dCount=0;
        if (tempGrid[0][2]=='O'){
            dCount++;
        }
        if (tempGrid[1][1]=='O'){
            dCount++;
        }
        if (tempGrid[2][0]=='O'){
            dCount++;
        }
        if (dCount>1){
            return -1;
        }
        return 0;

    } 
    public void simulateMoves(char[][] gameGrid) {
        char[][] simGrid = gameGrid;
        //for each empty cell, try and check the win state.
        //TODO add each state to a list where it can be returned
        for (int i=0;i<3;i++){
            for (int j=0; j<3; j++){
                if(simGrid[i][j]=='.'){
                    simGrid[i][j]= 'X';
                    //simulate each position and map to a list the returned score
                    System.out.printf("y=%d, ",j);
                    System.out.printf("x=%d, ",i);
                    int returnedValue = scoreChecker(simGrid);
                    System.out.printf("utility=%d\n",returnedValue);
                    simGrid[i][j]='.';//undo board state
                }
            }
        }
    }
    
}

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
        //check through all the win conditions again and return 0
        //horizontal, vertical, diagonal checks if the current state contains more than 2 Os and a space
        int dotCount =0;
        int oCount =0;
        for (int hor=0;hor<3;hor++){
            dotCount=0;
            oCount=0;
            if(gameGrid[hor][0]=='.'){
                dotCount++;
            }
            if(gameGrid[hor][1]=='.'){
                dotCount++;
            }
            if(gameGrid[hor][2]=='.'){
                dotCount++;
            }
            if(gameGrid[hor][0]=='O'){
                oCount++;
            }
            if(gameGrid[hor][1]=='O'){
                oCount++;
            }
            if(gameGrid[hor][2]=='O'){
                oCount++;
            }
            if ((oCount==2)&&(dotCount==1)){
                return -1;
            }
        }
        dotCount=0;
        oCount=0;
        for (int ver=0;ver<3;ver++){
            dotCount=0;
            oCount=0;
            if(gameGrid[0][ver]=='.'){
                dotCount++;
            }
            if(gameGrid[1][ver]=='.'){
                dotCount++;
            }
            if(gameGrid[2][ver]=='.'){
                dotCount++;
            }
            if(gameGrid[0][ver]=='O'){
                oCount++;
            }
            if(gameGrid[1][ver]=='O'){
                oCount++;
            }
            if(gameGrid[2][ver]=='O'){
                oCount++;
            }
            if ((oCount==2)&&(dotCount==1)){
                return -1;
            }
        }
        dotCount=0;
        oCount=0;
        if (gameGrid[0][0]=='.'){
            dotCount++;
        }
        if (gameGrid[1][1]=='.'){
            dotCount++;
        }
        if (gameGrid[2][2]=='.'){
            dotCount++;
        }
        if (gameGrid[0][0]=='O'){
            oCount++;
        }
        if (gameGrid[1][1]=='O'){
            oCount++;
        }
        if (gameGrid[2][2]=='O'){
            oCount++;
        }
        if ((oCount==2)&&(dotCount==1)){
            return -1;
        }
        dotCount=0;
        oCount=0;
        if (gameGrid[0][2]=='.'){
            dotCount++;
        }
        if (gameGrid[1][1]=='.'){
            dotCount++;
        }
        if (gameGrid[2][0]=='.'){
            dotCount++;
        }
        if (gameGrid[0][2]=='O'){
            oCount++;
        }
        if (gameGrid[1][1]=='O'){
            oCount++;
        }
        if (gameGrid[2][0]=='O'){
            oCount++;
        }
        if ((oCount==2)&&(dotCount==1)){
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
                    System.out.printf("y=%d, ",i);
                    System.out.printf("x=%d, ",j);
                    int returnedValue = scoreChecker(simGrid);
                    System.out.printf("utility=%d\n",returnedValue);
                    simGrid[i][j]='.';//undo board state
                }
            }
        }
    }
    
}

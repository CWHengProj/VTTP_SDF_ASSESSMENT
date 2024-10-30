package vttp.batch5.sdf.task02.supp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public int turnCount=0;
    public List<List<Integer>> finalEval = new ArrayList<>();
    
    public List<List<Integer>> getFinalEval() {
        return finalEval;
    }
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
        //check who won, print out the winner name and exit the application  
        for (int i=0; i<3; i++){
            if (gameGrid[i][0]==gameGrid[i][1] &&gameGrid[i][1]==gameGrid[i][2]){
                if(gameGrid[i][0]== 'X' || gameGrid[i][0]=='O'){
                    if (gameGrid[i][0]=='X'){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
            }
        }
        for (int j=0; j<3; j++){
            if (gameGrid[0][j]==gameGrid[1][j] &&gameGrid[1][j]==gameGrid[2][j]){
                if(gameGrid[0][j]== 'X' || gameGrid[0][j]=='O'){
                    if (gameGrid[0][j]=='X'){
                        return 1;
                    }
                    else{
                        return -1;
                    }                }
            } 
        }
        if (gameGrid[0][0]==gameGrid[1][1] &&gameGrid[1][1]==gameGrid[2][2]){
            if(gameGrid[0][0]== 'X' || gameGrid[0][0]=='O'){
                if (gameGrid[0][0]=='X'){
                    return 1;
                }
                else{
                    return -1;
                }            
            }
        }
        if (gameGrid[0][2]==gameGrid[1][1] &&gameGrid[1][1]==gameGrid[2][0]){
            if(gameGrid[0][2]== 'X' || gameGrid[0][2]=='O'){
                if (gameGrid[0][2]=='X'){
                    return 1;
                }
                else{
                    return -1;
                }            }
        } 
        int emptyCells = 0; //check if current boardstate is end of game.
        for (char[] c: gameGrid){
            for (char x: c){
                if (x=='.'){
                    emptyCells++;
                }
            }
        }
        if (emptyCells==0){
            return 0;
        }
        if (turnCount==1){
            //if player turn has already be simulated and inconclusive, return 0 
            return 0;
        }
        else
            turnCount++;
            return simulateMoves(gameGrid,false); //Check the next state and its possibilities, return utility
    }
    public int simulateMoves(char[][] gameGrid, boolean isPlayerTurn) {
        int bestScore = -12222222;
        int worstScore = 12222222;
        char playerToken;
        if (isPlayerTurn){
            playerToken = 'X';
        }
        else
            playerToken='O';
        char[][] simGrid = gameGrid;
        //for each empty cell, try and check the win state.
        List<Integer> innerList = new ArrayList<>();
        //TODO add each state to a list where it can be returned
        for (int i=0;i<3;i++){
            for (int j=0; j<3; j++){
                if(simGrid[i][j]=='.'){
                    simGrid[i][j]= playerToken;
                    //simulate each position and map to a list the returned score
                    if (isPlayerTurn){
                        if (scoreChecker(simGrid)>bestScore){
                            bestScore= scoreChecker(simGrid);
                            innerList.add(j);
                            innerList.add(i);
                            innerList.add(bestScore);
                            finalEval.add(innerList);
                        }
                    }
                    else{
                        if (scoreChecker(simGrid)<worstScore){
                            worstScore= scoreChecker(simGrid);
                        }
                    }
                    simGrid[i][j]='.';//undo board state
                }
            }
        }
        if (isPlayerTurn){
            return bestScore;
        }
        else{

            return bestScore;
        }


    }
    
}

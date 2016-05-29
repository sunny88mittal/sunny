/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Backtracking;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Priyanka
 */
public class QueensProblem {

    private static final int QUEEN = 1;
    private static final int noOfQueens = 8;
    public static void main(String args[]) {
        int[][] board = new int[noOfQueens][noOfQueens];
        List<Position> queensPositions = new ArrayList<Position>();
        
        //Place a Queen at 0,0 position on the board
        board[0][0] = QUEEN;
        queensPositions.add(new Position(0,0));
        
        while(queensPositions.size() != noOfQueens){
            int lastQueenX = queensPositions.get(queensPositions.size()-1).x;
            boolean successfull = false;
             //Put a queen in in the next row
            for(int i=0; i<noOfQueens; i++){
                boolean isPossible = isPossiblePosition(queensPositions, lastQueenX+1, i);
                if(isPossible){
                    board[lastQueenX+1][i] =  QUEEN;
                    queensPositions.add(new Position(lastQueenX+1, i));
                    successfull = true;
                    break;
                }
            }
            
            //If it was not possible to put a queen in the next row
            //move the last queen to next possible position
            if(!successfull){
                boolean moveSuccessfull = false;
                while(!moveSuccessfull){
                   Position lastQueenPosition = queensPositions.remove(queensPositions.size()-1);
                   lastQueenX = lastQueenPosition.x;
                   int lastQueenY = lastQueenPosition.y;
                   board[lastQueenX][lastQueenY] = 0;
                   
                   ++lastQueenY;
                   
                   while(lastQueenY<noOfQueens){
                       boolean isPossible = isPossiblePosition(queensPositions, lastQueenX, lastQueenY);
                       if(isPossible){
                          board[lastQueenX][lastQueenY] =  QUEEN;
                          queensPositions.add(new Position(lastQueenX, lastQueenY));
                          moveSuccessfull = true;
                          break;
                       }
                       ++lastQueenY;
                   }
                }
            }
        }
        
        //The queen positions are
        for(Position position : queensPositions){
            System.out.println(position);
        }
    }
    
    /**
     * Checks whether it is possible to put a queen at the given position
     * @param board
     * @param x
     * @param y
     * @return 
     */
    private static boolean isPossiblePosition(List<Position> queensPositions, int x, int y){
        //Column check
        for(Position position : queensPositions){
            if(position.y == y){
                return false;
            }
        }
        
        //Diagonal Check
        for(Position position : queensPositions){
            if(Math.abs(x-position.x) == Math.abs(y-position.y)){
                return false;
            }
        }
        
        return true;
    }
    
    
    //Inner class to hold the postion of a queen
    private static class Position{
        public int x;
        public int y;
        
        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }
}


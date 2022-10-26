package se.kth.pellebeeabraham.labb4.model;

import static se.kth.pellebeeabraham.labb4.model.Square.isLegalValue;
import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;

public class SquareMatrix {
    private Square[][] squares;

    public SquareMatrix(){
        squares = new Square[GRID_SIZE][GRID_SIZE];
    }

    public Square[][] getSquareMatrix() {
        Square[][] copy = new Square[GRID_SIZE][GRID_SIZE];
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                copy[row][col] = squares[row][col];
            }
        }
        return copy;
    }

    /*public void intMatrixToSquareMatrix(int[][] intMatrix) {
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                squares[row][col] = new Square(intMatrix[row][col]);
            }
        }
    }*/

    public Square getSquare(int row, int col){
        return squares[row][col];
    }

    public boolean setSquare(int row, int col, int value){
        isLegalValue(value);
        Square s = squares[row][col];
        if(s == null){
            squares[row][col] = new Square(value);
        }
        else{
            if (!s.isChangeable()) return false;
            s.setSquareValue(value);
        }
        return true;
    }

    public int getNrOfNotChangeable(){
        int number = 0;
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(!squares[row][col].isChangeable()) number++;
            }
        }
        return number;
    }
}

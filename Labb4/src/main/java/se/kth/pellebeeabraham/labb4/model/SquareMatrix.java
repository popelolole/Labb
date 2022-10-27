package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;
import java.util.Arrays;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;

/**
 * This class represents logic and data for a square matrix, used to create objects of type Task.
 * A Project contains information about the project, and a list of tasks.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */

public class SquareMatrix implements Serializable {
    private final Square[][] squares;

    SquareMatrix(){
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

    public Square getSquare(int row, int col){
        return squares[row][col];
    }

    boolean setSquare(int row, int col, int value){
        Square s = getSquare(row, col);
        if (!s.isChangeable()) return false;
        s.setSquareValue(value);
        return true;
    }

    void setSquare(int row, int col, Square square){
        squares[row][col] = square;
    }

    public void createSquare(int row, int col, int value){
        squares[row][col] = new Square(value);
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

    @Override
    public String toString() {
        return "SquareMatrix{" +
                "squares: [" + Arrays.toString(squares) +
                "]}";
    }
}

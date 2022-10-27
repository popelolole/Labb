package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;

import static se.kth.pellebeeabraham.labb4.model.Square.isLegalValue;
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
    private final boolean[][] changeableMatrix; //TODO: set in MatrixHandler.java

    public SquareMatrix(){
        squares = new Square[GRID_SIZE][GRID_SIZE];
        changeableMatrix = new boolean[GRID_SIZE][GRID_SIZE];
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

    public boolean setSquare(int row, int col, int value){
        isLegalValue(value);
        Square s = getSquare(row, col);
        if (!changeableMatrix[row][col]) return false;
        s.setSquareValue(value);
        return true;
    }

    public void createSquare(int row, int col, int value){
        isLegalValue(value);
        squares[row][col] = new Square(value);
    }

    public boolean[][] getChangeableMatrix(){
        boolean[][] copy = new boolean[GRID_SIZE][GRID_SIZE];
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                copy[row][col] = changeableMatrix[row][col];
            }
        }
        return copy;
    }

    public boolean isChangeable(int row, int col){
        return changeableMatrix[row][col];
    }

    public void setChangeable(int row, int col, boolean changeable){
        changeableMatrix[row][col] = changeable;
    }

    public int getNrOfNotChangeable(){
        int number = 0;
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(!changeableMatrix[row][col]) number++;
            }
        }
        return number;
    }
}

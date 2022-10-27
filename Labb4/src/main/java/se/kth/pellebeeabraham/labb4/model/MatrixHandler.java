package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;
import java.util.Random;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;
import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.generateSudokuMatrix;

/**
 * This class represents logic and data to handle a square matrix.
 * A MatrixHandler contains information about the square matrix, and the objects of squares.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */

public class MatrixHandler implements Serializable {

    private SquareMatrix playMatrix;
    private SquareMatrix resultMatrix;
    private int nrOfMoves;
    private SudokuUtilities.SudokuLevel currentLevel;

    public MatrixHandler(SudokuUtilities.SudokuLevel level){
        reset(level);
    }

    /**
     * This method initializes a unique sudoku puzzle and two different matrixes,
     * one matrix for showing in view and one for the end game.
     * @param level The level, i.e. the difficulty of the sudoku puzzle.
     */
    public void reset(SudokuUtilities.SudokuLevel level){
        int intMatrix[][][] = generateSudokuMatrix(level);
        this.currentLevel = level;

        playMatrix = intMatrixToSquareMatrix(
                convert3DTo2DMatrix(intMatrix, 0));
        resultMatrix = intMatrixToSquareMatrix(
                convert3DTo2DMatrix(intMatrix, 1));

        nrOfMoves = playMatrix.getNrOfNotChangeable();
    }

    public void reset(MatrixHandler matrixHandler){
        playMatrix = matrixHandler.getPlayMatrix();
        resultMatrix = matrixHandler.getResultMatrix();

        currentLevel = matrixHandler.getCurrentLevel();
        nrOfMoves = playMatrix.getNrOfNotChangeable();
    }

    public SudokuUtilities.SudokuLevel getCurrentLevel(){
        return currentLevel;
    }

    /**
     * Converts the default 3D matrix to playMatrix and resultMatrix.
     * @param oldMatrix is the 3D matrix.
     * @param startOrResult is a flag used to choose start and result
     * values of the puzzle and put them to respective matrix.
     * @return
     */
    private int[][] convert3DTo2DMatrix(int[][][] oldMatrix, int startOrResult){
        int[][] newMatrix = new int[GRID_SIZE][GRID_SIZE];
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                newMatrix[row][col] = oldMatrix[row][col][startOrResult];
            }
        }
        return newMatrix;
    }

    private SquareMatrix intMatrixToSquareMatrix(int[][] intMatrix) {
        SquareMatrix squares = new SquareMatrix();
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                squares.createSquare(row, col, intMatrix[row][col]);
            }
        }
        return squares;
    }

    public boolean addNumberToSquare(int row, int col, int number){
        if(playMatrix.getSquare(row, col).getSquareValue() != 0 ||
                !playMatrix.getSquare(row,col).isChangeable()) return false;
        playMatrix.setSquare(row, col, number);
        nrOfMoves++;
        return true;
    }

    public void removeNumberFromSquare(int row, int col){
        playMatrix.setSquare(row, col, 0);
        nrOfMoves--;
    }

    public SquareMatrix getPlayMatrix(){
        SquareMatrix copy = new SquareMatrix();
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                copy.setSquare(row, col, playMatrix.getSquare(row, col));
            }
        }
        return copy;
    }

    public SquareMatrix getResultMatrix(){
        SquareMatrix copy = new SquareMatrix();
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                copy.setSquare(row, col, resultMatrix.getSquare(row, col));
            }
        }
        return copy;
    }

    public void clearPlayMatrix(){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.getSquare(row, col).isChangeable()) playMatrix.setSquare(row, col, 0);
            }
        }
        nrOfMoves = playMatrix.getNrOfNotChangeable();
    }

    public boolean isGameOver(){
        if(nrOfMoves < GRID_SIZE * GRID_SIZE) return false;
        return true;
    }

    //TODO: make separate method for checking isCorrectSoFar, checking if all moves are allowed
    public boolean isCorrect(){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.getSquare(row, col).getSquareValue() !=
                        resultMatrix.getSquare(row,col).getSquareValue()) {
                    if(playMatrix.getSquare(row, col).getSquareValue() != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method shows a random number to the puzzle.
     * First it calculates how many zeros the matrix has,
     * then it randomizes a number between 1 and the nrOfZeros.
     * Lastly it chooses a zero spot depending on the randomized number.
     */
    public void giveHint(){
        if(isGameOver()) return;
        int nrOfZeros = 0;
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.getSquare(row, col).getSquareValue() == 0) nrOfZeros++;
            }
        }
        Random rand = new Random();
        int number = rand.nextInt(nrOfZeros) + 1;
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.getSquare(row, col).getSquareValue() == 0) number--;
                if(number == 0){
                    addNumberToSquare(row, col, resultMatrix.getSquare(row, col).getSquareValue());
                    return;
                }
            }
        }
    }
}

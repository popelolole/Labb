package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;
import java.util.Random;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;
import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.generateSudokuMatrix;

public class MatrixHandler implements Serializable {

    private SquareMatrix playMatrix;
    private SquareMatrix resultMatrix;
    private int nrOfMoves;
    private SudokuUtilities.SudokuLevel currentLevel;

    public MatrixHandler(SudokuUtilities.SudokuLevel level){
        reset(level);
    }

    public void reset(SudokuUtilities.SudokuLevel level){
        int intMatrix[][][] = generateSudokuMatrix(level);
        this.currentLevel = level;

        playMatrix = intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 0));
        resultMatrix = intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 1));

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

    public int[][] convert3DIntMatrixTo2DIntMatrix(int[][][] oldMatrix, int startOrResult){
        int[][] newMatrix = new int[GRID_SIZE][GRID_SIZE];
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                newMatrix[row][col] = oldMatrix[row][col][startOrResult];
            }
        }
        return newMatrix;
    }

    public SquareMatrix intMatrixToSquareMatrix(int[][] intMatrix) {
        SquareMatrix squares = new SquareMatrix();
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                squares.createSquare(row, col, intMatrix[row][col]);
                if(squares.getSquare(row, col).getSquareValue() == 0)
                    squares.setChangeable(row, col, true);
                else
                    squares.setChangeable(row, col, false);
            }
        }
        return squares;
    }

    public boolean addNumberToSquare(int row, int col, int number){
        if(playMatrix.getSquare(row, col).getSquareValue() != 0) return false;
        if(!playMatrix.isChangeable(row, col)) return false;
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
                copy.createSquare(row, col, playMatrix.getSquare(row, col).getSquareValue());
                copy.setChangeable(row, col, playMatrix.isChangeable(row, col));
            }
        }
        return copy;
    }

    public SquareMatrix getResultMatrix(){
        SquareMatrix copy = new SquareMatrix();
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                copy.createSquare(row, col, resultMatrix.getSquare(row, col).getSquareValue());
                copy.setChangeable(row, col, playMatrix.isChangeable(row, col));
            }
        }
        return copy;
    }

    public void clearPlayMatrix(){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.isChangeable(row, col)) playMatrix.setSquare(row, col, 0);
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

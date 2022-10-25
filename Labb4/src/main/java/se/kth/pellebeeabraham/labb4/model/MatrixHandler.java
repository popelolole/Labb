package se.kth.pellebeeabraham.labb4.model;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;
import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.generateSudokuMatrix;

public class MatrixHandler {

    private SquareMatrix playMatrix;
    private SquareMatrix resultMatrix;
    private int nrOfMoves;

    public MatrixHandler(SudokuUtilities.SudokuLevel level){
        reset(level);
    }

    public void reset(SudokuUtilities.SudokuLevel level){
        int intMatrix[][][] = generateSudokuMatrix(level);
        playMatrix = intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 0));
        resultMatrix = intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 1));
        nrOfMoves = playMatrix.getNrOfNotChangeable();
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
                squares.setSquare(row, col, intMatrix[row][col]);
            }
        }
        return squares;
    }

    public boolean addNumberToSquare(int row, int col, int number){
        if(playMatrix.getSquare(row, col).getSquareValue() != 0) return false;
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
                copy.setSquare(row, col, playMatrix.getSquare(row, col).getSquareValue());
            }
        }
        return copy;
    }

    public boolean isGameOver(){
        if(nrOfMoves < GRID_SIZE * GRID_SIZE) return false;
        return true;
    }

    public boolean isSolved(){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                if(playMatrix.getSquare(row, col).getSquareValue() !=
                        resultMatrix.getSquare(row,col).getSquareValue())
                    return false;
            }
        }
        return true;
    }
}

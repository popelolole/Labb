package se.kth.pellebeeabraham.labb4.model;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;

public class MatrixHandler {

    private SquareMatrix startMatrix;
    private SquareMatrix resultMatrix;

    public MatrixHandler(int[][][] intMatrix){
        startMatrix.intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 0));
        resultMatrix.intMatrixToSquareMatrix(
                convert3DIntMatrixTo2DIntMatrix(intMatrix, 1));
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

    /*TODO: setStartMatrix
    TODO: getStartMatrix*/
}

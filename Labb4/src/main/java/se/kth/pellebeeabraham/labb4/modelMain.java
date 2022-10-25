package se.kth.pellebeeabraham.labb4;

import se.kth.pellebeeabraham.labb4.model.*;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;

public class modelMain {
    public static void main(String[] args) {

        SquareMatrix sm = new SquareMatrix();

        int[][][] values = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.EASY);
        int[][] startMatrix = new int[GRID_SIZE][GRID_SIZE];
        int[][] resultMatrix = new int[GRID_SIZE][GRID_SIZE];
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                startMatrix[row][col] = values[row][col][0];
            }
        }
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                resultMatrix[row][col] = values[row][col][1];
            }
        }
        MatrixHandler mh = new MatrixHandler(SudokuUtilities.SudokuLevel.EASY);

        sm = mh.getPlayMatrix();
        sm.getSquareMatrix();
    }
}

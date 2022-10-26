package se.kth.pellebeeabraham.labb4;

import se.kth.pellebeeabraham.labb4.model.*;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.GRID_SIZE;

public class modelMain {
    public static void main(String[] args) {

        MatrixHandler mh = new MatrixHandler(SudokuUtilities.SudokuLevel.EASY);

        for(int row = 0;row < 9;row++) {
            for(int col = 0;col < 9;col++){
                System.out.print(mh.getPlayMatrix().getSquare(row, col).getSquareValue());
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
        for(int row = 0;row < 9;row++) {
            for(int col = 0;col < 9;col++){
                System.out.print(mh.getResultMatrix().getSquare(row, col).getSquareValue());
            }
            System.out.print("\n");
        }
    }
}

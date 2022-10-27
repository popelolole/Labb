package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;

/**
 * This class represents a one square in a sudoku game,
 * it contains the chosen value.
 *
 * @author Pelle Berggren, pellebe@kth.se
 * @author Elias Abraham, eabraham@kth.se
 */

public class Square implements Serializable {
    private int squareValue;
    private boolean changeable;

    public Square(int squareValue) {
        this.squareValue = squareValue;
        if(squareValue == 0) changeable = true;
        else changeable = false;
    }

    public int getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(int squareValue) {
        this.squareValue = squareValue;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }

    @Override
    public String toString() {
        return "Square{" +
                "squareValue=" + squareValue +
                ", changeable=" + changeable +
                '}';
    }
}

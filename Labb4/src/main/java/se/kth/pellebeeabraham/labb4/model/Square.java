package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;

public class Square implements Serializable {
    private int squareValue;

    public Square(int squareValue) {
        this.squareValue = squareValue;
    }

    public int getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(int squareValue) {
        this.squareValue = squareValue;
    }
}

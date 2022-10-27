package se.kth.pellebeeabraham.labb4.model;

import java.io.Serializable;

public class Square implements Serializable {
    private int squareValue;

    public Square(int squareValue) throws IllegalArgumentException{
        isLegalValue(squareValue);
        this.squareValue = squareValue;
    }

    public int getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(int squareValue) {
        this.squareValue = squareValue;
    }

    public static void isLegalValue(int value) throws IllegalArgumentException{
        if(value < 0 || value > 9) throw new
                IllegalArgumentException("Illegal squareValue: " + value);
    }
}

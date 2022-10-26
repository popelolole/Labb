package se.kth.pellebeeabraham.labb4.model;

public class Square {
    private int squareValue;
    private final boolean changeable;

    public Square(int squareValue) throws IllegalArgumentException{
        isLegalValue(squareValue);
        this.squareValue = squareValue;
        if(squareValue != 0){
            changeable = false;
        }
        else changeable = true;
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

    public static void isLegalValue(int value) throws IllegalArgumentException{
        if(value < 0 || value > 9) throw new
                IllegalArgumentException("Illegal squareValue: " + value);
    }
}

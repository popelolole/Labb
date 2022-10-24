package se.kth.pellebeeabraham.labb4.model;

public class Square {
    private int squareValue;
    private final boolean visible;

    public Square(int squareValue) throws IllegalArgumentException{
        isLegalValue(squareValue);
        this.squareValue = squareValue;
        if(squareValue != 0){
            visible = true;
        }
        else visible = false;
    }

    public int getSquareValue() {
        return squareValue;
    }

    public void setSquareValue(int squareValue) {
        this.squareValue = squareValue;
    }

    public boolean isVisible() {
        return visible;
    }

    public void isLegalValue(int value) throws IllegalArgumentException{
        if(value < 0 || value > 9) throw new
                IllegalArgumentException("Illegal squareValue: " + value);
    }
}

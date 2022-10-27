package se.kth.pellebeeabraham.labb4.model;

public class Square {
    private int squareValue;
    //private boolean changeable;

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

    /*public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable){
        this.changeable = changeable;
    }*/

    public static void isLegalValue(int value) throws IllegalArgumentException{
        if(value < 0 || value > 9) throw new
                IllegalArgumentException("Illegal squareValue: " + value);
    }
}

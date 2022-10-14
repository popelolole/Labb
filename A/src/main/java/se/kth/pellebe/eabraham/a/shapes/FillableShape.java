package se.kth.pellebe.eabraham.a.shapes;

import javafx.scene.paint.Color;

public abstract class FillableShape extends Shape{
    private boolean filled;

    protected FillableShape(double x, double y, Color color) {
        super(x, y, color);
    }
    protected FillableShape(){
        super();
    }

    public boolean isFilled(){
        return filled;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }
}

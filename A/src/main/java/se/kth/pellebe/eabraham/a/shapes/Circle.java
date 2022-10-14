package se.kth.pellebe.eabraham.a.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape{
    private double diameter;

    public Circle(double x, double y, double diameter, Color color){
        super(x, y, color);
        this.diameter = diameter;
    }
    public Circle(){
        super();
        this.diameter = 5;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        if(diameter <= 0) diameter = 1;
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(!isFilled()){
            gc.setStroke(getColor());
            gc.strokeOval(getX(), getY(), diameter, diameter);
        }
        else{
            gc.setFill(getColor());
            gc.fillOval(getX(), getY(), diameter, diameter);
        }
    }

    @Override
    protected void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (getX() + diameter > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        if (getY() < boxY) {
            setVelocity(getDx(), Math.abs(getDy()));
        } else if (getY() + diameter > boxHeight) {
            setVelocity(getDx(), -Math.abs(getDy()));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "diameter=" + diameter;
    }
}

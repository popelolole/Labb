package se.kth.pellebe.eabraham.a.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FillableShape{
    private double width, height;

    public Rectangle(double x, double y, double width, double height, Color color){
        super(x, y, color);
        this.width = width;
        this.height = height;
    }
    public Rectangle(){
        super();
        this.width = 10;
        this.height = 15;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width <= 0) width = 10;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height <= 0) height = 15;
        this.height = height;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(!isFilled()){
            gc.setStroke(getColor());
            gc.strokeRect(getX(), getY(), width, height);
        }
        else{
            gc.setFill(getColor());
            gc.fillRect(getX(), getY(), width, height);
        }
    }

    @Override
    protected void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (getX() + width > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        if (getY() < boxY) {
            setVelocity(getDx(), Math.abs(getDy()));
        } else if (getY() + height > boxHeight) {
            setVelocity(getDx(), -Math.abs(getDy()));
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "width=" + width +
                ", height=" + height;
    }
}

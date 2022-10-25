package se.kth.pellebeeabraham.labb4.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author anderslm@kth.se
 */
public class Line {
    
    public Line(Point2D start, Point2D end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    public Point2D getStart() {
        return start;
    }
    
    public Point2D getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }
    
    public String toString() {
        return start.toString() + " -> " + end.toString();
    }
    
    private Point2D start, end;
    private Color color;
}

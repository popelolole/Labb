/**
 *
 * @author Anders Lindström, anderslm@kth.se
 */
package se.kth.pellebeeabraham.labb4.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anders Lindström, anderslm@kth.se
 */
public class Lines {

    public Lines() {
        lines = new ArrayList<>();
    }
    
    public void clear() {
        lines.clear();
    }
    
    public int size() {
        return lines.size();
    }
    
    public void add(Line line) {
        lines.add(line);
    }
    
    public List<Line> getLines() {
        return (ArrayList<Line>) lines.clone();
    }
    
    private final ArrayList<Line> lines;
}


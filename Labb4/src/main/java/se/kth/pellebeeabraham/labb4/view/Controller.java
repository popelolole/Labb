package se.kth.pellebeeabraham.labb4.view;

import se.kth.pellebeeabraham.labb4.model.Line;
import se.kth.pellebeeabraham.labb4.model.Lines;

public class Controller {

    private Lines model;
    private ScribbleView view;

    public Controller(Lines model, ScribbleView view){
        this.model = model;
        this.view = view;
    }

    public void handleNewLine(Line line){
        model.add(line);
        view.updateCanvas();
    }

    public void handleClear(){
        model.clear();
        view.clearCanvas();
    }
}

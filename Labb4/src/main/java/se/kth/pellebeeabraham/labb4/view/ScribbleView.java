package se.kth.pellebeeabraham.labb4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import se.kth.pellebeeabraham.labb4.Main;
import se.kth.pellebeeabraham.labb4.model.Line;
import se.kth.pellebeeabraham.labb4.model.Lines;

import java.util.List;

public class ScribbleView extends BorderPane {

    private Lines model;
    private Canvas canvas;
    private Button clearButton;

    public ScribbleView(Lines model){
        super();
        this.model = model;
        this.canvas = new Canvas(400, 400);
        clearButton = new Button("Clear");

        this.setCenter(canvas);

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        pane.getChildren().add(clearButton);
        this.setBottom(pane);

        Controller controller = new Controller(model, this);
        addEventHandlers(controller);
    }

    public void updateCanvas(){
        List<Line> lines = model.getLines();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(Line l : lines) {
            gc.strokeLine(l.getStart().getX(), l.getStart().getY(), l.getEnd().getX(), l.getEnd().getY());
        }
    }

    public void clearCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void addEventHandlers(Controller controller){

        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

            private Point2D start = null;
            @Override
            public void handle(MouseEvent event) {
                if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                    start = new Point2D(event.getX(), event.getY());
                }else if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
                    Point2D end = new Point2D(event.getX(), event.getY());
                    Line line = new Line(start, end, Color.BLACK);
                    model.add(line);

                    controller.handleNewLine(line);
                }
            }
        };
        canvas.addEventHandler(MouseEvent.ANY, mouseHandler);

        EventHandler<ActionEvent> clearHandler = new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleClear();
            }
        };
        clearButton.addEventHandler(ActionEvent.ACTION, clearHandler);
    }
}

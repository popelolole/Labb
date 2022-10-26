package se.kth.pellebeeabraham.labb4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;

public class GameView extends BorderPane {
    private VBox numberButtonPane;
    private Button[] numberButtons;
    private GridView gridPane;
    private VBox gameButtonPane;
    private MatrixHandler matrixHandler;

    public GameView(MatrixHandler matrixHandler){
        gridPane = new GridView(matrixHandler);
        this.setCenter(gridPane);

        numberButtons = new Button[10];

        numberButtonPane = new VBox();
        numberButtonPane = makeNumberButtonPane();
        this.setRight(numberButtonPane);
    }

    private VBox makeNumberButtonPane(){
        for(int i = 0;i < 9;i++){
            numberButtons[i] = new Button("" + (i + 1));
        }
        numberButtons[9] = new Button("C");


        for(int i = 0;i < 10;i++) {
            numberButtonPane.getChildren().add(numberButtons[i]);
            numberButtons[i].addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        }
        numberButtonPane.setAlignment(Pos.CENTER);
        numberButtonPane.setPadding(new Insets(5));
        numberButtonPane.setSpacing(2);

        return numberButtonPane;
    }

    private EventHandler<ActionEvent> numberButtonHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            Button button = (Button) event.getSource();
            for(int i = 0;i < 9;i++){
                if(button == numberButtons[i]) gridPane.setNumber(i + 1);
            }
            if(button == numberButtons[9]) gridPane.setClear(true);
        }
    };
}

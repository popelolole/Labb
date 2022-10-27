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
    private Button[] gameButtons;
    private VBox gameButtonPane;
    private MatrixHandler matrixHandler;

    public GameView(MatrixHandler matrixHandler){
        this.matrixHandler = matrixHandler;

        gridPane = new GridView(matrixHandler);
        this.setCenter(gridPane);

        numberButtons = new Button[10];

        numberButtonPane = new VBox();
        makeNumberButtonPane();
        this.setRight(numberButtonPane);

        gameButtons = new Button[2];
        gameButtonPane = new VBox();
        makeGameButtonPane();
        this.setLeft(gameButtonPane);
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

    private VBox makeGameButtonPane(){
        gameButtons[0] = new Button("Check");
        gameButtons[1] = new Button("Hint");

        for(int i = 0;i < 2;i++){
            gameButtonPane.getChildren().add(gameButtons[i]);
            gameButtons[i].addEventHandler(ActionEvent.ACTION, gameButtonHandler);
        }
        gameButtonPane.setAlignment(Pos.CENTER);
        gameButtonPane.setPadding(new Insets(5));
        gameButtonPane.setSpacing(10);
        return gameButtonPane;
    }

    //TODO: make addEventHandlers method
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

    private EventHandler<ActionEvent> gameButtonHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            System.out.println("check1");
            Button button = (Button) event.getSource();
            if(button == gameButtons[0]) {
                System.out.println("check2");
                System.out.println(matrixHandler.isCorrect());
            }
            else if(button == gameButtons[1]) {
                System.out.println("check3");
                matrixHandler.giveHint();
                gridPane.update();
            }
        }
    };
}

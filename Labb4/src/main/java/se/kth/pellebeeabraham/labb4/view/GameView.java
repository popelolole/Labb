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
    private Controller controller;

    public GameView(MatrixHandler matrixHandler, Controller controller){
        this.matrixHandler = matrixHandler;

        gridPane = new GridView(matrixHandler, controller);
        this.setCenter(gridPane);

        numberButtons = new Button[10];

        numberButtonPane = new VBox();
        makeNumberButtonPane();
        this.setRight(numberButtonPane);

        gameButtons = new Button[2];
        gameButtonPane = new VBox();
        makeGameButtonPane();
        this.setLeft(gameButtonPane);

        this.controller = controller;
    }

    public GridView getGridPane() {
        return gridPane;
    }

    /**
     * This method creates the number buttons on the right side of the sudoku window.
     * @returns the VBox with numbers buttons as chilren.
     */
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

    private EventHandler<ActionEvent> numberButtonHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            Button button = (Button) event.getSource();
            for(int i = 0;i < 9;i++){
                if(button == numberButtons[i])
                    controller.handleSetNumber(i + 1);
            }
            if(button == numberButtons[9])
                controller.handleSetClear();
        }
    };

    private EventHandler<ActionEvent> gameButtonHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            Button button = (Button) event.getSource();
            if(button == gameButtons[0]) {
                controller.handleCheck();
            }
            else if(button == gameButtons[1]) {
                controller.handleHint();
            }
        }
    };
}

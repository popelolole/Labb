package se.kth.pellebeeabraham.labb4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;
import se.kth.pellebeeabraham.labb4.model.SudokuUtilities;

import java.io.File;
import java.io.IOException;

public class SudokuView extends VBox {
    private GameView gamePane;
    private MenuBar menuBar;
    private MatrixHandler matrixHandler;
    private Controller controller;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private FileChooser fileChooser;
    private Stage primaryStage;


    public SudokuView(MatrixHandler matrixHandler, Stage primaryStage){
        this.matrixHandler = matrixHandler;
        this.controller = new Controller(matrixHandler, this);
        createMenuBar();
        gamePane = new GameView(matrixHandler, controller);
        controller.setGridAndGameView();
        this.primaryStage = primaryStage;
        createFileChooser();

        this.getChildren().addAll(menuBar, gamePane);
    }

    public GameView getGamePane() {
        return gamePane;
    }

    public void showAlert(String text, String title){
        alert.setHeaderText("");
        alert.setTitle(title);
        alert.setContentText(text);
        alert.show();
    }

    //TODO: add controller
    private void createMenuBar(){
        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load Game");
        MenuItem saveItem = new MenuItem("Save Game");
        MenuItem exitItem = new MenuItem("Exit");
        EventHandler<ActionEvent> fileHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                MenuItem item = (MenuItem) event.getSource();
                if(item == loadItem){
                    try {
                        controller.handleLoadFile();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally{}
                }
                else if(item == saveItem){
                    try {
                        controller.handleSaveFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(item == exitItem){
                    controller.handleExit();
                }
            }
        };
        loadItem.setOnAction(fileHandler);
        saveItem.setOnAction(fileHandler);
        exitItem.setOnAction(fileHandler);
        fileMenu.getItems().addAll(loadItem, saveItem, exitItem);

        Menu gameMenu = new Menu("Game");
        MenuItem newGameItem = new MenuItem("New Game");
        Menu levelMenu = new Menu("Select New Level");
        MenuItem easyItem = new MenuItem("Easy");
        MenuItem mediumItem = new MenuItem("Medium");
        MenuItem hardItem = new MenuItem("Hard");
        EventHandler<ActionEvent> gameHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                MenuItem item = (MenuItem) event.getSource();
                if(item == newGameItem){
                    controller.handleNewGame();
                }
                else if(item == easyItem){
                    controller.handleNewLevel((SudokuUtilities.SudokuLevel.EASY));
                }
                else if(item == mediumItem){
                    controller.handleNewLevel(SudokuUtilities.SudokuLevel.MEDIUM);
                }
                else if(item == hardItem){
                    controller.handleNewLevel(SudokuUtilities.SudokuLevel.HARD);
                }
            }
        };
        newGameItem.setOnAction(gameHandler);
        easyItem.setOnAction(gameHandler);
        mediumItem.setOnAction(gameHandler);
        hardItem.setOnAction(gameHandler);
        levelMenu.getItems().addAll(easyItem, mediumItem, hardItem);
        gameMenu.getItems().addAll(newGameItem, levelMenu);

        Menu helpMenu = new Menu("Help");
        MenuItem clearItem = new MenuItem("Clear");
        MenuItem checkItem = new MenuItem("Check");
        MenuItem infoItem = new MenuItem("Info");
        EventHandler<ActionEvent> helpHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                MenuItem item = (MenuItem) event.getSource();
                if(item == clearItem){
                    controller.handleClear();
                }
                else if(item == checkItem){
                    controller.handleCheck();
                }
                else if(item == infoItem){
                    controller.handleInfo();
                }
            }
        };
        clearItem.setOnAction(helpHandler);
        checkItem.setOnAction(helpHandler);
        infoItem.setOnAction(helpHandler);
        helpMenu.getItems().addAll(clearItem, checkItem, infoItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    public void createFileChooser(){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Desired File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Sudoku Files", "*.sudoku"));
    }

    public File openFileChooser(){
        return(fileChooser.showOpenDialog(primaryStage));
    }

    public File saveFileChooser(){
        fileChooser.setTitle("Save File");
        return(fileChooser.showSaveDialog(primaryStage));
    }
}

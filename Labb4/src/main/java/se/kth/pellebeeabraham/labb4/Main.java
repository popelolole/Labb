package se.kth.pellebeeabraham.labb4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;
import se.kth.pellebeeabraham.labb4.model.SudokuUtilities;
import se.kth.pellebeeabraham.labb4.view.SudokuView;

public class Main extends Application {

    private MatrixHandler matrixHandler;
    @Override
    public void start(Stage primaryStage) {

        matrixHandler = new MatrixHandler(SudokuUtilities.SudokuLevel.EASY);

        SudokuView view = new SudokuView(matrixHandler, primaryStage);

        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        primaryStage.setTitle("Sudoku 2.0");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package se.kth.pellebeeabraham.labb4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;
import se.kth.pellebeeabraham.labb4.model.SudokuUtilities;
import se.kth.pellebeeabraham.labb4.view.SudokuView;

import java.util.List;

public class Main extends Application {

    private MatrixHandler matrixHandler;
    @Override
    public void start(Stage primaryStage) {

        matrixHandler = new MatrixHandler(SudokuUtilities.SudokuLevel.EASY);

        //model = new Lines();
        SudokuView view = new SudokuView(matrixHandler, primaryStage);

        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        primaryStage.setTitle("Hello JavaFX!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
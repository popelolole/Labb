package se.kth.pellebeeabraham.labb4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.kth.pellebeeabraham.labb4.model.Line;
import se.kth.pellebeeabraham.labb4.model.Lines;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;
import se.kth.pellebeeabraham.labb4.model.SudokuUtilities;
import se.kth.pellebeeabraham.labb4.view.GameView;
import se.kth.pellebeeabraham.labb4.view.GridView;
import se.kth.pellebeeabraham.labb4.view.ScribbleView;

import java.util.List;

public class Main extends Application {

    private MatrixHandler matrixHandler;
    @Override
    public void start(Stage primaryStage) {

        matrixHandler = new MatrixHandler(SudokuUtilities.SudokuLevel.EASY);

        //model = new Lines();
        GameView view = new GameView(matrixHandler);

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
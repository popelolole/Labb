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
import se.kth.pellebeeabraham.labb4.view.ScribbleView;

import java.util.List;

public class Main extends Application {

    private Lines model;
    @Override
    public void start(Stage primaryStage) {

        model = new Lines();
        ScribbleView view = new ScribbleView(model);

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
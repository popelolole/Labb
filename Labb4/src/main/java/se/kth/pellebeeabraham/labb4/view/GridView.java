package se.kth.pellebeeabraham.labb4.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;

import static se.kth.pellebeeabraham.labb4.model.SudokuUtilities.*;

public class GridView extends Pane {
    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private MatrixHandler matrixHandler;
    private int number;
    private boolean clear;

    public GridView(MatrixHandler matrixHandler) {
        this.matrixHandler = matrixHandler;
        clear = false;

        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();
        // ...

        numberPane = makeNumberPane();
        // ...

        this.getChildren().add(numberPane);

        //numberPane.requestFocus();

    }

    // called by constructor (only)
    private final void initNumberTiles() {
        Font font1 = Font.font("Monospaced", FontWeight.NORMAL, 20);
        Font font2 = Font.font("Monospaced", FontWeight.BOLD, 20);

        for(int row2 = 0;row2 < 9;row2++) {
            for(int col2 = 0;col2 < 9;col2++){
                System.out.print(matrixHandler.getPlayMatrix().getSquare(row2, col2).getSquareValue());
            }
            System.out.print("\n");
        }
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Label tile;
                if(matrixHandler.getPlayMatrix().getSquare(row, col).getSquareValue() != 0) {
                    tile = new Label("" + matrixHandler.getPlayMatrix().getSquare(row, col).getSquareValue());
                }
                else tile = new Label("");
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                /*if(matrixHandler.getPlayMatrix().getSquare(row, col).isChangeable())
                    tile.setFont(font1);
                else */tile.setFont(font2);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                tile.addEventHandler(MouseEvent.MOUSE_CLICKED, tileClickHandler);
                //tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                tile.setId("" + row + col);
                /*tile.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                        System.out.println("Label Text Changed");
                    }
                });*/
                numberTiles[row][col] = tile;
            }
        }
    }

    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }

        return root;
    }

    public void update(){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                //Label tile = new Label("");
                if(matrixHandler.getPlayMatrix().getSquare(row, col).getSquareValue() != 0) {
                    numberTiles[row][col].setText("" + matrixHandler.getPlayMatrix().getSquare(row, col).getSquareValue());
                }
                else numberTiles[row][col].setText("");

                if(matrixHandler.getPlayMatrix().isChangeable(row, col)) {
                    numberTiles[row][col].setFont(Font.font("Monospaced", FontWeight.NORMAL, 20));
                }
                else{
                    numberTiles[row][col].setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
                }
                numberTiles[row][col].addEventHandler(MouseEvent.MOUSE_CLICKED, tileClickHandler);
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    private EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            for(int row = 0; row < GRID_SIZE; row++) {
                for(int col = 0; col < GRID_SIZE; col++) {
                    if(((Label) event.getSource()).getId().equals(numberTiles[row][col].getId())) {
                        if(clear) {
                            matrixHandler.removeNumberFromSquare(row, col);
                            clear = false;
                        }
                        else {
                            matrixHandler.addNumberToSquare(row, col, number);
                        }
                        update();
                        return;
                    }
                }
            }
        }
    };
}

package se.kth.pellebeeabraham.labb4.view;

import javafx.stage.Stage;
import se.kth.pellebeeabraham.labb4.IO.TextFileReader;
import se.kth.pellebeeabraham.labb4.model.MatrixHandler;
import se.kth.pellebeeabraham.labb4.model.SudokuUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static se.kth.pellebeeabraham.labb4.IO.SudokuFileIO.deSerializeFromFile;
import static se.kth.pellebeeabraham.labb4.IO.SudokuFileIO.serializeToFile;

public class Controller {

    private MatrixHandler model;
    private SudokuView mainView;
    private GameView gameView;
    private GridView gridView;
    private File sudokuFile;

    public Controller(MatrixHandler model, SudokuView mainView){
        this.model = model;
        this.mainView = mainView;

    }

    public void setGridAndGameView(){
        this.gameView = mainView.getGamePane();
        this.gridView = gameView.getGridPane();
    }

    public void handleAddNumber(int row, int col, int number){
        model.addNumberToSquare(row, col, number);
        gridView.update();
    }

    public void handleClearSquare(int row, int col){
        model.removeNumberFromSquare(row, col);
        gridView.setClear(false);
        gridView.update();
    }

    public void handleSetNumber(int value){
        gridView.setNumber(value);
    }

    public void handleSetClear(){
        gridView.setClear(true);
        gridView.setNumber(0);
    }

    public void handleCheck(){
        if(model.isCorrect()){
            mainView.showAlert("Correct so far.", "Alert!");
        }
        else mainView.showAlert("Wrong.", "Alert!");
    }

    public void handleHint(){
        model.giveHint();
        gridView.update();
    }

    public void handleLoadFile() throws IOException, FileNotFoundException,ClassNotFoundException {
        sudokuFile = mainView.openFileChooser();
        MatrixHandler matrixHandlerFromFile = null;
        try {
            matrixHandlerFromFile = deSerializeFromFile(sudokuFile);
        }
        catch(FileNotFoundException | ClassNotFoundException e){
            mainView.showAlert("Could not load objects from file: " +
                    sudokuFile.getName(), "Alert!");
        }catch(IOException io){
            throw new IOException(io);
        }

        model.reset(matrixHandlerFromFile);
        gridView.update();
    }

    public void handleSaveFile() throws IOException{
        if(sudokuFile == null){
            sudokuFile = mainView.saveFileChooser();
        }
        try {
            serializeToFile(sudokuFile, model);
        }
        catch(IOException io){
            mainView.showAlert("Problem Saving File.", "Alert!");
            throw new IOException(io);
        }
    }

    public void handleExit(){
        System.exit(0);
    }

    public void handleNewGame(){
        model.reset(model.getCurrentLevel());
        gridView.update();
    }

    public void handleNewLevel(SudokuUtilities.SudokuLevel level){
        model.reset(level);
        gridView.update();
    }

    public void handleClear(){
        model.clearPlayMatrix();
        gridView.update();
    }

    public void handleGameOver(){
        if(model.isGameOver()){
            if(model.isCorrect()){
                mainView.showAlert("You've solved the puzzle.", "Congratulations!");
            }
            else{
                mainView.showAlert("You've failed to solve the puzzle. Keep trying!", "Too bad!");
            }
        }
    }

    public void handleInfo(){
        mainView.showAlert("Sudoku is played on a grid of 9 x 9 spaces. " +
                "Within the rows and columns are 9 “squares” (made up of 3 x 3 spaces)." +
                " Each row, column and square (9 spaces each) needs to be filled out with the numbers 1-9," +
                "without repeating any numbers within the row, column or square. Does it sound complicated? " +
                "Each Sudoku grid comes with a few spaces already filled in; the more spaces filled in, " +
                "the easier the game – the more difficult Sudoku puzzles have very few spaces that are already filled in.",
                "Info");
    }
}

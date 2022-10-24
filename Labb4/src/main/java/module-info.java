module se.kth.pellebeeabraham.labb4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.pellebeeabraham.labb4 to javafx.fxml;
    exports se.kth.pellebeeabraham.labb4;
}
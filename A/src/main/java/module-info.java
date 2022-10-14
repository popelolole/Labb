module se.kth.pellebe.eabraham.a {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.pellebe.eabraham.a to javafx.fxml;
    exports se.kth.pellebe.eabraham.a;
}
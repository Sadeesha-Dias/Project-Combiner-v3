module com.example.projectcombinerv3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.projectcombinerv3 to javafx.fxml;
    exports com.example.projectcombinerv3;
}
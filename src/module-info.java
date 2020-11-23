module WineRater2 {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens com.avsk.rater;
    opens datamodel;
}
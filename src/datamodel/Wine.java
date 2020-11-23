package datamodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Wine {

    //Store as properties to take advantage of data binding and writing to UI
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty color = new SimpleStringProperty("");
    private SimpleIntegerProperty year = new SimpleIntegerProperty(0);
    private SimpleStringProperty producer = new SimpleStringProperty("");
    private SimpleStringProperty variety = new SimpleStringProperty("");
    private SimpleIntegerProperty rating = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);

    //Default constructor for when loading the data
    public Wine() {
    }
}

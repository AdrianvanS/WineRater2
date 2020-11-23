package com.avsk.rater;

import datamodel.Wine;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class WineController {

    //Same field names as the fx:ids in winedialog.fxml
    @FXML
    private TextField nameField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField producerField;

    @FXML
    private TextField varietyField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField priceField;

    //Return data entered in dialog
    public Wine getWine(){
        String name = nameField.getText();
        String color = colorField.getText();
        int year = parseInt(yearField.getText());
        String producer = producerField.getText();
        String variety = varietyField.getText();
        int rating = parseInt(ratingField.getText());
        double price = parseDouble(priceField.getText());

        return new Wine(name, color, year, producer, variety, rating, price);
    }
}

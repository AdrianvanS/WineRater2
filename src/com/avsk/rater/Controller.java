package com.avsk.rater;

import datamodel.Wine;
import datamodel.WineList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Controller {

    @FXML
    private BorderPane mainPanel;

    //Instance of TableView to assign the data to
    @FXML
    private TableView<Wine> winesTable;

    //Add an instance of WineList class for use in initialize
    private WineList wines;

    //Initialize the WineList instance
    public void initialize(){
        wines = new WineList();
        wines.addWine(new Wine("Jacobs", "Red", 1998, "Villa Park", "Shiraz", 6, 4.99));
        wines.addWine(new Wine("Bobs", "Red", 1999, "Villa Park", "Shiraz", 7, 6.99));
        wines.addWine(new Wine("Natal Rose", "Rose", 2015, "Kloof Park", "Shiraz", 7, 5.75));
        wines.addWine(new Wine("Clear Waters", "White", 2016, "Harry's Place", "Zinfandel", 5, 3.50));

        winesTable.setItems(wines.getWines());
    }

    //Handle Exit
    @FXML
    public void handleExit(){
        Platform.exit();
    }
}

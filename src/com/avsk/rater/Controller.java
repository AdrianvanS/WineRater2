package com.avsk.rater;

import datamodel.Wine;
import datamodel.WineList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

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
//        wines.addWine(new Wine("Jacobs", "Red", 1998, "Villa Park", "Shiraz", 6, 4.99));
//        wines.addWine(new Wine("Bobs", "Red", 1999, "Villa Park", "Shiraz", 7, 6.99));
//        wines.addWine(new Wine("Natal Rose", "Rose", 2015, "Kloof Park", "Shiraz", 7, 5.75));
//        wines.addWine(new Wine("Clear Waters", "White", 2016, "Harry's Place", "Zinfandel", 5, 3.50));
//        wines.addWine(new Wine("Justina's Delight", "White", 1992, "Toby's", "Zinfandel", 7, 4.50));

        //Read in saved wines
        wines.loadWines();

        //Initialize WinesTable
        winesTable.setItems(wines.getWines());
    }

    @FXML
    public void showAddWineDialog() {
        //Create a dialog instance and assign the mainPanel as its parent
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Wine");
        FXMLLoader fxmlLoader = new FXMLLoader();
        //Set the dialog's content to what's in the winedialog.fxml
        fxmlLoader.setLocation(getClass().getResource("winedialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        //Added buttons
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        //Open the dialog
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //Get access to controller associated with winedialog.fxml
            WineController wineController = fxmlLoader.getController();
            //Call the getWine() method from WineController
            Wine newWine = wineController.getWine();
            //Add new contact to the data list
            wines.addWine(newWine);
            //Save the wines to the xml
            wines.saveWines();
        }
    }

    //Handle Exit
    @FXML
    public void handleExit(){
        Platform.exit();
    }






















}

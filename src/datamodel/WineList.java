package datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WineList {

    private ObservableList<Wine> wines;

    //Initialize the wines list
    public WineList(){
        wines = FXCollections.observableArrayList();
    }

    //Access wines
    public ObservableList<Wine> getWines(){
        return wines;
    }

    //Add a wine
    public void addWine(Wine wine){
        wines.add(wine);
    }

    //Remove a wine
    public void deleteWine(Wine wine){
        wines.remove(wine);
    }

}

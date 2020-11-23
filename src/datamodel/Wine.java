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

    public Wine(String name, String color, int year, String producer, String variety, int rating, double price){
        this.name.set(name);
        this.color.set(color);
        this.year.set(year);
        this.producer.set(producer);
        this.variety.set(variety);
        this.rating.set(rating);
        this.price.set(price);
    }

    //Getters and Setter

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getProducer() {
        return producer.get();
    }

    public SimpleStringProperty producerProperty() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer.set(producer);
    }

    public String getVariety() {
        return variety.get();
    }

    public SimpleStringProperty varietyProperty() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety.set(variety);
    }

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

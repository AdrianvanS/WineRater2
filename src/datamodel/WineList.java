package datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class WineList {

    //Update to wines xml

    private static final String WINES_FILE = "wines.xml";

    private static final String WINE = "wine";
    private static final String NAME = "name";
    private static final String COLOR = "color";
    private static final String YEAR = "year";
    private static final String PRODUCER = "producer";
    private static final String VARIETY = "variety";
    private static final String RATING = "rating";
    private static final String PRICE = "price";

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

    public void loadWines() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(WINES_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Wine wine = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a wine item, we create a new wine
                    if (startElement.getName().getLocalPart().equals(WINE)) {
                        wine = new Wine();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(NAME)) {
                            event = eventReader.nextEvent();
                            wine.setName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(COLOR)) {
                        event = eventReader.nextEvent();
                        wine.setColor(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(YEAR)) {
                        event = eventReader.nextEvent();
                        wine.setYear(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PRODUCER)) {
                        event = eventReader.nextEvent();
                        wine.setProducer(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(VARIETY)) {
                        event = eventReader.nextEvent();
                        wine.setVariety(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(RATING)) {
                        event = eventReader.nextEvent();
                        wine.setRating(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PRICE)) {
                        event = eventReader.nextEvent();
                        wine.setPrice(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }
                }

                // If we reach the end of a wine element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(WINE)) {
                        wines.add(wine);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveWines() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(WINES_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement winesStartElement = eventFactory.createStartElement("",
                    "", "wines");
            eventWriter.add(winesStartElement);
            eventWriter.add(end);

            for (Wine wine: wines) {
                saveWine(eventWriter, eventFactory, wine);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "wines"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Wines file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing wine: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveWine(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Wine wine)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create wine open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", WINE);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, NAME, wine.getName());
        createNode(eventWriter, COLOR, wine.getColor());
        createNode(eventWriter, YEAR, String.valueOf(wine.getYear()));
        createNode(eventWriter, PRODUCER, wine.getProducer());
        createNode(eventWriter, VARIETY, wine.getVariety());
        createNode(eventWriter, RATING, String.valueOf(wine.getRating()));
        createNode(eventWriter, PRICE, String.valueOf(wine.getPrice()));

        eventWriter.add(eventFactory.createEndElement("", "", WINE));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}


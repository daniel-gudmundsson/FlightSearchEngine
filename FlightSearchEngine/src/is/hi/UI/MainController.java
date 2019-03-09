/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.Core.*;
import is.hi.UI.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dantg
 */
public class MainController implements Initializable {

    @FXML
    private ComboBox<?> fromComboBox;
    @FXML
    private ComboBox<?> toComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Slider priceSlider;
    @FXML
    private TextField priceTextField;
    @FXML
    private ListView<Flight> flightListView;
    @FXML
    private Button searchButton;
    
    private FlightController flightController;
    private DatabaseController databaseController;
    private BookingController bookingController;
    
    // Þessar þrjár línur eru í vinnslu
    private String [] airports = {"Reykjavík", "Akueryi", "Vestmanneyjar", "Ísafjörður", "Egilsstaðir"};
    private ArrayList<String> listOfAirports = new ArrayList<String>();
    private ObservableList<String> ob = FXCollections.observableArrayList(); 
    
    
    private ObservableList<Flight> loadedFlights;
    private String to;
    private String from;
    private LocalDate date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flightController = new FlightController();
        bookingController = new BookingController();
        databaseController = new DatabaseController();
        
        flightController.initializeControllers(databaseController);
        
        initalizeComboboxes();
    }  

    @FXML
    private void searchHandler(ActionEvent event) {
        from = "Reykjavík";//fromComboBox.getPromptText();
        to = "Akureyri"; //toComboBox.getPromptText();
        date = datePicker.getValue();
        
        loadedFlights = FXCollections.observableArrayList(flightController.searchForFlight(from, to, date));
        
        flightListView.setItems(loadedFlights);
        
        
        
        
        
    }

    private void initalizeComboboxes() {
        for(String s: airports)
        {
            ob.add(s);
        }
        
        //ObservableList<String> ob = FXCollections.observableArrayList(listOfAirports);
        //fromComboBox.setItems(ob);
        
    }
    
    
    
    
}

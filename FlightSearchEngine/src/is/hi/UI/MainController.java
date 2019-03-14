/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.Core.*;
import is.hi.UI.*;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * FXML Controller class
 *
 * @author dantg
 */
public class MainController implements Initializable {

    @FXML
    private ComboBox<String> fromComboBox;
    @FXML
    private ComboBox<String> toComboBox;
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
    
    @FXML
    private TicketCreationDialogController tickeDialogController;
    
    // Þessar þrjár línur eru í vinnslu
    private String [] airports = {"Reykjavík", "Akueryi", "Vestmanneyjar", "Ísafjörður", "Egilsstaðir"};
    private ArrayList<String> listOfAirports = new ArrayList<String>();
    private ObservableList<String> ob = FXCollections.observableArrayList(); 
    
    int activeIndex = -1;
    
    
    private ObservableList<Flight> loadedFlights;
    private String to;
    private String from;
    private LocalDate date;
    @FXML
    private Button bookFlightButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flightController = new FlightController();
       // bookingController = new BookingController();
        databaseController = new DatabaseController();
        //tickeDialogController = new TicketCreationDialogController();
        
        flightController.initializeControllers(this, databaseController);
        
        initalizeComboboxes();
        initializeIndexControl();
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
        
        ObservableList<String> ob = FXCollections.observableArrayList("Reykjavík", "Akueryi", "Vestmanneyjar", "Ísafjörður", "Egilsstaðir");
        fromComboBox.setItems(ob);
        toComboBox.setItems(ob);
        
        
    }

    @FXML
    private void createTicket(ActionEvent event){
        tickeDialogController.addDialAndShow(flightListView.getItems().get(activeIndex));
    }
        /**
     * Frumstillir listann
     */
    private void initializeIndexControl() {
        MultipleSelectionModel<Flight> lsm = flightListView.getSelectionModel();
        lsm.selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, 
                    Flight oldValue, Flight newValue) {
                // Indexinn í listanum.             
                activeIndex = lsm.getSelectedIndex();
            }
        });
    }
    
    
    
    
}

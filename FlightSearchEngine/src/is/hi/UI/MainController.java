/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.Core.*;
import is.hi.UI.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * The main controller for the project
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
    private ListView<Flight> flightListView;
    @FXML
    private Button searchButton;
    
    private FlightController flightController;
    private DatabaseController databaseController;
    private BookingController bookingController;
    @FXML
    private TicketCreationDialogController tickeDialogController; // Þarf alltaf að vera @FXML fyrir ofan þetta
    
    // Þessar þrjár línur eru í vinnslu
    private String [] airports = {"Reykjavík", "Akueryi", "Vestmanneyjar", "Ísafjörður", "Egilsstaðir"};
    private ArrayList<String> listOfAirports = new ArrayList<String>();
    private ObservableList<String> ob = FXCollections.observableArrayList();
    private ArrayList<Ticket> cart;
    
    int activeIndex = -1;
    int cartIndex = -1;
    
    
    private ObservableList<Flight> loadedFlights;
    private String to;
    private String from;
    private LocalDate date;
    @FXML
    private Button bookFlightButton;
    @FXML
    private ListView<Ticket> cartListView;
    @FXML
    private Button cancelCartButton;
    @FXML
    private Button confirmCartButton;
    @FXML
    private Button deleteTicketButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        try {
            databaseController = new DatabaseController();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        flightController = new FlightController(databaseController);
        bookingController = new BookingController(databaseController);
        //tickeDialogController = new TicketCreationDialogController();
        
        tickeDialogController.initializeControllers(this, bookingController);
        
        initalizeComboboxes();
        initializeIndexControl();
        initializeIndexControlCart();
    }  

    @FXML
    private void searchHandler(ActionEvent event) {
        from = fromComboBox.getValue();
        to = toComboBox.getValue();
        date = datePicker.getValue();
        
        try {
            loadedFlights = FXCollections.observableArrayList(flightController.searchForFlight(from, to, date));
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        flightListView.setItems(loadedFlights);    
    }

    private void initalizeComboboxes() {
        for(String s: airports)
        {
            ob.add(s);
        }
        
        ObservableList<String> ob = FXCollections.observableArrayList("Reykjavík", "Akureyri", "Vestmannaeyjar", "Ísafjörður", "Egilsstaðir");
        fromComboBox.setItems(ob);
        toComboBox.setItems(ob);
        
        
    }

    @FXML
    private void createTicket(ActionEvent event){
        Flight f = flightListView.getItems().get(activeIndex);
        tickeDialogController.numberOfTickets(f);
        //tickeDialogController.addDialAndShow(i);
        //tickeDialogController.addDialAndShow(flightListView.getItems().get(activeIndex));
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
    
    private void initializeIndexControlCart() {
        MultipleSelectionModel<Ticket> lsmC = cartListView.getSelectionModel();
        lsmC.selectedItemProperty().addListener(new ChangeListener<Ticket>() {
            @Override
            public void changed(ObservableValue<? extends Ticket> observable, 
                    Ticket oldValue, Ticket newValue) {
                // Indexinn í listanum.             
                cartIndex = lsmC.getSelectedIndex();
            }
        });
    }

    void updateCart() {
        cart = bookingController.getBooking().getTickets();
        cartListView.setItems(FXCollections.observableArrayList(cart));
        
    }

    @FXML
    private void cancelCartButtonHandler(ActionEvent event) {
        cart = new ArrayList<Ticket>();
        cartListView.setItems(FXCollections.observableArrayList(cart));
        
        bookingController.resetBooking();
//        bookingController = new BookingController(databaseController);
//        tickeDialogController.initBookingController(bookingController);
        try {
            // Uppfæri listann af flugum sem leitin sýni svo sætin séu rétt
            loadedFlights = FXCollections.observableArrayList(flightController.searchForFlight(from, to, date));
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        flightListView.setItems(loadedFlights);  
        
    }

    @FXML
    private void confirmCartButtonHandler(ActionEvent event) {
        try {
            bookingController.confirmBooking();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cart = new ArrayList<Ticket>();
        cartListView.setItems(FXCollections.observableArrayList(cart));
        
    }

    @FXML
    private void deleteTicketHandler(ActionEvent event) {
        Ticket ticket = cartListView.getItems().get(cartIndex);
        cartListView.getItems().remove(ticket);
        bookingController.deleteTicket(ticket);
        updateCart();
        
        try {
            loadedFlights = FXCollections.observableArrayList(flightController.searchForFlight(from, to, date));
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        flightListView.setItems(loadedFlights); 
        
    }
    
    
    
    
}

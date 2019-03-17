/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.Core.BookingController;
import is.hi.Core.Flight;
import is.hi.Core.Passenger;
import is.hi.Core.SeatCoder;
import is.hi.Core.Ticket;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * FXML Controller class
 *
 * @author dantg
 */
public class TicketCreationDialogController implements Initializable {

    @FXML
    private AnchorPane ticketDialog;
    @FXML
    private TextField fNumberTextField;
    @FXML
    private TextField airlineTextField;
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox<Integer> numberOfticketsComboBox;
    @FXML
    private DialogPane numberOfTicketsDialog;
    
    @FXML
    private GridPane ticketGridPane;
    @FXML
    private MainController mainController;
    private BookingController bookingController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initPane(Flight f, int numTickets){
        SeatCoder seatCoder = new SeatCoder();
        seatCoder.setFlight(f);
        //ticketGridPane.getChildren().remove(0);
        
        
        for(int i = 0; i<numTickets;i++)
        {
//         if(i != numTickets - 1)
//            ticketGridPane.addColumn(0, new TextField());
         
         ObservableList<String> ob = FXCollections.observableArrayList(seatCoder.getAvailableSeats());
         ticketGridPane.addRow(i, new TextField(), new TextField(), new ComboBox<String>(ob));
         /*
         ticketGridPane.add(new TextField(), 0,i);
         ticketGridPane.add(new TextField(), 1,i);
         
         ticketGridPane.add(new ComboBox<String>(ob), 2, i);
         */
         
         
         
        }
    }
    
    public void numberOfTickets(Flight f){
        DialogPane p = new DialogPane();
        numberOfTicketsDialog.setVisible(true);
        p.setContent(numberOfTicketsDialog);
        Dialog<ButtonType> d = new Dialog();
        d.setDialogPane(p);
        SeatCoder seatCoder = new SeatCoder();
        seatCoder.setFlight(f);
        ObservableList<Integer> ob = FXCollections.observableArrayList();
        for(int i = 1; i<=seatCoder.getAvailableSeats().size(); i++)
        {
            ob.add(i);
        }
        numberOfticketsComboBox.setItems(ob);

        ButtonType next = new ButtonType("Next", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(next);       
        
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        
        Optional<ButtonType> result = d.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            confirmNumberOfTickets(f);
        }
        //nextButton = d.showAndWait();
        
        
       // return 1;
    }

    public void addDialAndShow(Flight flight, int numTickets){
        initPane(flight, numTickets);
        DialogPane p = new DialogPane();
        ticketDialog.setVisible(true);
        p.setContent(ticketDialog);
        Dialog<ButtonType> d = new Dialog();
        d.setDialogPane(p);
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        ButtonType finish = new ButtonType("Finish", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(finish);   
        
        
        fNumberTextField.setText(flight.getfNumber());
        airlineTextField.setText(flight.getAirline());
        fromTextField.setText(flight.getFrom());
        toTextField.setText(flight.getTo());
        dateTextField.setText(flight.getDate().toString());
        timeTextField.setText(flight.getTime().toString());
        priceTextField.setText(""+flight.getPrice());
        
                
        Optional<ButtonType> result = d.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            ObservableList<Ticket> ob = FXCollections.observableArrayList();
            for(int i = 0; i<numTickets;i++)
            {
                
                String name = ((TextField) (ticketGridPane.getChildren().get(i*3 +0))).getText();
                String ss = ((TextField) (ticketGridPane.getChildren().get(i*3 +1))).getText();
                Passenger passenger = new Passenger(name, ss);
                String seat = ((ComboBox<String>) (ticketGridPane.getChildren().get(i*3 +2))).getValue();
                System.out.println(name + " " + ss + " " + seat);
                
                String bNumber = bookingController.getBookingNumber();
                Ticket ticket = new Ticket(bNumber, seat, passenger, flight);
                bookingController.addTicketToBooking(ticket);
            }
            while (ticketGridPane.getChildren().size() > 0) {
            ticketGridPane.getChildren().remove(0);
            }
            while(ticketGridPane.getRowConstraints().size() > 0){
                ticketGridPane.getRowConstraints().remove(0);
            }

            while(ticketGridPane.getColumnConstraints().size() > 0){
                ticketGridPane.getColumnConstraints().remove(0);
            }
            /*
            for(Node c : ticketGridPane.getChildren())
            {
                ticketGridPane.getChildren().remove(c);
            }*/
            mainController.updateCart();
        }
        
        //System.out.println("Hello");
        
    }
    
    private void confirmNumberOfTickets(Flight f) {
        int i = numberOfticketsComboBox.getValue();
        numberOfTicketsDialog.setVisible(false);
        
        
        addDialAndShow(f, i);
    }

    void initializeControllers(MainController main, BookingController b) {
        mainController = main;
        bookingController = b;
    }
    
    void initBookingController(BookingController b){
        bookingController = b;
    }
    
}

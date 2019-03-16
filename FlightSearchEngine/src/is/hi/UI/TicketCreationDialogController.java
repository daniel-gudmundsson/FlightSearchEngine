/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.Core.Flight;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initPane(int numTickets){
        
        System.out.println("Hello");
        for(int i = 0; i<numTickets;i++)
        {
         if(i != numTickets - 1)
            ticketGridPane.addColumn(0, new TextField());
         
         ticketGridPane.add(new TextField(), 0,i);
         ticketGridPane.add(new TextField(), 1,i);
         ticketGridPane.add(new ComboBox<String>(), 2, i);
         
         
         
        }
    }
    
    public void numberOfTickets(Flight f){
        DialogPane p = new DialogPane();
        numberOfTicketsDialog.setVisible(true);
        p.setContent(numberOfTicketsDialog);
        Dialog<ButtonType> d = new Dialog();
        d.setDialogPane(p);
        ObservableList<Integer> ob = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9);
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
        initPane(numTickets);
        DialogPane p = new DialogPane();
        ticketDialog.setVisible(true);
        p.setContent(ticketDialog);
        Dialog<ButtonType> d = new Dialog();
        d.setDialogPane(p);
        ButtonType cancel = new ButtonType("cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        
        
        fNumberTextField.setText(flight.getfNumber());
        airlineTextField.setText(flight.getAirline());
        fromTextField.setText(flight.getFrom());
        toTextField.setText(flight.getTo());
        dateTextField.setText(flight.getDate().toString());
        timeTextField.setText(flight.getTime().toString());
        priceTextField.setText(""+flight.getPrice());
        
                
        d.showAndWait();
        //System.out.println("Hello");
        
    }
    
    private void confirmNumberOfTickets(Flight f) {
        int i = numberOfticketsComboBox.getValue();
        numberOfTicketsDialog.setVisible(false);
        
        
        addDialAndShow(f, i);
    }
    /*
    @FXML
    private void confirmNumberOfTickets(ActionEvent event) {
        int i = numberOfticketsComboBox.getValue();
        numberOfTicketsDialog.setVisible(false);
        
        addDialAndShow(i);
    }
    */
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;


import is.hi.UI.MainController;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;

/**
 *
 * @author dantg
 */
public class FlightController {
    
    private ArrayList<Flight> loadedFlights;
    private ArrayList<Flight> filteredFlights;
    private DatabaseController db;

    public FlightController(DatabaseController db) {
        this.db = db;
    }
    
    
    @FXML
    private MainController mainController;

      public void initializeControllers(MainController main, DatabaseController db) {
          this.db = db;
          mainController = main;
    }
      
      /**
       * Search for flight in the database with given from, to location and date.
       * Will return list of all flights with the argument passed in.
       * @param from location flight will be flying from.
       * @param to location flight will be flying to.
       * @param date of flight
       * @return list of all flights with argument given.
       * @throws SQLException
       * @throws IllegalArgumentException 
       */
    public ArrayList<Flight> searchForFlight(String from, String to, LocalDate date) throws SQLException, IllegalArgumentException{
        if (from == null || to == null || date == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        loadedFlights = db.getFlights(from, to, date);
        filteredFlights = new ArrayList<Flight>(loadedFlights); // Afrita loadedFlights
        
        
        return loadedFlights;
    }
    
    // Í vinnslu
    public ArrayList<Flight> filterByPrice(int price){
        for(Flight f: filteredFlights)
        {
            if (f.getPrice() > price)
            {
                filteredFlights.remove(f);
            }
        }
        System.out.println("Filter by price");
        //mainController.setFilteredFlights(filteredFlights);
        return filteredFlights;
        
    }
    
    public void filterByTime(String time){
    }
    
    public void filterByAirline(String airline){
    }
    
    public void resetFilters(){
        
    }
    
    
    public ArrayList<Flight> getLoadedFlights() {
        return loadedFlights;
    }

    public void setLoadedFlights(ArrayList<Flight> loadedFlights) {
        this.loadedFlights = loadedFlights;
    }

    public ArrayList<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public void setFilteredFlights(ArrayList<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }

    public DatabaseController getDb() {
        return db;
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }

  
    
    
    
    
}

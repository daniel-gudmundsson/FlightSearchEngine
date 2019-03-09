/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author dantg
 */
public class FlightController {
    
    private ArrayList<Flight> loadedFlights;
    private ArrayList<Flight> filteredFlights;
    private DatabaseController db;

      public void initializeControllers(DatabaseController db) {
          this.db = db;
    }
      
    public ArrayList<Flight> searchForFlight(String from, String to, LocalDate date){
        loadedFlights = db.getFlights(from, to, date);
        
        return loadedFlights;
    }
    
    public void filterByPrice(int price){
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

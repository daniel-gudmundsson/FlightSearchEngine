/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A flight controller class for searching for a flight
 * @author dantg
 */
public class FlightController {
    
    private ArrayList<Flight> loadedFlights; // The flights that the search returned
    private ArrayList<Flight> filteredFlights; // Contains the flights that have been filtered 
    private DatabaseController db; // Connection to the databes

    /**
     * Creates a new flight controller with a connection to the database
     * @param db 
     */
    public FlightController(DatabaseController db) {
        this.db = db;
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
        if (from == null || to == null || date == null || date.compareTo(LocalDate.of(2019, 01, 01)) < 0){
            throw new IllegalArgumentException("Argument cannot be null or illegal date");
        }
        loadedFlights = db.getFlights(from, to, date);
        filteredFlights = new ArrayList<>(loadedFlights); // Afrita loadedFlights
        
        
        return loadedFlights;
    }
    
    // Í vinnslu
    private ArrayList<Flight> filterByPrice(int price){
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
    
    private void filterByTime(String time){
    }
    
    private void filterByAirline(String airline){
    }
    
    private void resetFilters(){
        
    }
    
    
    private ArrayList<Flight> getLoadedFlights() {
        return loadedFlights;
    }

    private void setLoadedFlights(ArrayList<Flight> loadedFlights) {
        this.loadedFlights = loadedFlights;
    }

    private ArrayList<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    private void setFilteredFlights(ArrayList<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }

    public DatabaseController getDb() {
        return db;
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }

  
    
    
    
    
}

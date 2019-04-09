/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.time.*;
import java.util.Objects;

/**
 * Class for a flight
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Flight{
    
    private String fNumber; // Flight number
    private String airline; // Airline
    private String from; // From (departure)
    private String to; // To (destination)
    private LocalDate date; // Date of the flight
    private LocalTime time; // Time of the flight
    private int price; // Price of the flight
    private String seats; // Seats of the flight

    /**
     * Creates a new flight
     * @param fNumber
     * @param airline
     * @param from
     * @param to
     * @param date
     * @param time
     * @param price
     * @param seats 
     */
    public Flight(String fNumber, String airline, String from, String to, LocalDate date, LocalTime time, int price, String seats) {
        this.fNumber = fNumber;
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = seats;
    }

    /**
     * Returns the flight number
     * @return fNumber
     */
    public String getfNumber() {
        return fNumber;
    }
    
    /**
     * Returns the airline
     * @return airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Returns from
     * @return from
     */
    public String getFrom() {
        return from;
    }
    
    /**
     * Returns the destination
     * @return to
     */
    public String getTo() {
        return to;
    }
    
    /**
     * Returns the date of the flight
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Returns the time of the flight
     * @return time
     */
    public LocalTime getTime() {
        return time;
    }
    
    /**
     * Returns the price of the flight
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the seats of the flight
     * @return seats
     */
    public String getSeats() {
        return seats;
    }

    /**
     * Sets the seats of the flight
     * @param seats 
     */
    void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Equals method for flights
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.fNumber, other.fNumber)) {
            return false;
        }
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    
    /**
     * Retuns a copy of the flight
     * @return 
     */
    public Flight getCopy(){
        Flight flight;
        flight = new Flight(this.fNumber, this.airline, this.from, this.to, this.date, this.time, this.price, this.seats);
        return flight;
    }
    
    
    /**
     * To string method
     * @return 
     */
    @Override
    public String toString() {
        if (airline.equals("Falcon"))
        {
            return fNumber + "       " + airline + "     " + from + "        " + to + "      " + date + "        " + time + "        " + price;
        }     
        return fNumber + "      " + airline + "     " + from + "        " + to + "      " + date + "        " + time + "        " + price;
    } 
}

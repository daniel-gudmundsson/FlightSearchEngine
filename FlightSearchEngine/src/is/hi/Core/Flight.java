/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.time.*;
import java.util.Objects;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Flight{
    
    private String fNumber;
    private String airline;
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime time;
    private int price;
    private String seats;

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

    public String getfNumber() {
        return fNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

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

    
    
    public Flight getCopy(){
        Flight flight;
        flight = new Flight(this.fNumber, this.airline, this.from, this.to, this.date, this.time, this.price, this.seats);
        return flight;
    }
    
    
    
    @Override
    public String toString() {
        return fNumber + "      " + airline + "     " + from + "        " + to + "      " + date + "        " + time + "        " + price;
    }
    
    
    
    
}

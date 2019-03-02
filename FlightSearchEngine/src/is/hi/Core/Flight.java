/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.time.*;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Flight {
    
    private String fNumber;
    private String airline;
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime time;
    private int price;
    private boolean[] seats;

    public Flight(String fNumber, String airline, String from, String to, LocalDate date, LocalTime time, int price, boolean[] seats) {
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

    public boolean[] getSeats() {
        return seats;
    }

    
    
    
    
    
    
    @Override
    public String toString() {
        return "Flight{" + "fNumber=" + fNumber + ", airline=" + airline + ", from=" + from + ", to=" + to + ", date=" + date + ", time=" + time + ", price=" + price + ", seats=" + seats + '}';
    }
    
    
    
    
}

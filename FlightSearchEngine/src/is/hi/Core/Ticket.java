/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

/**
 *
 * @author dantg
 */
public class Ticket {
    
    private String bookingNumber;
    private String seat;
    private Passenger passenger;
    private Flight flight;
    
    public Ticket(String bookingNumber, String seat, Passenger passenger, Flight flight){
        this.bookingNumber = bookingNumber;
        this.seat = seat;
        this.passenger = passenger;
        this.flight = flight;
    }
    
    
    
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    
    
    

    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getSeat() {
        return seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Booking {
    
    String bookingNumber;
    String seat;
    Flight flight;
    Passenger passenger;

    public Booking(String bookingNumber, String seat, Flight flight, Passenger passenger) {
        this.bookingNumber = bookingNumber;
        this.seat = seat;
        this.flight = flight;
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingNumber=" + bookingNumber + ", seat=" + seat + ", flight=" + flight + ", passenger=" + passenger + '}';
    }
    
    
    
    
}

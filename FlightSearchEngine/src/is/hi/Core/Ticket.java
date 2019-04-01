/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

/**
 * Class for tickets
 * @author dantg
 */
public class Ticket {
    
    private String bookingNumber; // The booking number of the booking that contains the ticket
    private String seat; // Seat of the ticket
    private Passenger passenger; // The passenger of the ticket
    private Flight flight; // The flight the ticket is for
    
    /**
     * Creates a new ticket
     * @param bookingNumber
     * @param seat
     * @param passenger
     * @param flight 
     */
    public Ticket(String bookingNumber, String seat, Passenger passenger, Flight flight){
        this.bookingNumber = bookingNumber;
        this.seat = seat;
        this.passenger = passenger;
        this.flight = flight;
    }
    
    
    /**
     * Sets the booking number of the ticket
     * @param bookingNumber 
     */
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    /**
     * Sets the seat of the ticket
     * @param seat 
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

    /**
     * Sets the passenger of the ticket
     * @param passenger 
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Sets the flight of the ticket
     * @param flight 
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    /**
     * Returns the booking number of the ticket
     * @return bookingNumber
     */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Returns the seat of the ticket
     * @return seat
     */
    public String getSeat() {
        return seat;
    }

    /**
     * Returns the passenger of the ticket
     * @return passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Returns the flight of the ticket
     * @return flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * To string method
     * @return 
     */
    @Override
    public String toString() {
        return bookingNumber + "    " + flight.getfNumber() + "     " + passenger.getName() + "   " + passenger.getKt() + "     " + flight.getFrom() + "     " + flight.getTo() + "     " + flight.getDate().toString();
    }
}

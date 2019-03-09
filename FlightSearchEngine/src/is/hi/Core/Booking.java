/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.util.ArrayList;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Booking {
    
   private String bookingNumber;
   private ArrayList<Ticket> tickets;

    public Booking(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    /*
    @Override
    public String toString() {
        return "Booking{" + "bookingNumber=" + bookingNumber + ", seat=" + seat + ", flight=" + flight + ", passenger=" + passenger + '}';
    }
    */

    public String getBookingNumber() {
        return bookingNumber;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    
    
    
}

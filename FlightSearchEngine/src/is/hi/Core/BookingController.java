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
public class BookingController {
    
    private Booking booking;
    private DatabaseController db;
    
    // Ætti þessi að vera static eða ekki?????
    public static void cancel(Booking booking){
        // TO DO
    }
    
    // Static eða ekki???
    public static boolean bookFlight(Booking booking){
        // To do
        return false;
    }
    

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public DatabaseController getDb() {
        return db;
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }
    
    
    
}

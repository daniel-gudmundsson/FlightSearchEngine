/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class BookingControllerTest {
    
    DatabaseController db;
    BookingController bookingController;
    String bookingNumber = "";
    
    @Before
    public void setUp() {
        db = new DatabaseController();
        bookingController = new BookingController(db);
    }
    
    @After
    public void tearDown() {
        if(!bookingNumber.equals("")){
                 bookingController.cancelBooking(bookingNumber);   
        }
        db = null;
        bookingController = null;
    }
    
    @Test
    public void testBook(){
        System.out.println("Booking");
        Passenger passenger = new Passenger("Boris Fritz Stephanovich", "0105131337");
        String from = "Reykjavík";
        String to = "Akureyri";
        String airline = "Byteair";
        String fnumber = "BRA766";
        int price = 12345;
        LocalDate date = LocalDate.of(2019, 01, 01);
        LocalTime time = LocalTime.parse("08:00");
        String seat = "3a";
        bookingNumber = bookingController.getBookingNumber();
        Flight flight = new Flight(fnumber, airline, from, to, date, time, price, "00000000000000000000");
        Ticket ticket = new Ticket(bookingNumber, "3a", passenger, flight);
        
        bookingController.addTicketToBooking(ticket);
        bookingController.confirmBooking();
        
        Booking booking = bookingController.getBookingFromDB(bookingNumber);
        
        assertNotNull(booking);
        
        
        
    }

    
    
}

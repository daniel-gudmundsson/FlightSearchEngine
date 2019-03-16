/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author dantg
 */
public class BookingController {
    
    private Booking booking;
    private DatabaseController db;
    //Flights with unaffacted seats, this will be used if we need to reset booking.
    private ArrayList<Flight> unaffectedFlights;
    //All flights that are active in tickets.
    private ArrayList<Flight> activeFlights;
    private SeatCoder seatcoder = new SeatCoder();

    /**
     * Initialises the BookingController and establish a connection to the database.
     * Creates an active booking with new booking number.
     * @param db DatabaseController to use with the BookingController.
     */
    public BookingController(DatabaseController db) {
        this.db = db;
        booking = new Booking(bookingNumberGen());
        unaffectedFlights = new ArrayList<>();
        activeFlights = new ArrayList<>();
    }
    
    /**
     * Generates a random integer between 0 and 9999999.
     * @return Random interger.
     */
    private String bookingNumberGen(){
        return  Integer.toString((int) (Math.random() * 10000000));
    }
    
    /**
     * Resets all formally made booking actions, all tickets will be lost.
     * Creates a new active booking and a new booking number for that particular booking.
     * Does not delete booking from the database.
     */
    public void resetBooking(){
        for(Ticket i: booking.getTickets()){
            if(unaffectedFlights.contains(i.getFlight())){
                i.getFlight().setSeats(unaffectedFlights.get(unaffectedFlights.indexOf(i.getFlight())).getSeats());
            }
        }
        unaffectedFlights.clear();
        activeFlights.clear();
        booking = new Booking(bookingNumberGen());
    }
    
    /**
     * Adds a ticket to active booking.
     * @param ticket To be added to the active booking.
     */
    public void addTicketToBooking(Ticket ticket){
        if(!unaffectedFlights.contains(ticket.getFlight())){
            unaffectedFlights.add(ticket.getFlight().getCopy());
        }
        
        if(activeFlights.contains(ticket.getFlight())){
            ticket.setFlight(activeFlights.get(activeFlights.indexOf(ticket.getFlight())));
        }else{
            activeFlights.add(ticket.getFlight());
        }
        
        //Updates seats in flight in ticket
        seatcoder.setFlight(ticket.getFlight());
        seatcoder.reserveSeat(ticket.getSeat());
        
        //Add ticket to booking
        ArrayList<Ticket> tickets = booking.getTickets();
        tickets.add(ticket);
        booking.setTickets(tickets);
        
         
    }
    
    /**
     * Returns the booking number for the active booking.
     * @return booking number of active booking.
     */
    public String getBookingNumber(){
        return booking.getBookingNumber();
    }
    
    /**
     * Removes booking from the database with a specific booking number.
     * Return true if booking
     * @param bookingNumber
     * @return 
     */
    public boolean cancelBooking(String bookingNumber){
        return db.deleteBooking(bookingNumber);
    }
    
    public boolean confirmBooking(){
        return db.bookBooking(this.booking);
    }
    
    public Booking getBookingFromDB(String bookingNumber){
        return db.getBooking(bookingNumber);
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
    
    public static void main( String[] args ){
        DatabaseController db = new DatabaseController();
        ArrayList<Flight> list = db.getFlights("Reykjavík", "Akureyri" ,LocalDate.of(2019, 01, 01));
        Flight flight1 = list.get(2);
        BookingController BC = new BookingController(db);
        Ticket midi1 = new Ticket(BC.getBookingNumber(), "1a", new Passenger("Agnar Petursson", "3004972929"), flight1);
        Ticket midi2 = new Ticket(BC.getBookingNumber(), "2a", new Passenger("Jon Jonsson", "2901952929"), flight1);
        
        BC.addTicketToBooking(midi1);
        BC.addTicketToBooking(midi2);
       // BC.confirmBooking();
        
        BC.resetBooking();
        list = db.getFlights("Reykjavík", "Akureyri" ,LocalDate.of(2019, 01, 01));
        flight1 = list.get(2);
        Ticket midi3 = new Ticket(BC.getBookingNumber(), "3a", new Passenger("Magnus Jon", "2904972929"), flight1);
        Ticket midi4 = new Ticket(BC.getBookingNumber(), "3b", new Passenger("Agnes Magnusdottir", "0101942929"), flight1);
        
        BC.addTicketToBooking(midi3);
        BC.addTicketToBooking(midi4);
        
        //BC.confirmBooking();
        
    }
    
}

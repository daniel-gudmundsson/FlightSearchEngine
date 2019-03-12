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

    public BookingController(DatabaseController db) {
        this.db = db;
        booking = new Booking(bookingNumberGen());
        unaffectedFlights = new ArrayList<>();
        activeFlights = new ArrayList<>();
    }
    
    
    private String bookingNumberGen(){
        return  String.valueOf((int) Math.random()*10000000);
    }
    
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
    
    public String getBookingNumber(){
        return booking.getBookingNumber();
    }
    
    public boolean cancelBooking(Booking booking){
        return db.deleteBooking(booking.getBookingNumber());
    }
    
    public boolean confirmBooking(){
        return db.bookBooking(this.booking);
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
        BookingController BC = new BookingController(null);
        Ticket midi1 = new Ticket(BC.getBookingNumber(), "1a", new Passenger("Agnar Petursson", "3004972929"), new Flight("FN123", "Icelandair", "RK", "KV", LocalDate.MAX, LocalTime.MIN, 1999, "00000000000000000000"));
        Ticket midi2 = new Ticket(BC.getBookingNumber(), "2a", new Passenger("Jon Jonsson", "2901952929"), new Flight("FN123", "Icelandair", "RK", "KV", LocalDate.MAX, LocalTime.MIN, 1999, "001212312314"));
        System.out.println(System.identityHashCode(midi1.getFlight()));
        System.out.println(System.identityHashCode(midi2.getFlight()));
        
        
        BC.addTicketToBooking(midi1);
        System.out.println(midi1.getFlight().getSeats());
        BC.addTicketToBooking(midi2);
        System.out.println(midi2.getFlight().getSeats());
        
        BC.resetBooking();
        System.out.println(midi1.getFlight().getSeats());
        System.out.println(midi2.getFlight().getSeats());
        
        
        
    }
    
}

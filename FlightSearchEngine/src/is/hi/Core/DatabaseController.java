/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class DatabaseController{
    private Connection conn;
    PreparedStatement pstmt;
    
    private String SQL_URL = "jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2276561";
    private String SQL_USER = "sql2276561";
    private String SQL_PASSWORD = "qF3!zE5!";
    
    private String SQL_GETFLIGHT = "SELECT * FROM Flight WHERE fFrom = ? and fTo = ? and date = ? ";
    private String SQL_GETBOOKING = "SELECT * FROM Booking WHERE Bookings = ?";
    private String SQL_INSERTPASSENGER = "INSERT IGNORE INTO Passenger (kt, name, numberOfTickets) VALUES (?, ?, ?)";
    private String SQL_INSERTTICKET = "INSERT INTO Ticket (bookingNumber, kt, seat, fnumber, date, time, fFrom) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String SQL_INSERTBOOKING = "INSERT INTO Booking (bookingNumber, tickets) VALUES (?, ?)";
    private String SQL_INCREMENTTICKETCOUNT = "UPDATE Passenger SET numberOfTickets = numberOfTickets + 1 WHERE kt = ?";
    private String SQL_UPDATESEATS = "UPDATE Flight SET seats = ? where fNumber = ? and fFrom = ? and date = ? and time = ?";

                                  
    
    /**
     * Establish a connection to the database
     */
    public DatabaseController() {
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(SQL_URL,SQL_USER,SQL_PASSWORD);
            conn.setAutoCommit(false);
        }catch (Exception e){
            System.err.print("Error establishing a database connection");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Gets all flights from the database with a specific date.
     * @param date specific date.
     * @return List of all flights with a specific date.
     */
    public ArrayList<Flight> getFlights(String from, String to, LocalDate date){
        ArrayList<Flight> list = new ArrayList<Flight>();
        Flight flight;
        try {
            pstmt = conn.prepareStatement(SQL_GETFLIGHT);
            
            pstmt.clearParameters();
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setString(3, date.toString());
            
            ResultSet rs = pstmt.executeQuery();
            conn.commit();
            while(rs.next() ){
                String fNumber = rs.getString(1);
                String airline = rs.getString(2);
                String ffrom = rs.getString(3);
                String fto = rs.getString(4);
                LocalDate fdate = LocalDate.parse(rs.getString(5));
                LocalTime time = LocalTime.parse(rs.getString(6));
                int price = Integer.parseInt(rs.getString(7));
                String seats = rs.getString(8);
                
                flight = new Flight(fNumber, airline, ffrom, fto, fdate, time, price, seats);
                list.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Finds and return booking from database with a specific booking number.
     * @param bookingNumber Booking number to look up in the database.
     * @return booking with bookingNumber. Returns null if booking with bookingNumber does not exist.
     */
    /*
    public Booking getBooking(String bookingNumber){
        Booking booking = null;
        Passenger passenger;
        Flight flight;
        try {
            pstmt = conn.prepareStatement(SQL_GETBOOKING);
            
            pstmt.clearParameters();
            pstmt.setString(1, bookingNumber);  
            ResultSet rs = pstmt.executeQuery();
            conn.commit();
            
            while(rs.next() ){
                String bookingNumber = rs.getString(1);
                booking = new Booking(bookingNumber,null);
//                String name = rs.getString(2);
//                String fnumber = rs.getString(3);
//                String airline = rs.getString(4);
//                String ffrom = rs.getString(5);
//                String fto = rs.getString(6);
//                LocalDate date = LocalDate.parse(rs.getString(7));
//                LocalTime time = LocalTime.parse(rs.getString(8));
//                int price = Integer.parseInt(rs.getString(9));
//                String seat = rs.getString(10);
//                String kt = rs.getString(11);
                
                passenger = new Passenger(name, kt);
                flight = new Flight(fnumber, airline, ffrom, fto, date, time, price, seat);
                //booking = new Booking(bookingNumber, seat, flight, passenger);  /// Þarf að laga hér !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return booking;
    }
    */
    

    /**
     * Insert booking into the database.
     * @param booking booking to put into the database.
     * @return 
     */
    public boolean bookBooking(Booking booking){
        //For booking
        String bookingNumber; // Booking and Ticket
        int tickets;
        
        //For Passenger
        String name;
        String kt; //Passenger and Ticket
        
        //For Ticket
        int id;
        String seat;
        String fnumber;
        LocalDate date;
        LocalTime time;
        String fFrom;
        
        SeatCoder seatCoder = new SeatCoder();

        try{
            //Inserting into Booking
            pstmt = conn.prepareStatement(SQL_INSERTBOOKING);
            bookingNumber = booking.getBookingNumber();
            tickets = booking.getTickets().size();
            pstmt.clearParameters();
            pstmt.setString(1, bookingNumber);
            pstmt.setString(2, Integer.toString(tickets));
            pstmt.execute();
            
            
            
            
            //Inserting into Passenger and ticket
            for (Ticket i: booking.getTickets()){
                
                
                //Inserting into passenger if the passenger does not exists
                pstmt = conn.prepareStatement(SQL_INSERTPASSENGER);
                name = i.getPassenger().name;
                kt = i.getPassenger().kt;
                pstmt.clearParameters();
                pstmt.setString(1, kt);
                pstmt.setString(2, name);
                pstmt.setString(3, Integer.toString(0));
                pstmt.execute();
                
                //Increment numberOfTickets by 1
                pstmt = conn.prepareStatement(SQL_INCREMENTTICKETCOUNT);
                pstmt.clearParameters();
                pstmt.setString(1, kt);
                pstmt.execute();
                
                //Inserting Ticket for passenger
                pstmt = conn.prepareStatement(SQL_INSERTTICKET);
                seat = i.getSeat();
                fnumber = i.getFlight().getfNumber();
                date = i.getFlight().getDate();
                time = i.getFlight().getTime();
                fFrom = i.getFlight().getFrom();
                
                pstmt.clearParameters();
                pstmt.setString(1, bookingNumber);
                pstmt.setString(2, kt);
                pstmt.setString(3, seat);
                pstmt.setString(4, fnumber);
                pstmt.setString(5, date.toString());
                pstmt.setString(6, time.toString());
                pstmt.setString(7, fFrom);
                pstmt.execute();
                
                //Insert reserved seats into flights
                seatCoder.setseatcode(i.getFlight().getSeats());
                System.out.println(seatCoder.getSeatcode());
                seatCoder.reserveSeat(seat);
                i.getFlight().setSeats(seatCoder.getSeatcode());
                System.out.println(seatCoder.getSeatcode());
                pstmt = conn.prepareStatement(SQL_UPDATESEATS);
                pstmt.clearParameters();
                pstmt.setString(1, seatCoder.getSeatcode());
                pstmt.setString(2, fnumber);
                pstmt.setString(3, fFrom);
                pstmt.setString(4, date.toString());
                pstmt.setString(5, time.toString());
                pstmt.execute();
                
                
                
            }
        conn.commit();
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
           
    }
    
    
    /**
     * Closes the connection to the database.
     */
    public void closeConnection(){
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main( String[] args ){
        System.out.println("dad");
        DatabaseController DB = new DatabaseController();
        ArrayList<Flight> list = DB.getFlights("Reykjavík", "Akureyri" ,LocalDate.of(2019, 01, 01));
        //Booking booking = DB.getBooking("1234");
        System.out.println(list);
        //System.out.println(booking);
        Flight flight1 = list.get(2);
        //Booking booking = new Booking("4321",new Ticket[]{new Ticket("1234", "2a", new Passenger("Agnar Petursson", "3004972929"), flight1), new Ticket("1234", "3a", new Passenger("Jon Jonsson", "2008972929"), flight1)});
        //System.out.println(booking);
        //DB.bookBooking(booking);
        

        
        DB.closeConnection();
        
        
    }
    
    
    
}

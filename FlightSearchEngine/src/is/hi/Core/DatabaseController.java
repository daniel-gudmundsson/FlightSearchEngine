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
    
    private String SQL_GETFLIGHT = "SELECT * FROM Flights WHERE date = ?";
    private String SQL_GETBOOKING = "SELECT * FROM Bookings WHERE Bookings = ?";
                                  
    
    /**
     * Establish a connection to the database
     */
    public DatabaseController() {
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(SQL_URL,SQL_USER,SQL_PASSWORD);
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
    public ArrayList<Flight> getFlights(LocalDate date){
        ArrayList<Flight> list = new ArrayList<Flight>();
        Flight flight;
        try {
            pstmt = conn.prepareStatement(SQL_GETFLIGHT);
            
            pstmt.clearParameters();
            pstmt.setString(1, date.toString());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next() ){
                String fNumber = rs.getString(1);
                String airline = rs.getString(2);
                String ffrom = rs.getString(3);
                String fto = rs.getString(4);
                LocalDate fdate = LocalDate.parse(rs.getString(5));
                LocalTime time = LocalTime.parse(rs.getString(6));
                int price = Integer.parseInt(rs.getString(7));
                
                flight = new Flight(fNumber, airline, ffrom, fto, fdate, time, price, null);
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
    public Booking getBooking(String bookingNumber){
        Booking booking = null;
        Passenger passenger;
        Flight flight;
        try {
            pstmt = conn.prepareStatement(SQL_GETBOOKING);
            
            pstmt.clearParameters();
            pstmt.setString(1, bookingNumber);  
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next() ){
                String bookingN = rs.getString(1);
                String name = rs.getString(2);
                String fnumber = rs.getString(3);
                String airline = rs.getString(4);
                String ffrom = rs.getString(5);
                String fto = rs.getString(6);
                LocalDate date = LocalDate.parse(rs.getString(7));
                LocalTime time = LocalTime.parse(rs.getString(8));
                int price = Integer.parseInt(rs.getString(9));
                String seat = rs.getString(10);
                String kt = rs.getString(11);
                
                passenger = new Passenger(name, kt);
                flight = new Flight(fnumber, airline, ffrom, fto, date, time, price, null);
                //booking = new Booking(bookingNumber, seat, flight, passenger);  /// Þarf að laga hér !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return booking;
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
        DatabaseController DB = new DatabaseController();
        ArrayList<Flight> list = DB.getFlights(LocalDate.of(2019, 01, 02));
        Booking booking = DB.getBooking("1234");
        System.out.println(list);
        System.out.println(booking);
        DB.closeConnection();
        
        
    }
    
    
    
}

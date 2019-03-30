/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import is.hi.UI.MainController;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class FlightControllerTest {
    
    DatabaseController db;
    FlightController flightController;
    
    
    @Before
    public void setUp() {
        try {
            db = new DatabaseController();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlightControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FlightControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        flightController = new FlightController(db);
    }
    
    @After
    public void tearDown() {
        db = null;
        flightController = null;
    }



    /**
     * Test of searchForFlight method, of class FlightController.
     */
    @Test
    public void testRkAkSearchForFlight() {
        System.out.println("Searching for an existing flight");
        String from = "Reykjavík";
        String to = "Akureyri";
        LocalDate date = LocalDate.of(2019, 01, 01);
        try {
            ArrayList<Flight> result = flightController.searchForFlight(from, to, date);
            assertTrue(!result.isEmpty());
        } catch (SQLException ex) {
            fail("Exception should not be thrown");
        }
    }
    
    @Test
    public void testEmptySearchForFlight() {
        System.out.println("Searching for a non-existing flight");
        String from = "Narnia";
        String to = "Mordor";
        LocalDate date = LocalDate.of(2019, 01, 01);
        ArrayList<Flight> result;
        
        try {
            result = flightController.searchForFlight(from, to, date);
            assertTrue(result.isEmpty());
        } catch (SQLException ex) {
            fail("Exception should not be thrown");
        }
    }
    
    
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testNullSearchForFlight() throws IllegalArgumentException, SQLException  {
        System.out.println("Searching for a flight with all arguments as null.");
       
        String from = null;
        String to = null;
        LocalDate date = null;
        ArrayList<Flight> result = flightController.searchForFlight(from, to, date);
    }
    
    @Test
    public void test4AvailableSearchForFlight() {
        System.out.println("Searching for an existing flight and checking if 4 flights are available");
        String from = "Reykjavík";
        String to = "Akureyri";
        LocalDate date = LocalDate.of(2019, 01, 01);
        try {
            ArrayList<Flight> result = flightController.searchForFlight(from, to, date);
            assertTrue(result.size() == 4);
        } catch (SQLException ex) {
            fail("Exception should not be thrown");
        }
    }



}

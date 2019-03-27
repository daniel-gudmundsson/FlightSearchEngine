/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import is.hi.UI.MainController;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class FlightControllerTest {
    
    DatabaseController db;
    FlightController flightController;
    
    public FlightControllerTest() {
    }
    
    @Before
    public void setUp() {
        db = new DatabaseController();
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
        ArrayList<Flight> result = flightController.searchForFlight(from, to, date);
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testEmptySearchForFlight() {
        System.out.println("Searching for a non-existing flight");
        String from = "Narnia";
        String to = "Mordor";
        LocalDate date = LocalDate.of(2019, 01, 01);
        ArrayList<Flight> result = flightController.searchForFlight(from, to, date);
        assertTrue(result.isEmpty());
    }



}

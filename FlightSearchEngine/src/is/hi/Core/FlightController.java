package is.hi.Core;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A flight controller class for searching for a flight
 *
 * @author dantg
 */
public class FlightController {

    private ArrayList<Flight> loadedFlights; // The flights that the search returned
    private DatabaseController db; // Connection to the databes

    /**
     * Creates a new flight controller with a connection to the database
     *
     * @param db to use in FlightController
     */
    public FlightController(DatabaseController db) {
        this.db = db;
    }

    /**
     * Search for flight in the database with given from, to location and date. Will return list of all
     * flights with the argument passed in.
     *
     * @param from location flight will be flying from.
     * @param to location flight will be flying to.
     * @param date of flight
     * @return list of all flights with argument given.
     * @throws SQLException
     * @throws IllegalArgumentException
     */
    public ArrayList<Flight> searchForFlight(String from, String to, LocalDate date) throws SQLException, IllegalArgumentException {
        if (from == null || to == null || date == null || date.compareTo(LocalDate.of(2019, 01, 01)) < 0) {
            throw new IllegalArgumentException("Argument cannot be null or illegal date");
        }
        loadedFlights = db.getFlights(from, to, date);

        return loadedFlights;
    }

    /**
     * Gets database that FlightController is using.
     *
     * @return database that FlightController is using.
     */
    public DatabaseController getDb() {
        return db;
    }
}

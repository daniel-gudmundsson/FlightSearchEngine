package is.hi.Core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A encoder/decoder for the seats of a flight Helps us keep track of available seats and taken seats
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
class SeatCoder {

    //Name of all the seats on the plane.
    private final String SEAT[] = {"1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", "3a", "3b", "3c", "3d", "4a", "4b", "4c", "4d", "5a", "5b", "5c", "5d"};

    //Seats that are available in binary format. element number 0 is "1a" element number 1 is "1b" etc.
    //0 is available, 1 is reserved.
    private int[] availableSeatsBinary = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    //ArrayList with allt available seats, in string format.
    private ArrayList<String> availableSeats = new ArrayList<>();
    //State of seats on the active flight in binaryformat, e.g 10001000010000000011.
    private String seatcode;
    //Active flight.
    private Flight flight;

    /**
     * Set active flight. Active flight is the flight that the SeatCoder will do actions to when functions are
     * called, e.g. reserveSeat.
     *
     * @param flight to set active.
     */
    void setFlight(Flight flight) {
        this.seatcode = flight.getSeats();
        this.flight = flight;
        int n = seatcode.length();
        resetAvailableSeatBinary();
        availableSeats.clear();
        for (int i = 0; i < n; i++) {
            if ((this.seatcode.charAt(i)) == '0') {
                availableSeats.add(SEAT[i]);
                availableSeatsBinary[i] = 0;
            }
        }
    }

    /**
     * Reserve a seat of the active flight.
     *
     * @param seat to reserve.
     */
    void reserveSeat(String seat) {
        if (isAvailable(seat)) {
            availableSeats.remove(seat);
            updateSeatcode();
        } else {
            System.err.println("Seat reserved, is not available");
        }
    }

    /**
     * Cancels the reserved seat of the active flight.
     *
     * @param seat to cancel.
     */
    void cancelReservedSeat(String seat) {
        if (!isAvailable(seat)) {
            availableSeats.add(seat);
            updateSeatcode();
        } else {
            System.err.print("Seat already available");
        }
    }

    /**
     * Checks if seats is available in active flight.
     *
     * @param seat to check if is available.
     * @return true is seat is available else false.
     */
    public boolean isAvailable(String seat) {
        if (availableSeats.contains(seat)) {
            return true;
        }
        return false;
    }

    /**
     * Update the SeatCoder so availableSeatsBinary and availableSeats is in a legal state.
     */
    private void updateSeatcode() {
        int n = this.seatcode.length();
        resetAvailableSeatBinary();
        String code = "";
        for (String i : availableSeats) {
            int k = Arrays.asList(SEAT).indexOf(i);
            availableSeatsBinary[k] = 0;
        }
        for (int i = 0; i < this.seatcode.length(); i++) {
            code += availableSeatsBinary[i];
        }
        this.seatcode = code;
        flight.setSeats(this.seatcode);
    }

    /**
     * Resets the availableSeatsBinary to {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
     */
    private void resetAvailableSeatBinary() {
        availableSeatsBinary = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }

    /**
     * Returns available seats.
     *
     * @return available seats.
     */
    ArrayList<String> getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Returns active flight.
     *
     * @return active flight.
     */
    Flight getFlight() {
        return flight;
    }

    /**
     * Returns name of all seats on the plane.
     *
     * @return name of all seats on the plane.
     */
    public String[] getSeatNames() {
        return SEAT;
    }
}

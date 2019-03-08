/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class SeatCoder {

    //0 er laust, 1 er fratekid
    private String SEAT[] = {"1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", "3a", "3b", "3c", "3d", "4a", "4b", "4c", "4d", "5a", "5b", "5c", "5d"};

    private int[] availableSeatsBinary = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private ArrayList<String> availableSeats = new ArrayList<>();
    private String seatcode;


    
    public void setseatcode(String seatcode){
        this.seatcode = seatcode;
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
    
    public void reserveSeat(String seat) {
        if (isAvailable(seat)) {
            availableSeats.remove(seat);
            updateSeatcode();
        }else{
            System.err.println("Seat not reserved, is not available");
        }
    }
    
    public void cancelReservedSeat(String seat){
        if(!isAvailable(seat)){
            availableSeats.add(seat);
            updateSeatcode();   
        }else{
            System.err.print("Seat already available");
        }
    }

    public boolean isAvailable(String seat) {
        if (availableSeats.contains(seat)) {
            return true;
        }
        return false;
    }

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
    }
    
    private void resetAvailableSeatBinary(){
        availableSeatsBinary = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }

    public ArrayList<String> getAvailableSeats() {
        return availableSeats;
    }

    public String getSeatcode() {
        return seatcode;
    }

    public int[] getAvailableSeatsBinary() {
        return availableSeatsBinary;
    }
    
    
    
    
    
     public static void main( String[] args ){
         SeatCoder code = new SeatCoder();
         code.setseatcode("00000000000000000000");
         System.out.println(code.getAvailableSeats());
         System.out.println(code.getSeatcode());
         code.reserveSeat("1c");
         System.out.println(code.getAvailableSeats());
         System.out.println(code.getSeatcode());
         code.setseatcode("00000000001111111111");
         
         System.out.println(code.getAvailableSeats());
         System.out.println(code.getSeatcode());
         code.reserveSeat("1c");
         System.out.println(code.getAvailableSeats());
         System.out.println(code.getSeatcode());
         
     }
    
}

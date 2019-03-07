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

    private String SEAT[] = {"1a","1b","1c","1d","2a","2b","2c","2d","3a","3b","3c","3d","4a","4b","4c","4d","5a","5b","5c","5d"};
    
    private ArrayList<String> availableSeats;
    private String seatcode;
    
    public SeatCoder(String seatcode) {
        this.seatcode = seatcode;
        int n = seatcode.length();
        for(int i = 0; i<n; i++){
            if((int)this.seatcode.charAt(i) == 0){
                availableSeats.add(SEAT[i]);
            }
        }    
    }
    
    public void reserveSeat(String seat){
        if(isAvailable(seat)){
            availableSeats.remove(seat);
            //updateSeatcode();
        }
    }
    
    public boolean isAvailable(String seat){
        if(availableSeats.contains(seat)){
            return true;
        }
        return false;
    }
     /*
    private void updateSeatcode(){
        int n = seatcode.length();
        seatcode = "";
        for(String i: availableSeats){
            int k = Arrays.asList(SEAT).indexOf(i);
            
            }
        }
    }
 */
    

    public ArrayList<String> getAvailableSeats() {
        return availableSeats;
    }
    
}

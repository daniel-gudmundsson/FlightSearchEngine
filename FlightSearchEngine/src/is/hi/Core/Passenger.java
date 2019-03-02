/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

/**
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Passenger {
    
    String name;
    String kt;

    public Passenger(String name, String kt) {
        this.name = name;
        this.kt = kt;
    }

    @Override
    public String toString() {
        return "Passenger{" + "name=" + name + ", kt=" + kt + '}';
    }
    
    
    
}

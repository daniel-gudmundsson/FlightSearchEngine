/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.Core;

/**
 * Class for a passenger
 *
 * @author Agnar Pétursson, Háskóli Íslands, agp11@hi.is
 */
public class Passenger {

    String name; // Name of the passenger
    String kt; // Social security number of the passenger

    /**
     * Creates a new passenger
     *
     * @param name name of the passenger
     * @param kt social security number of the passenger (Kennitala)
     */
    public Passenger(String name, String kt) {
        this.name = name;
        this.kt = kt;
    }

    /**
     * To string method
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "Passenger{" + "name=" + name + ", kt=" + kt + '}';
    }

    /**
     * Returns the name of the passenger
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the social security number of the passenger
     *
     * @return social security number of the passenger
     */
    public String getKt() {
        return kt;
    }

}

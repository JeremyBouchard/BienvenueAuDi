package main;

import java.io.Serializable;

/**
 *  Enumeration that lists all the possible direction
 *  that could be taken.
 *  @author Aymane RAMACH, Michel NGATIMO, Morad SANBA
 */

public enum Direction implements Serializable
{
    None("NONE"),
    North("NORTH"),
    South("SOUTH"),
    East("EAST"),
    West("WEST");

    private final String name;

    /**
     * Constructor 
     * @param s
     */
    private Direction(String s) {
        name = s;
    }

    /**
     * 
     * @param otherName
     * @return
     */
    public boolean equals(String otherName)
    {
        return name.equals(otherName);
    }

    /**
     * surcharge on toString method
     */
    public String toString() {
        return this.name;
    }
}
package model;

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
     * @param s the name
     */
    private Direction(String s) {
        name = s;
    }

    /**
     * 
     * @param otherName to compare with
     * @return the acording boolean
     */
    public boolean equals(String otherName)
    {
        return name.equals(otherName);
    }

    /**
     * overload on toString method
     */
    public String toString() {
        return this.name;
    }
}
package projectModel;
/**
 *  Enumeration that lists all the possible direction
 *  that could be taken.
 *  @author Aymane RAMACH, Michel NGATIMO, Morad SANBA
 */

public enum Direction
{
    None("NONE"),
    North("NORTH"),
    South("SOUTH"),
    East("EAST"),
    West("WEST");

    private final String name;

    private Direction(String s) {
        name = s;
    }

    public boolean equals(String otherName)
    {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
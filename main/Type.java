package projectModel;

/**
 *  Enumeration that lists all the possible type
 *  that a room can take.
 *  @author Aymane RAMACH, Michel NGATIMO, Morad SANBA
 */

public enum Type
{
    Amphis("Amphis"),
    SallesTD("Salles TD"),
    SallesMachines("Salles machines"),
    SallesReunions("Salles reunions"),
    Orientation("Noeud d'orientation"),
    Bureaux("Bureaux"),
    SallesDiverses("Salles diverses"),
    SallesRecherche("Salles de recherche");


    /**
     * Name of the type
     */
    private final String name;

    /**
     * Constructor for the type
     * @param s The name of the type
     */
    private Type(String s) {
        name = s;
    }

    /**
     * Getter for the Type
     * @return String that contains the type
     */
    public String toString() {
        return this.name;
    }
}
package model;

import java.io.Serializable;

/**
 * Represents a point of the map
 * Current object : Information, direction to get to the next node
 * @author Aymane RAMACH, Michel NGATIMO, Morad SANBA
 */

public class Node implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3124121248466770167L;

	/**
     * List all the information about a node
     */
    private Information information;

    /**
     * The direction to take to get to the next node
     */
    private Direction directionToTake;


    /**
     * Constructor with all the attributes
     * @param information Information on the node
     * @param directionToTake Direction to take to get to the next node
     */
    public Node(Information information, Direction directionToTake) {
        this.information = information;
        this.directionToTake = directionToTake;
    }

    /**
     * Get the information of the node
     * @return Object Information that contains all the informations of the node
     */
    public Information getInformation()
    {
        return information;
    }

    /**
     * Set the information for a node
     * @param information Object Information that contain informations about the node
     */
    public void setInformation(Information information)
    {
        this.information = information;
    }

    /**
     * Get the direction of the node
     * @return The direction to take to get to the next node
     */
    public Direction getDirectionToTake()
    {
        return directionToTake;
    }

    /**
     * Set the direction of the node
     * @param directionToTake The direction to take to get to the next node
     */
    public void setDirectionToTake(Direction directionToTake)
    {
        this.directionToTake = directionToTake;
    }
}
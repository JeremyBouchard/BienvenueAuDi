package com.Projet6.Client.Model;


/**
 * The class NODE will represent a point of the building
 * it can either be a classroom, an office or a transition point
 */
public abstract class Node
{
    /**
     * Represents the ID of the node
     */
    private int idNode;
    /**
     * Represents the name of the node
     */
    private String name;


    public Node() {
    }

    /**
     * Constructor of the node
     * @param idNode the ID of the node
     * @param name the name of the node
     */
    public Node(int idNode, String name)
    {
        this.idNode = idNode;
        this.name = name;
    }

    /**
     * Obtains the ID of the node
     * @return the ID of the node
     */
    public int getIdNode() {
        return idNode;
    }

    /**
     * Set the ID of the node
     * @param idNode the new ID Node
     */
    public void setIdNode(int idNode) {
        this.idNode = idNode;
    }

    /**
     * Get the name of the node
     * @return String with the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the node
     * @param name The new name of the node
     */
    public void setName(String name) {
        this.name = name;
    }
}

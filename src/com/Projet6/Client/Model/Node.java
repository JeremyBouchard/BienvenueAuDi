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
    private int idNoeud;
    /**
     * Represents the name of the node
     */
    private int name;

    /**
     * Constructor of the node
     * @param idNoeud the ID of the node
     * @param name the name of the node
     */
    public Node(int idNoeud, int name)
    {
        this.idNoeud = idNoeud;
        this.name = name;
    }
}

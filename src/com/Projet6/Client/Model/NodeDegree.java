package com.Projet6.Client.Model;

/**
 * Represents a node that contains a certain degree
 */
public class NodeDegree extends Node
{
    /**
     * Degree indicating the direction to take
     * to get to the next node.
     */
    private double degree;

    /**
     * Constructor of the node with degree
     * @param idNoeud the ID of the node
     * @param name the name of the node
     * @param degree degree of the next node
     */
    public NodeDegree(int idNoeud, int name, double degree)
    {
        super(idNoeud, name);
        this.degree = degree;
    }
}

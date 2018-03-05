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


    public NodeDegree() {
    }

    /**
     * Constructor of the node with degree
     * @param idNode the ID of the node
     * @param name the name of the node
     * @param degree degree of the next node
     */
    public NodeDegree(int idNode, String name, double degree)
    {
        super(idNode, name);
        this.degree = degree;
    }

    /**
     * Get the degree of the node
     * @return A double that contains the degree of the node
     */
    public double getDegree() {
        return degree;
    }

    /**
     * Set the degree of the node
     * @param degree The new degree of the node
     */
    public void setDegree(double degree) {
        this.degree = degree;
    }
}

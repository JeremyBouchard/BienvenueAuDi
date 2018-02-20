package com.Projet6.Client.Model;

/**
 * Class that will format and send the data to
 * the server
 */
public abstract class DataToSend
{
    /**
     * Represents the initial node
     */
    private Node initialNode;

    /**
     * Constructor for the data to send
     * @param initialNode the initial node
     */
    public DataToSend(Node initialNode)
    {
        this.initialNode = initialNode;
    }

    /**
     * Function that sends the data to the server
     */
    abstract void send();
}

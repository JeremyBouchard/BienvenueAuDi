package com.Projet6.Client.Model;


/**
 * Class that will format and send the data to
 * the server
 */
public class DataToSendRoute extends DataToSend
{
    /**
     * Represents the destination node
     */
    private Node destinationNode;

    /**
     * Constructor of the data to send following the model: Route
     * @param initialNode The initial node
     * @param destinationNode the destination node
     */
    public DataToSendRoute(Node initialNode, Node destinationNode)
    {
        super(initialNode);
        this.destinationNode = destinationNode;
    }

    /**
     * Function that sends the data to the server
     */
    @Override
    void send() {

    }
}

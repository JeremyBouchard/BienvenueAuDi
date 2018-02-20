package com.Projet6.Client.Controller;

import com.Projet6.Client.Model.Node;

import java.util.ArrayList;


/**
 * Interface with methods that translate Node to JSON,
 * and JSON to Node
 */
public interface JSONController
{
    /**
     * Transform the given node to JSON text
     * @param nodeParam Node to transform
     * @return JSON Text representing the node
     */
    public String toJSON(Node nodeParam);

    /**
     * Transform the string to a list of nodes
     * @param JSONParam JSON Text representing a list of nodes
     * @return A list of Nodes
     */
    public ArrayList<Node> fromJSON(String JSONParam);
}

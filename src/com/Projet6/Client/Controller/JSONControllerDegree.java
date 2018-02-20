package com.Projet6.Client.Controller;

import com.Projet6.Client.Model.Node;
import java.util.ArrayList;

/**
 * Interface with methods that translate Node Degree to JSON,
 * and JSON to Node Degree
 */
public class JSONControllerDegree implements JSONController
{
    /**
     * Transform the given node to JSON text
     * @param nodeParam Node to transform
     * @return JSON Text representing the node
     */
    @Override
    public String toJSON(Node nodeParam) {
        return null;
    }

    /**
     * Transform the string to a list of nodes
     * @param JSONParam JSON Text representing a list of nodes
     * @return A list of Nodes
     */
    @Override
    public ArrayList<Node> fromJSON(String JSONParam) {
        return null;
    }
}

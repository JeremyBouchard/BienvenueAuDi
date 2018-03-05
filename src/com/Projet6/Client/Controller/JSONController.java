package com.Projet6.Client.Controller;
import com.Projet6.Client.Model.NodeDegree;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class with methods that translate Object to JSON,
 * and JSON to Object
 */
public class JSONController
{
    /**
     * Transform the given node to JSON text
     * @param objectParam Object to transform
     * @return JSON Text representing the node
     */
    public String toJSON(Object objectParam)
    {
        String jsonInString = "Error";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(objectParam);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return jsonInString;
    }

    /**
     * Transform the string to a list of nodes
     * @param JSONParam JSON Text representing an Object
     * @return A NodeDegree
     */
    public NodeDegree getNodeDegree(String JSONParam)
    {
        ObjectMapper mapper = new ObjectMapper();
        NodeDegree obj = null;
        try {
            obj = mapper.readValue(JSONParam, NodeDegree.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

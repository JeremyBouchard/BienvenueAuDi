package com.Projet6.Client.Test;
import com.Projet6.Client.Controller.JSONController;
import com.Projet6.Client.Model.DataToSendRoute;
import com.Projet6.Client.Model.NodeDegree;
import org.junit.*;
import static org.junit.Assert.*;

public class TestJSON
{
    @Test
    public void convertToJSon()
    {
        NodeDegree nodeTest = new NodeDegree(1,"Test",4.3d);
        String nodeJSON = JSONController.toJSON(nodeTest);
        System.out.println(nodeJSON);
        String expectedJSON = "{\"idNode\":1,\"name\":\"Test\",\"degree\":4.3}";
        assertEquals(expectedJSON, nodeJSON);
    }

    @Test
    public void convertToNode()
    {
        String nodeJSON = "{\"idNode\":1,\"name\":\"Test\",\"degree\":4.3}";
        NodeDegree nodeTest = JSONController.getNodeDegree(nodeJSON);
        assertEquals(1, nodeTest.getIdNode());
        assertEquals("Test", nodeTest.getName());
        assertEquals(4.3d, nodeTest.getDegree(),0.1);
    }

    @Test
    public void ArrayToJSON()
    {
        NodeDegree node1 = new NodeDegree(1,"test1",1.0d);
        NodeDegree node2 = new NodeDegree(2,"test2",2.0d);
        DataToSendRoute sendRoute = new DataToSendRoute(node1,node2);
        sendRoute.send();
    }
}

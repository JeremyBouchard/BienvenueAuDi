package model;
import java.io.IOException;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import model.Vertex;

import org.jgrapht.ext.*;



public class main {

	public static void main(String[] args) throws IOException 
	{
		
	    System.out.println("test");
			
	    DefaultDirectedWeightedGraph<Vertex,Edge>  g = new DefaultDirectedWeightedGraph<Vertex,Edge>(Edge.class);
	
	    Vertex n1 = new Vertex("Boole",Type.SallesTD);
	    Vertex n2 = new Vertex("lovelacE",Type.SallesTD);
	    Vertex n3 = new Vertex("cafet",Type.Orientation);
	    Vertex n4 = new Vertex("VON NEUMAN",Type.SallesTD);
	       
	    g.addVertex(n1);
	    g.addVertex(n2);
	    g.addVertex(n3);
	    g.addVertex(n4);   
	    
	    g.addEdge(n1, n2);
	    g.addEdge(n2, n3);
	    g.addEdge(n3, n1);
	    g.addEdge(n3, n4);
		
	    GraphMLExporter<Vertex,Edge> imp= new GraphMLExporter<Vertex,Edge>();
		try {
			imp.exportGraph(g, System.out);
		} catch (ExportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   new Thread( new ManageConnexions(g)).run();
	}

}
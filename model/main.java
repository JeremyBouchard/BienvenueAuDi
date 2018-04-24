
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
	
	    Vertex n1 = new Vertex("Bool",Type.SallesTD);
	    Vertex n2 = new Vertex("Lovelace",Type.SallesTD);
	    Vertex n3 = new Vertex("Cafet",Type.Orientation);
	       
	    g.addVertex(n1);
	    g.addVertex(n2);
	    g.addVertex(n3);
	       
	    g.addEdge(n1, n2);
	    g.addEdge(n2, n3);
	    g.addEdge(n3, n1);
		
	    GraphMLExporter<Vertex,Edge> imp= new GraphMLExporter<Vertex,Edge>();
		try {
			imp.exportGraph(g, System.out);
		} catch (ExportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

	
	        
		//Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);
		
		
		
		
		 
		//Room room1 = new Room(1,"test",Type.Couloir,"testlus");
		//Vertex[] tabVertex= new Vertex[5];
		
		//tabVertex[0]=room1;
		
		//tabVertex[0].getId();
		//(Room)tabVertex[0]).getNumber();
		
		
		

	}

}
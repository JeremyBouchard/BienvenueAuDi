package main;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.ext.*;

import main.Vertex;



public class main {

	public static void main(String[] args) {
		
		System.out.println("test");
		
		Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);

       Vertex n1 = new Vertex("Test1",Type.SallesTD);
       Vertex n2 = new Vertex("Test2",Type.SallesTD);
       Vertex n3 = new Vertex("Test3",Type.Orientation);
       
       g.addVertex(n1);
       g.addVertex(n2);
       g.addVertex(n3);
       
       g.addEdge(n1, n2);
       g.addEdge(n2, n3);
       g.addEdge(n3, n1);
	@SuppressWarnings("deprecation")
	GraphMLExporter<Vertex,Edge> imp= new GraphMLExporter<Vertex,Edge>();
	try {
		imp.exportGraph(g, System.out);
	} catch (ExportException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       
       
	}

}
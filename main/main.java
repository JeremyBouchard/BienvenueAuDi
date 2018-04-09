package main;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import main.Vertex;



public class main {

	public static void main(String[] args) {
		
		System.out.println("test");
		
/*		Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);
		//DefaultDirectedGraph<Vertex, Edge> g = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);

       Vertex n1 = new Vertex(1,"Test1",Type.Salle);
       Vertex n2 = new Vertex(1,"Test2",Type.Salle);
       Vertex n3 = new Vertex(1,"Test3",Type.Couloir);
       
       g.addVertex(n1);
       g.addVertex(n2);
       g.addVertex(n3);
       
       g.addEdge(n1, n2);
       g.addEdge(n2, n3);
       g.addEdge(n3, n1);
       
      
       
       //CSVExporter<Vertex, Edge> test = new CSVExporter<>();
       GraphMLExporter<Vertex,Edge> test = new GraphMLExporter<Vertex, Edge>();
       
       try {
		test.exportGraph(g, System.out);
	} catch (ExportException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 */
		
		private static GraphImporter<Vertex, Edge> createImporter()
	    {
	        /*
	         * Create vertex provider.
	         *
	         * The importer reads vertices and calls a vertex provider to create them. The provider
	         * receives as input the unique id of each vertex and any additional attributes from the
	         * input stream.
	         */
	        VertexProvider<Vertex> vertexProvider = (id, name) -> {
	            Vertex cv = new Vertex(id, name, null);

	            // read color from attributes map
	            if (attributes.containsKey("color")) {
	                String color = attributes.get("color").getValue();
	                switch (color) {
	                case "black":
	                    cv.setColor(Color.BLACK);
	                    break;
	                case "white":
	                    cv.setColor(Color.WHITE);
	                    break;
	                default:
	                    // ignore not supported color
	                }
	            }

	            return cv;
	        };
	        
		Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);
		
		
		 
		//Room room1 = new Room(1,"test",Type.Couloir,"testlus");
		//Vertex[] tabVertex= new Vertex[5];
		
		//tabVertex[0]=room1;
		
		//tabVertex[0].getId();
		//(Room)tabVertex[0]).getNumber();
		
		
		
	}

}

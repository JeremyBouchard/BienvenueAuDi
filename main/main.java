package main;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.ext.*;

import main.Vertex;



public class main {

	public static void main(String[] args) 
	{
		
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
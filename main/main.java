package main;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import main.Vertex;



public class main {

	public static void main(String[] args) {
		
		System.out.println("test");
		
		Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);

       Vertex n1 = new Vertex();
       Vertex n2 = new Vertex();
       Vertex n3 = new Vertex();
       
       g.addVertex(n1);
       g.addVertex(n2);
       g.addVertex(n3);
       
       g.addEdge(n1, n2);
       g.addEdge(n2, n3);
       g.addEdge(n3, n1);

       
	}

}

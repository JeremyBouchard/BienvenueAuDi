package main;

import java.io.Serializable;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

/**
 * Class aiming at implementing 
 * the shortestPath method
 * @author MariamKonate,JeremyBouchard
 *
 */
public class ShortestPath implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List< Vertex > PathBetween(DefaultDirectedWeightedGraph<Vertex,Edge> graph,Information nod1,Information nod2)
	{
		DijkstraShortestPath<Vertex,Edge> shortpath= new 	DijkstraShortestPath<Vertex,Edge>(graph);
		Vertex v1= new Vertex(nod1.getName(),nod1.getType());
		Vertex v2= new Vertex(nod2.getName(),nod2.getType());
		GraphPath<Vertex,Edge> path=shortpath.getPath(v1, v2);
		return path.getVertexList();
		
		
	}
	
}

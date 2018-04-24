package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
		
		Vertex v1= null;
		Vertex v2= null;
		Set<Vertex> myset=graph.vertexSet();
		for(Vertex v:myset)
		{
			if(nod1.getName().equals(v.getName()))
			{
				v1=v;
			}
			if(nod2.getName().equals(v.getName()))
			{
				v2=v;
			}
		}
		GraphPath<Vertex,Edge> path=shortpath.getPath(v1, v2);
		return path.getVertexList();
		
		
	}
	
}

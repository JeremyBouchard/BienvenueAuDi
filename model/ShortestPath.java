package model;


import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

/**
 * Class for conveniently calling shortest path algorithm of the JGraph Library
 * @author MariamKonate,JeremyBouchard
 *
 */
public class ShortestPath {
	
	/**
	 * to find the shortest path between nod1 and nod2.
	 * @param graph : the graph where we want to find the shortest path.
	 * @param nod1 : departure
	 * @param nod2 : destination
	 * @return the shortest path
	 */
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

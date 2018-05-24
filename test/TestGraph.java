package test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.junit.Test;

import model.Direction;
import model.Edge;
import model.Information;
import model.ShortestPath;
import model.Vertex;
import read.ReadCSVDirection;
import read.ReadCSVPas;
import read.ReadCSVVertex;

/**
 * Unit testing for creating a graph.
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public class TestGraph {	
	
	/**
	 * test 'view - Main'
	 */
	@Test
	public void testCreateGraph()
	{
		DefaultDirectedWeightedGraph<Vertex,Edge>  graph = new DefaultDirectedWeightedGraph<Vertex,Edge>(Edge.class);

		//lire noeud
		ReadCSVVertex vertexReader = new ReadCSVVertex();
		vertexReader.setPath("D:\\eclipse_workspace\\Projet_Collection\\BienvenueAuDi\\BienvenueAuDi\\readCSVVertex.csv");
		vertexReader.convertCSV();
		List<Vertex> vertexList = vertexReader.getListVertex();

		//lire pas
		ReadCSVPas edgeReader = new ReadCSVPas();
		edgeReader.setPath("D:\\eclipse_workspace\\Projet_Collection\\BienvenueAuDi\\BienvenueAuDi\\readCSVPas.csv");
		edgeReader.convertCSV();
		List<List<String>> edgeList = edgeReader.getListPas();

		//lire direction
		ReadCSVDirection directionReader = new ReadCSVDirection();
		directionReader.setPath("D:\\eclipse_workspace\\Projet_Collection\\BienvenueAuDi\\BienvenueAuDi\\readCSVDirection.csv");
		directionReader.convertCSV();
		List<List<String>> directionList = directionReader.getListDirection();
		//Ajout de tous les vertex au graphe
		for(Vertex v:vertexList)
		{
			graph.addVertex(v);
		}

		double weight;
		for (int  ligne=1; ligne < edgeList.size(); ligne++) {
			for (int colonne=1; colonne < edgeList.get(ligne).size() ; colonne++) {
				try {
					weight = Integer.parseInt(edgeList.get(ligne).get(colonne));	
					Edge tmpEdge = new Edge ();			
					tmpEdge.setDirection(Direction.valueOf(directionList.get(ligne).get(colonne)));				
					graph.addEdge(vertexList.get(ligne-1), vertexList.get(colonne-1), tmpEdge);
					graph.setEdgeWeight(tmpEdge, weight);				
				}catch(NumberFormatException e) {
					System.out.print("process error! ");
				}
			}
		}
		assertEquals(86, graph.vertexSet().size());
		
		Set<Edge> setsE=graph.edgeSet();
		Set<Vertex> setsV=graph.vertexSet();
		for(Vertex vertex:setsV)
		{
			assertEquals(graph.edgesOf(vertex).size(), graph.degreeOf(vertex));	
		}
		assertEquals(true, graph.isWeighted());
		
		ShortestPath shortestPath=new ShortestPath();
		Information information=new Information("LOVELACE", null, null);
		Information information2=new Information("BABBAGE", null, null);
		
		List<Vertex> result=shortestPath.PathBetween(graph, information, information2);
		assertEquals(5, result.size());
	}

}

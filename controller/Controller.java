package controller;

import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import model.Direction;
import model.Edge;
import model.Vertex;
import read.ReadCSVDirection;
import read.ReadCSVPas;
import read.ReadCSVVertex;

/**
 * Our controller class enabling us to connect
 * our different components and let them interact with one another
 * @author MariamKonate
 *
 */

public class Controller{

	public DefaultDirectedWeightedGraph<Vertex, Edge> g = new DefaultDirectedWeightedGraph<Vertex,Edge>(Edge.class);
	
	public void initGraph() {

		//Init
		ReadCSVVertex vertexReader = new ReadCSVVertex();
		vertexReader.setPath("csv/readCSVVertex.csv");
		vertexReader.convertCSV();
		List<Vertex> vertexList = vertexReader.getListVertex();
		//System.out.println(vertexReader.getListNoeud());

		//Pour les arcs
		ReadCSVPas edgeReader = new ReadCSVPas();
		edgeReader.setPath("csv/readCSVPas.csv");
		edgeReader.convertCSV();
		List<List<String>> edgeList = edgeReader.getListPas();

		ReadCSVDirection directionReader = new ReadCSVDirection();
		directionReader.setPath("csv/readCSVDirection.csv");
		directionReader.convertCSV();
		List<List<String>> directionList = directionReader.getListDirection();


		for(Vertex v:vertexList)
		{
			g.addVertex(v);
			System.out.println(v.getName());
		}

		double weight;

	

		for (int  ligne=1; ligne < edgeList.size(); ligne++) {
			for (int colonne=1; colonne < edgeList.get(ligne).size() ; colonne++) {
				try {
					weight = Integer.parseInt(edgeList.get(ligne).get(colonne));	

					Edge tmpEdge = new Edge ();
					//System.out.println(directionList.get(ligne).get(colonne));

					tmpEdge.setDirection(Direction.valueOf(directionList.get(ligne).get(colonne)));

					g.addEdge(vertexList.get(ligne-1), vertexList.get(colonne-1), tmpEdge);
					g.setEdgeWeight(tmpEdge, weight);


				}catch(NumberFormatException e) {

				}
			}
	
		}


		Set<Edge> mySet = g.edgeSet();
		for(Edge e:mySet)
		{

			System.out.println(e.Source().getName() + "\t\t\t -> " + e.Destination().getName() + " \t\t\t\tweight : " + g.getEdgeWeight(e) + " \t\t\t direction" + e.getDirection());
		}
		
		initConnexion();

	}
	
	public void initConnexion () {
		
		
		new Thread(new CommSource(3000)).start();
		new Thread( new ManageConnexions(g)).run();
	}
}



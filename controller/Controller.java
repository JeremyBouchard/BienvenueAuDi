package controller;

import java.util.List;
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
 * @author Jérémy Bouchard
 *
 */

public class Controller{

	public DefaultDirectedWeightedGraph<Vertex, Edge> g = new DefaultDirectedWeightedGraph<Vertex,Edge>(Edge.class);

	/**
	 * Class that create the graph from the different sources 
	 */

	public void initGraph() {

		//Read list of edges
		ReadCSVVertex vertexReader = new ReadCSVVertex();
		vertexReader.setPath("src/csv/readCSVVertex.csv");
		vertexReader.convertCSV();
		List<Vertex> vertexList = vertexReader.getListVertex();
		//System.out.println(vertexReader.getListNoeud());

		//Read list of Edges with weight
		ReadCSVPas edgeReader = new ReadCSVPas();
		edgeReader.setPath("src/csv/readCSVPas.csv");
		edgeReader.convertCSV();
		List<List<String>> edgeList = edgeReader.getListPas();

		//Read directions
		ReadCSVDirection directionReader = new ReadCSVDirection();
		directionReader.setPath("src/csv/readCSVDirection.csv");
		directionReader.convertCSV();
		List<List<String>> directionList = directionReader.getListDirection();

		//Add vertex in the graph
		for(Vertex v:vertexList)
		{
			g.addVertex(v);
			//System.out.println(v.getName());
		}

		double weight;


		//Add Edges
		for (int  ligne=1; ligne < edgeList.size(); ligne++) {
			for (int colonne=1; colonne < edgeList.get(ligne).size() ; colonne++) {
				try {
					//get the edgeWeight
					weight = Integer.parseInt(edgeList.get(ligne).get(colonne));	

					//Create the new edge
					Edge tmpEdge = new Edge ();
					//System.out.println(directionList.get(ligne).get(colonne));

					//Set the Edge Direction
					tmpEdge.setDirection(Direction.valueOf(directionList.get(ligne).get(colonne)));

					//Add the edge
					g.addEdge(vertexList.get(ligne-1), vertexList.get(colonne-1), tmpEdge);

					//Set the Edge Weight
					g.setEdgeWeight(tmpEdge, weight);


				}catch(NumberFormatException e) {

				}
			}

		}
		initConnexion();
	}



	/**
	 * Method that lauch the thred for connection to clients and sources
	 */
	public void initConnexion () {


		new Thread(new CommSource(3000)).start();
		new Thread( new ManageConnexions(g)).run();
	}
}



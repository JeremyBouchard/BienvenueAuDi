package view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;

import controller.CommSource;
import controller.ManageConnexions;
import model.ClassRoom;
import model.Course;
import model.Direction;
import model.Edge;
import model.ShortestPath;
import model.Type;
import model.Vertex;
import read.ReadCSVDirection;
import read.ReadCSVPas;
import read.ReadCSVVertex;

import org.jgrapht.ext.*;



public class main {

	static DefaultDirectedWeightedGraph<Vertex,Edge>  g = null;
	
	public static void main(String[] args) throws IOException 
	{
		
		
		new Thread(new CommSource(3000)).start();


		g = new DefaultDirectedWeightedGraph<Vertex,Edge>(Edge.class);

		//Pour les vertex
		ReadCSVVertex vertexReader = new ReadCSVVertex();
		vertexReader.setPath("C:/Users/Jeremy/Google Drive/_Proffesionnel_Drive/WorkspaceEclipse/DiProject/src/readCSVVertex.csv");
		vertexReader.convertCSV();
		List<Vertex> vertexList = vertexReader.getListVertex();
		//System.out.println(vertexReader.getListNoeud());

		//Pour les arcs
		ReadCSVPas edgeReader = new ReadCSVPas();
		edgeReader.setPath("C:/Users/Jeremy/Google Drive/_Proffesionnel_Drive/WorkspaceEclipse/DiProject/src/readCSVPas.csv");
		edgeReader.convertCSV();
		List<List<String>> edgeList = edgeReader.getListPas();

		ReadCSVDirection directionReader = new ReadCSVDirection();
		directionReader.setPath("C:/Users/Jeremy/Google Drive/_Proffesionnel_Drive/WorkspaceEclipse/DiProject/src/readCSVDirection.csv");
		directionReader.convertCSV();
		List<List<String>> directionList = directionReader.getListDirection();

		//System.out.println(edgeList);
		//System.out.println(directionList);

		//Ajout de tous les vertex au graphe
		for(Vertex v:vertexList)
		{
			g.addVertex(v);
			System.out.println(v.getName());
		}

		double weight;

		//		System.out.println(edgeReader.getListPas());
		//		System.out.println(edgeList.size());
		//		System.out.println(edgeList.get(0).size());
		//		System.out.println(vertexList.size());
		//System.out.println(directionList.get(80).size());

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
			//System.out.println(ligne);
		}


		//debug display
		Set<Edge> mySet = g.edgeSet();
		for(Edge e:mySet)
		{

			System.out.println(e.Source().getName() + "\t\t\t -> " + e.Destination().getName() + " \t\t\t\tweight : " + g.getEdgeWeight(e) + " \t\t\t direction" + e.getDirection());
		}






		//debug Dijkstra
		//		
		//		List<Edge> edgeList= edgeReader.getListEdge();
		//		for(Edge e:edgeList)
		//		{
		//	
		//			g.addEdge(e);
		//		}


		/*				
	    Vertex n1 = new Vertex("Boole",Type.SallesTD);
	    Vertex n2 = new Vertex("lovelacE",Type.SallesTD);
	    Vertex n3 = new Vertex("cafet",Type.Orientation);
	    Vertex n4 = new Vertex("VON NEUMAN",Type.SallesTD);

	    g.addVertex(n1);
	    g.addVertex(n2);
	    g.addVertex(n3);
	    g.addVertex(n4);   

	    Edge e1 = g.addEdge(n1,n2); 
        g.setEdgeWeight(e1, 5); 

        Edge e2 = g.addEdge(n1,n3); 
        g.setEdgeWeight(e2, 10); 

        Edge e3 = g.addEdge(n2,n1); 
        g.setEdgeWeight( e3, 5); 

        Edge e4 = g.addEdge(n2,n3); 
        g.setEdgeWeight( e4, 3); 

        Edge e5 = g.addEdge(n3,n4); 
        g.setEdgeWeight( e5, 8); 



		DijkstraShortestPath<Vertex,Edge> sp= new 	DijkstraShortestPath<Vertex,Edge>(g);
		GraphPath<Vertex, Edge> gp = sp.getPath(n1, n4);

		List<Vertex> myset= gp.getVertexList();
		for(Vertex v:myset)
		{
			System.out.println(v.getName());
		}
		System.out.println(gp.getWeight());
		 */

		//		
		//	    GraphMLExporter<Vertex,Edge> imp= new GraphMLExporter<Vertex,Edge>();
		//		try {
		//			imp.exportGraph(g, System.out);
		//		} catch (ExportException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}   



		//Graph<Vertex, Edge> g = new SimpleGraph<>(Edge.class);





		//Room room1 = new Room(1,"test",Type.Couloir,"testlus");
		//Vertex[] tabVertex= new Vertex[5];

		//tabVertex[0]=room1;

		//tabVertex[0].getId();
		//(Room)tabVertex[0]).getNumber();

		//new Thread( new ManageConnexions(g)).run();
	}

	public static void addSrcData (HashMap<String, TreeMap<Float, ArrayList<Course>>> hMap) {
		TreeMap<Float, ArrayList<Course>> map = hMap.get("DI");
		for (Entry<Float, ArrayList<Course>> mapEntry : map.entrySet()) {
				for (Course cours : mapEntry.getValue()) {
					
					//Debug
					System.out.println(cours.getStartTime()+ " -> "+cours.getEndTime());
					System.out.println("Prof : " + cours.getProfName());
					System.out.println("Promo : " + cours.getStudentPromo());
					System.out.println("Salle : " + cours.getClassroomName());
					System.out.println("___________________________");
					
				}
		}
		
		System.out.println(hMap);
		
		

	}
}


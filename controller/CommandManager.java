package controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import model.Direction;
import model.Edge;
import model.Information;
import model.Node;
import model.ShortestPath;
import model.Vertex;
import read.ReadCSVDirection;

/**
 * Class implementing methods to communicate with client interpret command
 * and send back results
 * @author MariamKonate,JeremyBouchard
 *
 */
public class CommandManager implements Runnable
{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String message = "";
	private DefaultDirectedWeightedGraph<Vertex,Edge> graph;

	/**
	 * Constructor
	 * @param socket the tcp socket
	 * @param in Input buffer
	 * @param out output writer
	 * @param graph the graph
	 */
	public CommandManager(Socket socket, BufferedReader in, PrintWriter out,DefaultDirectedWeightedGraph<Vertex,Edge> graph)
	{
		this.socket = socket;
		this.in = in;
		this.out =out;
		this.graph=graph;
	}

	/**
	 * Send a list of informations to the client
	 * each information object represent a vertex
	 * @throws IOException if there is a problem with the output stream
	 */
	public void SendInformations() throws IOException
	{

		//Create a list of all Information, one for each vertex
		List<Information> information =createListInformation(graph);
		//System.err.println("Envoi des informations ..");
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(information);
		oos.flush();
	}

	/**
	 * 
	 * @throws IOException if there is a problem with the output stream
	 * @throws ClassNotFoundException if the class differ from the one implemented in the client
	 */
	public void getRouteRequest() throws IOException, ClassNotFoundException
	{
		System.out.println("Reception d'informations en cours...");
		/**
		 * Creating an object input Stream to get the first object
		 */
		ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
		Information firstNode = (Information) is.readObject();
		/**
		 * Creating an object input Stream to get the second object
		 */
		ObjectInputStream is2 = new ObjectInputStream(socket.getInputStream());
		Information secondNode = (Information) is2.readObject();

		//shortestPath
		ShortestPath path=new ShortestPath();
		//CreateNodList with the list of vertex corresponding to the shortest path
		List<Node> node=createList(path.PathBetween(graph, firstNode, secondNode));

		//Send node list
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(node);
		oos.flush();
	}

	/**
	 * thread
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				message = in.readLine();
				System.out.println("[REQUEST]" + message);

				if(message!=null)
				{
					if(message.equals("GET ROUTE"))
					{
						getRouteRequest();
					}
					else if(message.equals("GET INFORMATIONS"))
					{
						SendInformations();
					}
				}
				else
				{
					break;
				}

			}
			catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}

		}
	}
/**
 * 
 * @param graph the graph
 * @return a list of Information that represent each vertex of the graph
 */
	public  List<Information> createListInformation(DefaultDirectedWeightedGraph<Vertex,Edge> graph)
	{
		List<Information> list=new ArrayList<Information>();
		Set<Vertex> vertices=graph.vertexSet();
		for(Vertex vertex:vertices)
		{
			Information info=new Information(vertex.getName(),vertex.getType(),"/images/"+ vertex.getId()+".JPG");
			list.add(info);
		}
		return list;
	}
	/**
	 * 
	 * @param vertices a list of vertex to transform into Node (information + direction)
	 * @return the list of node with the right direction and information
	 */

	public  List<Node> createList(List<Vertex> vertices)
	{

		ReadCSVDirection reader = new ReadCSVDirection();//Use for calculating the real direction to the next node
		List<Node> nodeList=new ArrayList<Node>();

		//Special case : The first node
		Information firstInfo=new Information(vertices.get(0).getName(),vertices.get(0).getType(),"/images/"+ vertices.get(0).getId()+".JPG");
		Node firstNode = new Node(firstInfo,graph.getEdge(vertices.get(0), vertices.get(1)).getDirection());
		nodeList.add(firstNode);

		//All the node in the middle
		for(int i=1; i <vertices.size()-1; i++)//For all vertices except the last and first one
		{
			Information middleInfo=new Information(vertices.get(i).getName(),vertices.get(i).getType(),"/images/"+ vertices.get(i).getId()+".JPG");
			 
			//Call the realDirection method
			Node middleNode= new Node(middleInfo,reader.getRealDirection(vertices.get(i-1), vertices.get(i), vertices.get(i+1), graph)); 
			nodeList.add(middleNode);
		}

		//Special case : The last node
		Information lastInfo=new Information(vertices.get(vertices.size()-1).getName(),vertices.get(vertices.size()-1).getType(),"/images/"+ vertices.get(vertices.size()-1).getId()+".JPG");
		Node lastNode = new Node(lastInfo,Direction.None);
		nodeList.add(lastNode);

		return nodeList;
	}

}

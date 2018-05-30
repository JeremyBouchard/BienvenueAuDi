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
 * Class implementing methods to communicate with client
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
	 * @param socket
	 * @param in
	 * @param out
	 */
	public CommandManager(Socket socket, BufferedReader in, PrintWriter out,DefaultDirectedWeightedGraph<Vertex,Edge> graph)
	{
		this.socket = socket;
		this.in = in;
		this.setOut(out);
		this.graph=graph;
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void SendInformations() throws IOException
	{

		//Create a list of all nodes(information)
		List<Information> information =createListInformation(graph);
		System.err.println("Envoi des informations ..");
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(information);
		oos.flush();
	}

	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
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
		/**
		 * TODO: DO SOMETHING WITH THE FIRST AND SECOND NODE (in this case: get the route)
		 * 
		 */
		/*List<Information> srcDest= new ArrayList<Information>();
		srcDest.add(firstNode);
		srcDest.add(secondNode);
		return srcDest;*/
		
		//System.out.println(firstNode.getName() + " & " + secondNode.getName());
		
		
		
		//shortestPath
		ShortestPath path=new ShortestPath();
		//CreateNodList
		List<Node> node=createList(path.PathBetween(graph, firstNode, secondNode));
		//CalculateDirection
		//calculDirection(node);
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
	 * @return the out
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}

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

	public  List<Node> createList(List<Vertex> vertices)
	{

		ReadCSVDirection reader = new ReadCSVDirection();
		List<Node> nodeList=new ArrayList<Node>();
		
		
		Information firstInfo=new Information(vertices.get(0).getName(),vertices.get(0).getType(),"/images/"+ vertices.get(0).getId()+".JPG");
		Node firstNode = new Node(firstInfo,graph.getEdge(vertices.get(0), vertices.get(1)).getDirection());
		nodeList.add(firstNode);

		for(int i=1; i <vertices.size()-1; i++)//For all vertices except the last one
		{
			Information middleInfo=new Information(vertices.get(i).getName(),vertices.get(i).getType(),"/images/"+ vertices.get(i).getId()+".JPG");
			//Node middleNode= new Node(info,graph.getEdge(vertices.get(i), vertices.get(i+1)).getDirection()); 
			Node middleNode= new Node(middleInfo,reader.getRealDirection(vertices.get(i-1), vertices.get(i), vertices.get(i+1), graph)); //
			nodeList.add(middleNode);
		}
		
		
		Information lastInfo=new Information(vertices.get(vertices.size()-1).getName(),vertices.get(vertices.size()-1).getType(),"/images/"+ vertices.get(vertices.size()-1).getId()+".JPG");
		Node lastNode = new Node(lastInfo,Direction.None);
		nodeList.add(lastNode);

		return nodeList;
	}

	/*public void calculDirection(List<Node> list)
	{
		String path="/Users/MariamKonate/Desktop/ProjetS8/readCSVDirection.csv";
		ReadCSVDirection readCSV=new ReadCSVDirection();
		readCSV.setPath(path);
		readCSV.convertCSV();
		for(int i=0;i<list.size()-1;i++)
		{
			String direction=readCSV.getDirection(list.get(i).getInformation().getName(), list.get(i+1).getInformation().getName());
			if(direction.equals("N"))
			{
				list.get(i).setDirectionToTake(Direction.North);
			}
			else if(direction.equals("S"))
			{
				list.get(i).setDirectionToTake(Direction.South);
			}
			else if(direction.equals("R"))
			{
				list.get(i).setDirectionToTake(Direction.West);
			}
			else if(direction.equals("L"))
			{
				list.get(i).setDirectionToTake(Direction.East);
			}
			else
			{
				list.get(i).setDirectionToTake(Direction.None);
			}

		}
	}*/


}

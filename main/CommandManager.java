package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

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

    /**
     * Constructor
     * @param socket
     * @param in
     * @param out
     */
    public CommandManager(Socket socket, BufferedReader in, PrintWriter out)
    {
        this.socket = socket;
        this.in = in;
        this.setOut(out);
    }

    /**
     * 
     * @throws IOException
     */
    public void SendInformations(DefaultDirectedWeightedGraph<Vertex,Edge> graph) throws IOException
    {
        
    		//Create a list of all nodes(information)
        List<Information> informations =createListInformation(graph);
        System.err.println("Envoi des informations ..");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(informations);
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
         */
        System.out.println(firstNode.getName() + " & " + secondNode.getName());
        //ShortestPath.PathBetween 
        //CalculateDirection
        //CreateNodList
    }

    /**
     * thread
     */
    public void run(DefaultDirectedWeightedGraph<Vertex,Edge> graph)
    {
        while(true)
        {
            try
            {
                message = in.readLine();
                System.out.println("[REQUEST]" + message);

                if(message.equals("GET ROUTE"))
                {
                    getRouteRequest();
                }
                else if(message.equals("GET INFORMATIONS"))
                {
                    SendInformations(graph);
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
			Information info=new Information(vertex.getName(),vertex.getType());
			list.add(info);
		}
		return list;
	}
	
	public  List<Information> createListInformation(List<Vertex> vertices)
	{
		List<Information> list=new ArrayList<Information>();
		for(Vertex vertex:vertices)
		{
			Information info=new Information(vertex.getName(),vertex.getType());
			list.add(info);
		}
		return list;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

package controller;

import java.io.*;
import java.net.*;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import model.Edge;
import model.Vertex;

/**
 * Class that opens a server socket on a defined port
 * This server can host multiples clients.
 */

public class ManageConnexions implements Runnable
{
    private final static int SERVER_PORT = 2009;
    private BufferedReader in;
    private PrintWriter out;
    private ServerSocket socketserver = null;
    private Socket socket = null;
    DefaultDirectedWeightedGraph<Vertex,Edge> graph;

    public ManageConnexions(DefaultDirectedWeightedGraph<Vertex,Edge> graph)
    {
        try
        {
            socketserver = new ServerSocket(SERVER_PORT);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            this.graph=graph;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public ManageConnexions()
    {
        try
        {
            socketserver = new ServerSocket(SERVER_PORT);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    
    public void run() {try
    {
        while(true)
        {
            socket = socketserver.accept();
            System.out.println("Tentative de connexion en cours...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            new Thread(new CommandManager(socket,in, out,graph)).run();
        }
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }};
    
    public void run(DefaultDirectedWeightedGraph<Vertex,Edge> graph)
    {
        try
        {
            while(true)
            {
                socket = socketserver.accept();
                System.out.println("Tentative de connexion en cours...");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                new Thread(new CommandManager(socket,in, out,graph)).run();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

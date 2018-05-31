package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import model.Course;

/**
 * Class for communication with the source client ENT
 * @author Xavier Bouchenard
 *
 */
public class CommSource implements Runnable{
	/**
	*  Sets the socket's server
	*/
	private ServerSocket socketserver = null;
	/**
	*  Active socket of the server
	*/
	private Socket socket = null;
	
	/**
	 * Number of data to receive from the client
	 */
	private int Size = 0;
	
	/**
	 * Contains the lists of all courses generated
	 */
	private HashMap<String, TreeMap<Float, ArrayList<Course>>> hMap = null;//Liste des Cours de la journ�e au DI, trie par Heure debut
	
	private boolean flag = true;
	
	/**
	 * Constructor of the class
	 * @author Xavier Bouchenard
	 * @param port	Port ID to launch the server service
	 */
	public CommSource(int port) {
		try {
			socketserver = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		hMap = new HashMap<String, TreeMap<Float, ArrayList<Course>>>();
	}
	
	/**
	 * Awaiting for input data to read
	 * @author Xavier Bouchenard
	 */
	@Override
	public void run() {
		System.out.println("Server is starting");
		while (flag) {
			try {
				System.out.println("Awaiting for a connection attempt");
				socket = socketserver.accept();
				getDataSize();
				getData();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayContent();
		}
		
	}
	
	/**
	 * Gets how much data will be received by the server for the next receive stage
	 * @author Xavier Bouchenard
	 */
	private void getDataSize() {
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Size = in.readInt();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("I will receive " + Size + " thing(s)");
	}
	
	/**
	 * Gets the information of the department name first and the list of course next
	 * @author Xavier Bouchenard
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void getData() {
		ObjectInputStream in = null;
		
		Object obj = null;
		boolean var = true;
		String dptName = null;
		TreeMap<Float, ArrayList<Course>> map = null;
		int i = 0;
		
		// loops until i reaches the Size value
		while (i < Size) {
			try {
				// accepts all connection attempt
				socket = socketserver.accept();
				in = new ObjectInputStream(socket.getInputStream());			
				obj = in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			dptName = (String)obj;
			
			try {
				obj = in.readObject();
				// closes the input stream
				in.close();
				map = (TreeMap<Float, ArrayList<Course>>) obj;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			if (hMap.containsKey(dptName))	hMap.replace(dptName, map);
			else hMap.put(dptName, map);
			// counter of receiving "thing(s)"
			i++;
			try {
				// closes the socket connection
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//main.addSrcData(hMap);
		
		
	}
	
	/**
	 * Convenient class for displaying the keys of the HashMap in the console
	 * @author Xavier Bouchenard
	 */
	private void displayContent() {
		if (hMap.size() != 0) {
			Set<String> str = hMap.keySet();
			System.out.println("These are the departments with a stored list of courses:");
			for (String string : str) {
				System.out.println("\t- " + string);
			}
			for (String string : str) {
				if (hMap.get(string) != null) {
					System.out.println("The key value " + string + " is correct because it is existent");
				}
			}
		}
		else System.out.println("Nothing to display");
	}
}

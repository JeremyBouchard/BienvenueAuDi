package SocketService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeMap;

import Model.Course;

public class TCPClientSocket implements Serializable{
	/**
	 * Serial number for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contains the IP address of the socket server handled by the main application
	 */
	private String IPAddress = null;
	/**
	 * Corresponds to the input port of the socket server 
	 */
	private int conPort = 0;
	/**
	 * Represents the communication between the socket client and the socket server
	 */
	private Socket socket = null;
	
	/**
	 * Constructor of the class, it has nothing to do 
	 * 		the IP Address and communication port are fixed before the launch of the application
	 * @param IPAddress		IP address of the TCP server with the one to connect
	 * @param port			Input port of the connection
	 */
	public TCPClientSocket(String IPAddress, int port) {
		this.IPAddress = IPAddress;
		conPort = port;
	}

	/**
	 * Opens a socket connection with the socket server
	 * @author Xavier Bouchenard
	 */
	public void OpenSocketConnection() {
		try {
			socket = new Socket(IPAddress, conPort);
			System.out.println("The socket connection succeed");
		} catch (UnknownHostException e) {
			System.out.println("The host machine has not been found.");
		} catch (IOException e) {
			System.out.println("Error: the server is not working yet or the information given are wrong");
		}
	}
	
	public void SendDataSize(int size) {
		try {
			ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeInt(size);
			obj.flush();
			obj.close();
		} catch(IOException e) {
			System.out.println("Problem to send the data size to the server");
		}
	}
	
	/** 
	 * Sends a list of courses of the current day to store to the main to the server application
	 * @author Xavier Bouchenard
	 * @param lCourse	List of courses
	 * @param dptName	Name of the timetable database
	 */
	public void sendData(String dptName, TreeMap<Float, ArrayList<Course>> lCourse) {		
		try {
			ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeObject(dptName);
			obj.flush();
			obj.writeObject(lCourse);
			obj.flush();
			obj.close();
		} catch (IOException e) {
			System.out.println("Failed to give information of the courses to the server.");
		}
	}
	
	/**
	 * Sends the size of the map to the socket server
	 * @author Xavier Bouchenard
	 * @param mapSize	Size of the map
	 */
	public void SendMapSize(int mapSize) {
		ObjectOutputStream obj;
		try {
			obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeInt(mapSize);
			obj.flush();
			obj.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the socket connection
	 * @author Xavier Bouchenard
	 */
	public void closeSocketConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Failed to close the socket connection with the server.");
		}
		socket = null;
	}
}

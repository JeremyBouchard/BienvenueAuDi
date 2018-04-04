package SocketService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import Model.Course;

public class TCPClientSocket{
	/**
	 * Contains the IP address of the socket server handled by the main application
	 */
	private String IPAddress = null;
	/**
	 * Corresponds to the input port of the socket server 
	 */
	private int conPort = 3007;
	/**
	 * Represents the communication between the socket client and the socket server
	 */
	private Socket socket = null;
	
	/**
	 * Constructor of the class, it has nothing to do 
	 * 		the IP Address and communication port are fixed before the launch of the application
	 */
	public TCPClientSocket() {
		
	}

	/**
	 * @author Xavier Bouchenard
	 * @param lCourse	List of courses 
	 * Sends a list of courses of the current day to store for use
	 */
	public void sendData(ArrayList<Course> lCourse) {
		try {
			socket = new Socket(IPAddress, conPort);
			System.out.println("The socket connection succeed");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("The host machine has not been found.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeObject(lCourse);
			obj.flush();
			obj.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to give information of the courses to the server.");
		}
		
		closeSocketConnection();
	}

	/**
	 * @author Xavier Bouchenard
	 * Closes the socket connection
	 */
	private void closeSocketConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to close the socket connection with the server.");
		}
		socket = null;
	}
}

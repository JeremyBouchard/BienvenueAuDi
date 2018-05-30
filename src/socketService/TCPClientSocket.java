package socketService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Course;

/**
 * Socket TCP client class which is in charge to send all the data needed
 * @author Xavier Bouchenard
 *
 */
public class TCPClientSocket {
	/**
	 * Contains the IP address of the socket server handled by the main application
	 */
	private static String IPAddress = null;
	/**
	 * Corresponds to the input port of the socket server 
	 */
	private static int conPort = 0;
	/**
	 * Represents the communication between the socket client and the socket server
	 */
	private static Socket socket = null;
	
	/**
	 * Constructor of the class, it has nothing to do 
	 * 		the IP Address and communication port are fixed before the launch of the application
	 * @param adIP		IP address of the TCP server with the one to connect
	 * @param port			Input port of the connection
	 */
	public static void setSocketInfo(String adIP, int port) {
		IPAddress = adIP;
		conPort = port;
	}

	/**
	 * Opens a socket connection with the socket server
	 * @author Xavier Bouchenard
	 * @throws IOException 	Exception throwed if an opening connection attempt failed 
	 */
	public static void OpenSocketConnection() throws IOException {
		try {
			socket = new Socket(IPAddress, conPort);
			System.out.println("The socket connection succeed");
		} catch (UnknownHostException e) {
			throw new UnknownHostException("The host machine has not been found.");
		} catch (IOException e) {
			throw new IOException("Error: the server is not working yet or the information given are wrong");
		} 
	}
	
	/**
	 * Gets the number of data to receive
	 * @param size				Number of data to receive
	 * @throws IOException		Exception throwed when an error occured
	 */
	public static void SendDataSize(int size) throws IOException {
		try {
			ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeInt(size);
			obj.flush();
			obj.close();
			System.out.println("The size of the data to send has been sent to the server\n");
		} catch(IOException e) {
			throw new IOException("A problem occured while sending the data size to the server");
		}
	}
	
	/** 
	 * Sends a list of courses of the current day to store to the main to the server application
	 * @author Xavier Bouchenard
	 * @param lCourse	List of courses
	 * @param dptName	Name of the timetable database
	 * @throws IOException 	Exception throwed if a list of courses has not been sent
	 */
	public static void sendData(String dptName, TreeMap<Float, ArrayList<Course>> lCourse) throws IOException {		
		try {
			ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeObject(dptName);
			obj.flush();
			obj.writeObject(lCourse);
			obj.flush();
			obj.close();
			System.out.println("The data has been sent to the server\n");
		} catch (IOException e) {
			throw new IOException("Failed to give information of the courses to the server.");
		}
	}
	
	/**
	 * Sends the size of the map to the socket server
	 * @author Xavier Bouchenard
	 * @param mapSize	Size of the map
	 * @throws IOException 	Exception throwed in a sending attempt case
	 */
	public static void SendMapSize(int mapSize) throws IOException {
		ObjectOutputStream obj;
		try {
			obj = new ObjectOutputStream(socket.getOutputStream());
			obj.writeInt(mapSize);
			obj.flush();
			obj.close();
		} catch (IOException e) {
			throw new IOException("Error: a problem occured while sending the data size");
		}
	}

	/**
	 * Closes the socket connection
	 * @author Xavier Bouchenard
	 * @throws IOException 	Exception throwed if the connection cannot be closed properly
	 */
	public static void closeSocketConnection() throws IOException {
		try {
			socket.close();
		} catch (IOException e) {
			throw new IOException("Failed to close the socket connection with the server.");
		}
		socket = null;
	}
}

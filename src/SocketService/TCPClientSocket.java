package SocketService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Model.Course;

public class TCPClientSocket{
	private String IPAddress = null;
	private int comPort = 3007;
	private Socket socket = null;
	
	public TCPClientSocket() {
		
	}

	public void sendData(ArrayList<Course> lCourse) {
		try {
			socket = new Socket(IPAddress, comPort);
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
		
		closeSocket();
	}

	private void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to close the socket connection with the server.");
		}
	}
}

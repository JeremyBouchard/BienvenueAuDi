package Controllers;

import SocketService.TCPClientSocket;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Model.Course;

public final class ApplicationHandler{
	/**
	 * Object of the class TCPClientSocket initialized at null
	 */
	private TCPClientSocket socket = null;
	
	private HashMap<String, ADEHandler> mapADEH;
	
	/**
	 * Constructor of the ApplicationHandler class
	 */
	public ApplicationHandler() {
		socket = new TCPClientSocket();
		new Thread(new TimeHandler()).start();
		mapADEH = new HashMap<String, ADEHandler>();
	}
	
	public void AddNewTimeTblDB(String dptName, String urlTimeTB) {
		ADEHandler ade = new ADEHandler(dptName, urlTimeTB);
		
	}

}

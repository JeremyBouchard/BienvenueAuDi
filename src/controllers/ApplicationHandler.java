package controllers;

import socketService.TCPClientSocket;
import controllers.ADEHandler;
import model.Course;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Handler of this app which coordinates all actions according to the current state of the system
 * @author Xavier Bouchenard
 *
 */
public final class ApplicationHandler implements Serializable{
	/**
	 * Value declared for the serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * IP Address of the host machine to store
	 */
	private String IPAddress = null;
	
	/**
	 * Input port of the TCP server
	 */
	private int conPort = 0;
	
	/**
	 * Stores the daily timetable for each department added by the user
	 */
	private static HashMap<String, TreeMap<Float, ArrayList<Course>>> mapADEH = null;
	
	/**
	 * Stores all the URLs of the ADE file associated to a department name
	 */
	private static HashMap<String, String> lURL = null;
	
	/**
	 * Important to stop the TimeHandler thread
	 */
	private static boolean StopValue = false;
	
	/**
	 * Constructor of the ApplicationHandler class
	 * @author Xavier Bouchenard
	 */
	public ApplicationHandler() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the IP address of the machine on which the main application is running");
		
		IPAddress = sc.nextLine();
		System.out.println("Please enter the connection port now");
		conPort = sc.nextInt();
		
		TCPClientSocket.setSocketInfo(IPAddress, conPort);
		mapADEH = new HashMap<String, TreeMap<Float, ArrayList<Course>>>();
		lURL = new HashMap<String, String>();
		
		new Thread(new TimeHandler()).start();
	}
	
	/**
	 * Constructor of the ApplicationHandler class
	 * @author Xavier Bouchenard
	 * @param address		IP Address of the host machine
	 * @param port			Input listen port of the TCP server
	 * @param map			List of courses sorted by department name
	 * @param urls			List of URLs to join the ADE file related to the department timetable
	 */
	public ApplicationHandler(String address, int port, HashMap<String, TreeMap<Float, ArrayList<Course>>> map, 
			HashMap<String, String> urls) {
		IPAddress = address;
		conPort = port;
		mapADEH = map;
		lURL = urls;
		
		StopValue = false;
		
		CheckTCPSocketConf();		
	}
	
	
	/**
	 * Adds a new timetable database for a given department and will be loaded from a given url
	 * @author Xavier Bouchenard
	 * @param dptName		Name of the department to add
	 * @param urlTimeTB		URL of an online ADE file related to this department
	 */
	public void AddNewTimeTblDB(String dptName, String urlTimeTB) {
		TreeMap<Float, ArrayList<Course>> map = null;
		
		if (!mapADEH.containsKey(dptName)) {
			lURL.put(dptName, urlTimeTB);
			
			try {
				map = ADEHandler.GenerateTimeTableOfDay(urlTimeTB);
				SendDataSize(1);
				SendTCPCoursesList(dptName, map);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			mapADEH.put(dptName, map);			
		}
		else System.out.println("This timetable database already exists\n");		
	}
	
	/**
	 * Gets the flag value to stop the thread related to the TimeHandler class
	 * @author Xavier Bouchenard
	 * @param value		The new value
	 */
	public void setAppliStop(boolean value) {
		StopValue = value;
	}
	
	/**
	 * Allows the TimeHandler thread to check the stop value set in this class
	 * @author Xavier Bouchenard
	 * @return StopValue	Value to return
	 */
	public static boolean AppliStop() {
		return StopValue;
	}
	
	/**
	 * Updates the timetables of all the ADEHandler objects stored in the HashMap
	 * @author Xavier Bouchenard
	 */
	public static void UpdateTimeTable() {
		TreeMap<Float, ArrayList<Course>> map = null;
		
		// for all key-value combination ...
		for (Entry<String, String> mapEntry : lURL.entrySet()) {
			try {
				map = ADEHandler.GenerateTimeTableOfDay(mapEntry.getValue());
				
				if (map.hashCode() != mapADEH.get(mapEntry.getKey()).hashCode()) {
					mapADEH.replace(mapEntry.getKey(), mapADEH.get(mapEntry.getKey()), map);
					SendDataSize(1);
					SendTCPCoursesList(mapEntry.getKey(), map);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Gets all the available keys of the HashMaps
	 * @author Xavier Bouchenard
	 * @return	All the available keys of the HashMap
	 */
	public Set<String> getKeysOfHashMap() {
		return mapADEH.keySet();
	}

	/**
	 * Updates the URL of an existing timetable database and builds again the new timetable with the new URL
	 * @author Xavier Bouchenard
	 * @param dbName	Name of the timeatable to update
	 * @param newURL	New URL to get the ADE file
	 */
	public void UpdateURL(String dbName, String newURL) {
		TreeMap<Float, ArrayList<Course>> map = null;
		
		if (lURL.containsKey(dbName)) {
			lURL.replace(dbName, lURL.get(dbName), newURL);
			
			try {
				map = ADEHandler.GenerateTimeTableOfDay(newURL);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			mapADEH.replace(dbName, mapADEH.get(dbName), map);
			
			SendDataSize(1);
			SendTCPCoursesList(dbName, map);
		}
		
	}
	
	/**
	 * Removes a timetable database from the HashMap
	 * @author Xavier Bouchenard
	 * @param dbName	Name of the timetable database to remove
	 */
	public void RemoveTimetableDB(String dbName) {
		if (mapADEH.containsKey(dbName)) {
			mapADEH.remove(dbName);
			lURL.remove(dbName);
		}
		else	System.out.println("No existing timetable has this name");
	}
	
	/**
	 * Sends the timetable of courses for the current day
	 * @author Xavier Bouchenard
	 */
	public static void SendTCPCourses() {
		if (mapADEH.size() > 0) {
			try {
				TCPClientSocket.OpenSocketConnection();
				SendDataSize(mapADEH.size());				
				
				for (Entry<String, TreeMap<Float, ArrayList<Course>>> entry : mapADEH.entrySet()) {
					TCPClientSocket.sendData(entry.getKey(), entry.getValue());
				}	

				TCPClientSocket.closeSocketConnection();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}			
		}
		else System.out.println("No database is stored");
	}
	
	/**
	 * Sends a courses list for a specific department
	 * @author Xavier Bouchenard
	 * @param dptName	Name of the department
	 * @param map		Courses list of this object to send
	 */
	private static void SendTCPCoursesList(String dptName, TreeMap<Float, ArrayList<Course>> map) {		
		try {
			TCPClientSocket.OpenSocketConnection();
			TCPClientSocket.sendData(dptName, map);
			TCPClientSocket.closeSocketConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Sends the number of timetable to send
	 * @author Xavier Bouchenard
	 * @param size		Number of courses list to send
	 */
	private static void SendDataSize(int size) {
		try {
			TCPClientSocket.OpenSocketConnection();
			TCPClientSocket.SendDataSize(size);
			TCPClientSocket.closeSocketConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Displays the TCP settings and the application state: false if running or true if not
	 * @author Xavier Bouchenard
	 */
	public void showParamSocket() {
		System.out.println("IP Address: " + IPAddress + "\t Port: " + conPort);		
		System.out.println("State of the stop value: " + StopValue);
	}
	
	/**
	 * Asks for TCP settings changes when reading and parsing the serialized file
	 * @author Xavier Bouchenard
	 */
	private void CheckTCPSocketConf() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String value;
		boolean flag = true;
		
		while (flag) {
			System.out.println("Has the IP address of the host machine changed ? (Y/N)");
			value = sc.nextLine();
			
			if (value.equals("Y")) {
				System.out.println("Please set the new IP address:");
				IPAddress = sc.nextLine();
				System.out.println("Please set the new input port:");
				conPort = sc.nextInt();
				flag = false;
			}
			else if (value.equals("N"))	{
				System.out.println("No parameters changed");
				flag = false;
			}
			else {
				System.out.println("Please try to set a valid value");
			}
		}		
		
		TCPClientSocket.setSocketInfo(IPAddress, conPort);
	}
	
	/**
	 * Returns the size of the current HashMap
	 * @author Xavier Bouchenard
	 * @return	the HashMap size
	 */
	public static int getMapSize() {
		return mapADEH.size();
	}
	
	/**
	 * Returns the ADE map of courses lists build previously to write in the serialized file
	 * @author Xavier Bouchenard
	 * @return		HashMap which contains for each department a list of courses sorted per increasing starting time
	 */
	public HashMap<String, TreeMap<Float, ArrayList<Course>>> getmapADE() {
		return mapADEH;
	}
	
	/**
	 * Returns the URLs map 
	 * @author Xavier Bouchenard
	 * @return	A map of URLs for each department
	 */
	public HashMap<String, String> getmapURLs() {
		return lURL;
	}
	
	/**
	 * Returns the input port of the TCP server
	 * @author Xavier Bouchenard
	 * @return		Input server port for TCP connection attempt
	 */
	public int getConnectionPort() {
		return conPort;
	}
	
	/**
	 * Returns the IP address of the host TCP server machine
	 * @author Xavier Bouchenard
	 * @return		IP address
	 */
	public String getIPAddress() {
		return IPAddress;
	}
	
	public boolean getStopValue() {
		return StopValue;
	}
}

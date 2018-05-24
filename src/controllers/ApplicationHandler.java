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

public final class ApplicationHandler implements Serializable{
	/**
	 * Value declared for the serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private String IPAddress = null;
	
	private int conPort = 0;
	
	/**
	 * Stores the daily timetable for each department added by the user
	 */
	private static HashMap<String, TreeMap<Float, ArrayList<Course>>> mapADEH = null;
	
	private static HashMap<String, String> lURL = null;
	
	/**
	 * Important to stop the TimeHandler thread
	 */
	private static boolean StopValue = false;
	
	/**
	 * Constructor of the ApplicationHandler class
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

		if (mapADEH.size() > 0) {
			SendTCPCourses();
		}
	}
	
	public ApplicationHandler(String address, int port, HashMap<String, TreeMap<Float, ArrayList<Course>>> map, 
			HashMap<String, String> urls) {
		IPAddress = address;
		conPort = port;
		mapADEH = map;
		lURL = urls;
		
		StopValue = false;
		TCPClientSocket.setSocketInfo(IPAddress, conPort);
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
	
	private static void SendDataSize(int size) {
		try {
			TCPClientSocket.OpenSocketConnection();
			TCPClientSocket.SendDataSize(size);
			TCPClientSocket.closeSocketConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void showParamSocket() {
		System.out.println("IP Address: " + IPAddress + "\t Port: " + conPort);
		if (mapADEH != null) {
			if (mapADEH.isEmpty()) System.out.println("La map de listes de cours est vide");
		}
		else System.out.println("La map de listes de cours est nulle\t=> pas sérialisée !");
		if (lURL != null) {
			if (lURL.isEmpty())	System.out.println("La map avec les URLs des fichiers ADE est vide");
		}
		else System.out.println("La map avec les URLs des fichiers ADE est nulle\t=> pas sérialisée !");		
		
		System.out.println("Valeur de la variable d'arret: " + StopValue);
	}
	
	/**
	 * Returns the size of the current HashMap
	 * @author Xavier Bouchenard
	 * @return	the HashMap size
	 */
	public static int getMapSize() {
		return mapADEH.size();
	}
	
	public HashMap<String, TreeMap<Float, ArrayList<Course>>> getmapADE() {
		return mapADEH;
	}
	
	public HashMap<String, String> getmapURLs() {
		return lURL;
	}
	
	public int getConnectionPort() {
		return conPort;
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	public boolean getStopValue() {
		return StopValue;
	}
}

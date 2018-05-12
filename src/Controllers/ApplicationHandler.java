package Controllers;

import SocketService.TCPClientSocket;
import Controllers.ADEHandler;
import Model.Course;

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

	/**
	 * Object of the class TCPClientSocket initialized at null
	 */
	private static TCPClientSocket socket = null;
	
	/**
	 * Stores the daily timetable for each department added by the user
	 */
	private static HashMap<String, ADEHandler> mapADEH = null;
	
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
		
		String IPAddress = sc.nextLine();
		System.out.println("Please enter the connection port now");
		int port = sc.nextInt();
		
		socket = new TCPClientSocket(IPAddress, port);
		mapADEH = new HashMap<String, ADEHandler>();

		//new Thread(new TimeHandler()).start();
		if (mapADEH.size() > 0) {
			SendTCPCourses();
		}
	}
	
	/**
	 * Adds a new timetable database for a given department and will be loaded from a given url
	 * @author Xavier Bouchenard
	 * @param dptName		Name of the department to add
	 * @param urlTimeTB		URL of an online ADE file related to this department
	 */
	public void AddNewTimeTblDB(String dptName, String urlTimeTB) {
		if (!mapADEH.containsKey(dptName)) {
			ADEHandler ade = new ADEHandler(dptName, urlTimeTB);
			ade.GenerateTimeTableOfDay();
			mapADEH.put(dptName, ade);	
			SendTCPCoursesList(ade);
					
		}
		else System.out.println("This timetable database already exists");		
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
		for (Entry<String, ADEHandler> mapEntry : mapADEH.entrySet()) {
			mapEntry.getValue().UpdateTimeTable();
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
		mapADEH.remove(dbName);
		
		ADEHandler ade = new ADEHandler(dbName, newURL);
		ade.GenerateTimeTableOfDay();
		
		if (!mapADEH.containsKey(dbName)) {
			mapADEH.put(dbName, ade);
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
		}
	}
	
	/**
	 * Sends the timetable of courses for the current day
	 * @author Xavier Bouchenard
	 */
	public static void SendTCPCourses() {
		if (mapADEH.size() > 0) {
			socket.OpenSocketConnection();
		
			for (Entry<String, ADEHandler> ade : mapADEH.entrySet()) {
				TreeMap<Float, ArrayList<Course>> map = ade.getValue().getDailyTimetable();
				socket.sendData(ade.getKey(), map);
			}
			socket.closeSocketConnection();
		}
		else System.out.println("No database is stored");
	}
	
	/**
	 * Sends a courses list for a specific department
	 * @author Xavier Bouchenard
	 * @param ade	Courses list of this object to send
	 */
	private void SendTCPCoursesList(ADEHandler ade) {
		socket.OpenSocketConnection();
		
		TreeMap<Float, ArrayList<Course>> list = null;
		list = ade.getDailyTimetable();
		socket.sendData(ade.getdptName(), list);
		socket.closeSocketConnection();
	}
	
	/**
	 * Returns the size of the current HashMap
	 * @author Xavier Bouchenard
	 * @return	the HashMap size
	 */
	public static int getMapSize() {
		return mapADEH.size();
	}
}

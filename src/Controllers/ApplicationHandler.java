package Controllers;

import SocketService.TCPClientSocket;

import java.util.HashMap;

public final class ApplicationHandler{
	/**
	 * Object of the class TCPClientSocket initialized at null
	 */
	@SuppressWarnings("unused")
	private TCPClientSocket socket = null;
	
	/**
	 * Stores the daily timetable for each department added by the user
	 */
	private HashMap<String, ADEHandler> mapADEH;
	
	/**
	 * Constructor of the ApplicationHandler class
	 */
	public ApplicationHandler() {
		socket = new TCPClientSocket();
		mapADEH = new HashMap<String, ADEHandler>();
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
		}
		else System.out.println("This timetable database already exists");		
	}
	
	/**
	 * Returns an ADEHandler object related to a department name passed in parameter
	 * @author Xavier Bouchenard
	 * @param dptName	Name of the department
	 * @return			ADEHandler object associated with the department name
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ADEHandler GetDailyTimeTable(int dptName) {
		if (mapADEH.containsKey(dptName)) {
			return mapADEH.get(dptName);
		}
		else return null;
	}
	

}

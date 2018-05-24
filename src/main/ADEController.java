package main;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import controllers.ApplicationHandler;
import controllers.Deserialization;
import controllers.Serialization;

/**
 * Handles the actions of the user and executes the commands
 * @author Xavier Bouchenard
 */
public class ADEController {
	/**
	 * Reads the keyboard inputs done by the user
	 */
	private Scanner sc = null;
	
	/**
	 * Handles the actions of the user
	 */
	private ApplicationHandler appli = null;
	
	/**
	 * Serializes the timetable databases which already exist 
	 */
	private Serialization serializer = null;
	
	/**
	 * 	Deserializes an ApplicationHandler object in a file
	 */
	private Deserialization deserializer = null;
	
	/**
	 * Constructor of the ADEController class
	 * @author Xavier Bouchenard
	 */
	public ADEController() {
		sc = new Scanner(System.in);
		deserializer = new Deserialization();
		serializer = new Serialization();
			
		appli = deserializer.toDeserialize();
		if (appli == null)	appli = new ApplicationHandler();
		else {
			appli.setAppliStop(false);
			ApplicationHandler.UpdateTimeTable();
		}
		
	}
	
	/**
	 * Main method which is always running and asks for an operation to do
	 * @author Xavier Bouchenard
	 */
	public void launch() {
		boolean flag = true;
		int choice;
		
		System.out.println("/*************\tADE Sources Handler Application\t*************/\n");
		
		while (flag) {
			Actions();
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					AddTimeTableDB();	
					break;
				case 2:  
					ChangeElementInDB();
					break;
				case 3:  
					RemoveTimetableDB();
					break;
				case 4:  
					ShowTimetablesNames();
					break;
				case 5:
					ShowParamSocket(appli);
					break;
				case 0: 
					System.out.println("Closing the application ...\n");
					flag = false;
					sc.close();
					appli.setAppliStop(true);
					toSerialize(appli);					
					break;
				default:
					System.out.println(choice + " is not a valid command (out of possible actions)\n");
					break;
			}			
		}
	}
	
	/**
	 * Displays the actions that the user is allowed to ask
	 * @author Xavier Bouchenard
	 */
	private void Actions() {
		System.out.println("Please choose between all of these actions:");
		System.out.println("\t1: Add a new timetable database");
		System.out.println("\t2: Change the URL of an existing timetable database");
		System.out.println("\t3: Remove an existing timetable database");
		System.out.println("\t4: Show all the existing timetable databases names");
		System.out.println("\t5: Show socket information");
		System.out.println("\t0: Close the application\n");
	}
	
	/**
	 * Prepares the elements to pass in parameter for a timetable database creation
	 * @author Xavier Bouchenard
	 */
	private void AddTimeTableDB() {
		String dptName = null;
		String urlTimeTblDpt = null;
		boolean tries = true;
		
		System.out.println("Please enter the name of the department as the syntax declared below:");
		System.out.println("ex: DI, DMS,...");
		
		// Creates a loop while the string typed is empty
		while (tries) {
			dptName = sc.nextLine();
			if (dptName.isEmpty())	System.out.println("Please try to set a correct name for the timetable database");
			else	tries = false;
		}
		tries = true;
		
		System.out.println("Please enter the URL of the timetable to link with\n\t=>you can find it when you select the correct folder of the department on the ENT");
		// Creates a loop while the string typed is empty
		while (tries) {
			urlTimeTblDpt = sc.nextLine();
			if (urlTimeTblDpt.isEmpty())	System.out.println("Please try to set the correct URL of the timetable database");
			else	tries = false;
			
		}
		appli.AddNewTimeTblDB(dptName, urlTimeTblDpt);
	}
	
	/**
	 * Prepares the changes of the URL of an existing timetable database and allows it to build the good timetable
	 * @author Xavier Bouchenard
	 */
	private void ChangeElementInDB() {
		System.out.println("Please enter the name of one of the following DBs names to update the associated URL");
		Set<String> setS = appli.getKeysOfHashMap();
		
		if (setS.size() == 0)	System.out.println("No timetable DB was found, no operation can be done");
		else {
			Iterator<String> it = setS.iterator();
			while (it.hasNext()) {
				System.out.println("\t- " + it.next());
			}
			
			String chName = sc.nextLine();
			System.out.println("\nPlease enter now the new URL of the timetable DB\n\t just copy and paste the one on the ENT related to this department");
			
			String newURL = sc.nextLine();
			appli.UpdateURL(chName, newURL);
			System.out.println("The system applied the changes.");
		}		
	}
	
	/**
	 * Removes a specific timetable database from the HashMap in ApplicationHandler
	 * @author Xavier Bouchenard
	 */
	private void RemoveTimetableDB() {
		System.out.println("Please enter the name of one of the following DBs names to remove");
		Set<String> setS = appli.getKeysOfHashMap();
		
		if (setS.size() == 0)	System.out.println("No timetable DB was found, no operation can be done");
		else {
			Iterator<String> it = setS.iterator();
			while (it.hasNext()) {
				System.out.println("\t- " + it.next());
			}
			sc.nextLine();
			System.out.println("Which one do you want to remove ?");
			String dbName = sc.nextLine();
			appli.RemoveTimetableDB(dbName);
			
			setS = appli.getKeysOfHashMap();
			if (setS.size() == 0)	System.out.println("No timetable DB left after the removal");
			else {
				it = setS.iterator();
				System.out.println("After the remove, there are the last timetables stored");
				while (it.hasNext() ) {
					System.out.println("\t- " + it.next());
				}
			}
		}
	}
	
	/**
	 * Displays the timetable database stored
	 * @author Xavier Bouchenard
	 */
	private void ShowTimetablesNames() {
		Set<String> setS = appli.getKeysOfHashMap();
		
		if (setS.size() == 0) 	System.out.println("There is no database stored");		
		else {
			System.out.println("These are the timetable databases stored which are available");
			Iterator<String> it = setS.iterator();
			
			while (it.hasNext()) {
				System.out.println("\t- " + it.next());
			}
		}
	}
	
	/**
	 * Calls the serialization method to serialize the object
	 * @author Xavier Bouchenard
	 * @param appli	ApplicationHandler object to serialize
	 */
	private void toSerialize(ApplicationHandler appli) {
		serializer.writeData(appli);
	}
	
	private void ShowParamSocket(ApplicationHandler appli) {
		appli.showParamSocket();
	}
}

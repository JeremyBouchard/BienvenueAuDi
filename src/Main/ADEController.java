package Main;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Controllers.ApplicationHandler;

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
	 * Instance of the actions handler
	 */
	private ApplicationHandler appli = null;
	
	/**
	 * Constructor of the ADEController class
	 * @author Xavier Bouchenard
	 */
	public ADEController() {
		sc = new Scanner(System.in);
		appli = new ApplicationHandler();
	}
	
	/**
	 * Main method which is always running and asks for an operation to do
	 * @author Xavier Bouchenard
	 */
	public void launch() {
		boolean flag = true;
		int choice;
		
		System.out.println("/*************\tADE Sources Handler Application\t*************/\n\n");
		
		while (flag) {
			Actions();
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					AddTimeTableDB();					
					//appli.;
					break;
				case 2:  //appli.;
					break;
				case 3:  //appli.;
					break;
				case 4:  //appli.;
					break;
				case 0: 
					System.out.println("Closing the application ...\n");
					flag = false;
					// Sérialisation des informations des "databases stockées" dans applicationHandler
					// Classe ADEHandler à sérialiser => implements Serializable (faire l'import) + automatic generated id à inclure
					//appli.;
					sc.close();
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
		System.out.println("\t2: Change an element of an existing timetable database");
		System.out.println("\t3: Remove an existing timetable database");
		System.out.println("\t4: Show all the existing timetable databases names");
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
			try {
				dptName = sc.nextLine();
				tries = false;
			} catch(NoSuchElementException e) {
				System.out.println("Please try to set a correct name for the timetable database");
			}
		}
		tries = true;
		
		System.out.println("Please enter the URL of the timetable to link with\n\t=>you can find it when you select the correct folder of the department on the ENT");
		// Creates a loop while the string typed is empty
		while (tries) {
			try {
				urlTimeTblDpt = sc.nextLine();
				tries = false;
			}  catch(NoSuchElementException e) {
				System.out.println("Please try to set the correct URL of the timetable database");
			}
		}
		appli.AddNewTimeTblDB(dptName, urlTimeTblDpt);
	}
}

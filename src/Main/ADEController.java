package Main;

import java.util.Scanner;

import Controllers.ApplicationHandler;

public class ADEController {
	private Scanner sc = null;
	
	public ADEController() {
		new Thread(new ApplicationHandler()).start();
	}
	
	public void launch() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int choice;
		
		System.out.println("/*************\tADE Sources Handler Application\t/*************\n\n");
		
		while (flag) {
			Actions();
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: //appli.;
					break;
				case 2: //appli.;
					break;
				case 3: //appli.;
					break;
				case 4: //appli.;
					break;
				case 0: 
					System.out.println("Closing of the application...\n");
					flag = false;
					// Sérialisation des informations des "databases stockées" dans applicationHandler
					// Classe ADEHandler à sérialiser => implements Serializable (faire l'import) + automatic generated id à inclure
					//appli.;
					sc.close();
					break;
				default:
					System.out.println(choice + " is not a valid command (out of possible actions)\n\n");					
					Actions();
					break;
			}			
		}
	}
	
	/**
	 * 
	 */
	private void Actions() {
		System.out.println("Please choose between all of these actions:");
		System.out.println("\t1: Add a new timetable database");
		System.out.println("\t2: Change an element of an existing timetable database");
		System.out.println("\t3: Remove an existing timetable database");
		System.out.println("\t4: Show all of the existing timetable databases");
		System.out.println("\t0: Close the application\n");
	}
}

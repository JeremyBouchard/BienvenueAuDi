package Main;

import java.util.Scanner;

import Controllers.ApplicationHandler;

public class ADEController {
	private Scanner sc = null;
	private ApplicationHandler appli = null;
	
	public ADEController() {
		sc = new Scanner(System.in);
		appli = new ApplicationHandler();
	}
	
	public void launch() {
		boolean flag = true;
		int choice;
		
		System.out.println("/*************\tADE Sources Handler Application\t*************/\n\n");
		
		while (flag) {
			Actions();
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					System.out.println("Ok, j'ai bien reçu " + choice);
					//appli.;
					break;
				case 2: 
					System.out.println("Ok, j'ai bien reçu " + choice); //appli.;
					break;
				case 3: 
					System.out.println("Ok, j'ai bien reçu " + choice); //appli.;
					break;
				case 4: 
					System.out.println("Ok, j'ai bien reçu " + choice); //appli.;
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

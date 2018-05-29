package main;

import controllers.TimeHandler;

/**
 * Main of the Sources Application
 * @author Xavier
 *
 */
public class Main {

	public static void main(String[] args) {
		ADEController ade = new ADEController();	
		// Starts the system
		ade.launch();
		// Starts the time handler
		new Thread(new TimeHandler()).start();
	}
}

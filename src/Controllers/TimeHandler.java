package Controllers;

import java.time.LocalDateTime;

public class TimeHandler implements Runnable{
	/**
	 * Reference of the current time
	 */
	private LocalDateTime date = null;
	/**
	 * State of this variable will change if the time delay of 2 hours will be elapsed
	 */
	private static boolean ValUpdate = false;
	
	/**
	 * Constructor of the class
	 */
	public TimeHandler() {
		date = LocalDateTime.now();
	}
	
	/**
	 * @author Xavier Bouchenard
	 * Runs a thread which will check the time duration is elapsed and do the correct actions
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
	
	/**
	 * @author Xavier Bouchenard
	 * @return ValUpdate	State of the update to do
	 * Returns the state of the variable which shows if the update of the file must be done or not
	 */
	public static boolean getValUpdate() {
		return ValUpdate;
	}
}

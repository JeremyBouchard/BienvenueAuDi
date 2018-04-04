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
	 * State of this variable will change if the current day is different from the day of the reference object LocalDateTime stored
	 */
	private static boolean DayChanged = false;
	
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
		while (true) {
			
			if (DurationTimeElapsed()) {
				date = LocalDateTime.now();
				ValUpdate = true;
			}
			else if (HasDayChanged()) {
				date = LocalDateTime.now();
				DayChanged = true;
			}
				
			ValUpdate = false;
			DayChanged = false;
		}
	}	
	
	/**
	 * @author Xavier Bouchenard
	 * @return ValUpdate	State of the update to do
	 * Returns the state of the variable which shows if the update of the file must be done or not
	 */
	public static boolean getValUpdate() {
		return ValUpdate;
	}
	
	/**
	 * @author Xavier Bouchenard
	 * @return	DayChanged
	 * Returns the state of the variable if the current day has changed from the previous value stored
	 */
	public static boolean getDayChanged() {
		return DayChanged;
	}
	
	/**
	 * @author Xavier Bouchenard
	 * @return result	State of the elapsed time
	 * Measures the elapsed time and returns the result of the comparison between 
	 * 		the current date time and the previous value stored more 2 hours
	 */
	private boolean DurationTimeElapsed() {
		boolean result;
		
		LocalDateTime now = LocalDateTime.now();
		if ((now.getHour() >= (date.getHour()+2)) && (now.getMinute() >= date.getMinute()))
			result = true;
		else result = false;
		
		return result;
	}
	
	/**
	 * @author Xavier Bouchenard
	 * @return day State of the day change
	 * Returns the state of the day change between the current value of day and the previous stored value
	 */
	private boolean HasDayChanged() {
		boolean day;
		
		if (LocalDateTime.now().getDayOfYear() == date.getDayOfYear())
			day = true;
		else day = false;
		
		return day;
	}
}

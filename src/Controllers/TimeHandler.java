package Controllers;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeHandler implements Runnable, Serializable{
	/**
	 * Default value to serialize this class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference of the current time
	 */
	private LocalDateTime date = null;
	
	/**
	 * Constructor of the class
	 */
	public TimeHandler() {
		date = LocalDateTime.now();
	}
	
	/**
	 * Runs a thread which will check the time duration is elapsed and do the correct actions
	 * @author Xavier Bouchenard
	 */
	@Override
	public void run() {
		boolean value = true;	
		
		while (value) {			
			if (DurationTimeElapsed() && (ApplicationHandler.getMapSize() > 0)) {
				date = LocalDateTime.now();
				ApplicationHandler.UpdateTimeTable();
				ApplicationHandler.SendTCPCourses();
			}
			else if (IsANewDay() && (ApplicationHandler.getMapSize() > 0)) {
				date = LocalDateTime.now();
				ApplicationHandler.UpdateTimeTable();
				ApplicationHandler.SendTCPCourses();
			}
			
			if (ApplicationHandler.AppliStop()) {
				value = false;
			}
		}
	}
	
	/**
	 * Measures the elapsed time and returns the result of the comparison between 
	 * 		the current date time and the previous value stored more 2 hours
	 * @author Xavier Bouchenard
	 * @return result	State of the elapsed time
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
	 * Returns the state of the day change between the current value of day and the previous stored value
	 * @author Xavier Bouchenard
	 * @return day State of the day change
	 */
	private boolean IsANewDay() {
		boolean day;
		
		if (LocalDateTime.now().getDayOfYear() == date.getDayOfYear())
			day = true;
		else day = false;
		
		return day;
	}
}

package Controllers;

import java.time.LocalDateTime;

public class TimeHandler implements Runnable{
	private LocalDateTime date = null;
	
	public TimeHandler() {
		date = LocalDateTime.now();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
}

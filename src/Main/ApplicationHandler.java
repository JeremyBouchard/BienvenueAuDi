package Main;

import Controllers.*;
import SocketService.TCPClientSocket;

public class ApplicationHandler implements Runnable {
	/**
	 * Object of the class TCPClientSocket initialized at null
	 */
	private TCPClientSocket socket = null;
	
	/**
	 * Constructor of the ApplicationHandler class
	 */
	public ApplicationHandler() {
		socket = new TCPClientSocket();
		new Thread(new TimeHandler()).start();
		//new Thread(new ADEHandler()).start();
	}
	
	/**
	 * Launched when a thread will be called and will work while the program is not stop
	 */
	@Override
	public void run() {
		while (true) {
			if (TimeHandler.getValUpdate()) {
				// traitement � faire dans le cas o� les 2H sont �coul�s
			}
			else if(TimeHandler.getDayChanged()) {
				// traitement a faire en cas de changement de jour
			}
		}
		
	}

}

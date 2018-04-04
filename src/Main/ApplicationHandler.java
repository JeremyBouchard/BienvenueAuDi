package Main;

import Controllers.ADEHandler;
import Controllers.TimeHandler;
import SocketService.TCPClientSocket;

public class ApplicationHandler implements Runnable {
	private TCPClientSocket socket = null;
	private ADEHandler ade = null;
	
	public ApplicationHandler() {
		socket = new TCPClientSocket();
		ade = new ADEHandler();
		new Thread(new TimeHandler()).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

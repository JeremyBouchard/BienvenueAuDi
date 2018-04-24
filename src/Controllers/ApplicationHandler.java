package Controllers;

import SocketService.TCPClientSocket;

public final class ApplicationHandler{
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
	}

}

package Main;

import Controllers.TimeHandler;

public class Main {

	public static void main(String[] args) {
		ADEController ade = new ADEController();
		ade.launch();

		new Thread(new TimeHandler()).start();
	}
}

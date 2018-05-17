package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

/**
 * Deserializes an ApplicationHandler object which was serialized at the end of the program
 * @author Xavier
 *
 */
public class Deserialization {
	/**
	 * Contains the serialized object to deserialize
	 */
	private FileInputStream file = null;
	
	/**
	 * Opens an input stream and gets the object to deserialize
	 */
	private ObjectInputStream ois = null;
	
	/**
	 * 	Constructor of the Deserialization class
	 */
	public Deserialization() {
		try {
			file = new FileInputStream("dataSaves.txt");
			try {
				ois = new ObjectInputStream(file);
			} catch (IOException e) {
				System.out.println("Unable to open an input stream for the deserialization");
			}
		} catch (FileNotFoundException e) {
			System.out.println("This file does not exist");
		}
	}
	
	@SuppressWarnings("finally")
	public ApplicationHandler toDeserialize() {
		ApplicationHandler appli = null;
		try {
			appli = (ApplicationHandler) ois.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("The ApplicationHandler class has not been found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to deserialize this file");
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Unable to close correctly the file");
				}
			}
			return appli;
		}
	}
}

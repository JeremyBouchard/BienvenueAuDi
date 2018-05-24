package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import model.Course;

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
		
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ApplicationHandler toDeserialize() {
		ApplicationHandler appli = null;
		int conPort = 0;
		String IPAddress = null;
		HashMap<String, TreeMap<Float, ArrayList<Course>>> map = null;
		HashMap<String, String> lURL = null;
		
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
		
		try {
			IPAddress = ois.readUTF();

			conPort = ois.readInt();

			map = (HashMap<String, TreeMap<Float, ArrayList<Course>>>) ois.readObject();
			
			lURL = (HashMap<String, String>) ois.readObject();
			
			appli = new ApplicationHandler(IPAddress, conPort, map, lURL);
			
			System.out.println("The deserialization has been done");
			
			} catch (ClassNotFoundException e) {
			System.out.println("The ApplicationHandler class has not been found");
		} catch (IOException e) {
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

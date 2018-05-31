package model;

import java.io.Serializable;

/**
 * 
 * Class representing a professor
 * @author MariamKonate, Jérémy Bouchard
 * 
 */
public class Professor {


	private String lastname;

	private String firstname;
	
	private Room  defaultRoom;
	
	
	/**
	 * Constructor
	 * @param lastname lastanme of the professor
	 * @param firstname firstname of the professor
	 * @param defaultRoom the name of the default room (bureau) of the professor
	 */
	public Professor(String lastname,String firstname,Room defaultRoom)
	{
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setDefaultRoom(defaultRoom);
	}
	
	/**
	 * Constructor
	 */
	public Professor()
	{
		
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the defaultRoom
	 */
	public Room getDefaultRoom() {
		return defaultRoom;
	}

	/**
	 * @param defaultRoom the defaultRoom to set
	 */
	public void setDefaultRoom(Room defaultRoom) {
		this.defaultRoom = defaultRoom;
	}

}

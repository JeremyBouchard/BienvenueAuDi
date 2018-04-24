package model;

import java.io.Serializable;

/**
 * Class representing a professor
 * @author MariamKonate, JeremyBouchard
 *
 */
public class Professor  implements Serializable{

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private String lastname;

	private String firstname;

	private int salleId;
	
	private int defaultRoom;
	
	/**
	 * Constructor
	 * @param lastName
	 * @param firstname
	 */
	public Professor(String lastName,String firstname)
	{
		this.setLastname(lastName);
		this.setFirstname(firstname);
	}
	
	/**
	 * Constructor
	 * @param lastname
	 * @param firstname
	 * @param salleId
	 * @param defaultRoom
	 */
	public Professor(String lastname,String firstname, int salleId,int defaultRoom)
	{
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setSalleId(salleId);
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
	 * @return the salleId
	 */
	public int getSalleId() {
		return salleId;
	}

	/**
	 * @param salleId the salleId to set
	 */
	public void setSalleId(int salleId) {
		this.salleId = salleId;
	}

	/**
	 * @return the defaultRoom
	 */
	public int getDefaultRoom() {
		return defaultRoom;
	}

	/**
	 * @param defaultRoom the defaultRoom to set
	 */
	public void setDefaultRoom(int defaultRoom) {
		this.defaultRoom = defaultRoom;
	}

}

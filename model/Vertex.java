package model;

import java.io.Serializable;

/**
 * Class representing a Vertex
 * @author MariamKonate, JeremyBouchard
 *
 */
public class Vertex  implements Serializable{

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
	private String name;
	private Type type;
	private int etage;

	
	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param type
	 * @param etage
	 */
	public Vertex(int id, String name, Type type, int etage) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.setEtage(etage);
	}

	
	/**
	 * Constructor
	 * @param name
	 * @param type
	 */
	public Vertex(String name, Type type) {
		this.setName(name);
		this.setType(type);
		id++;
	}
	
	/**
	 * Constructor
	 */
	public Vertex()
	{
		id++;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public  int getId() {
		return id;
	}

	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}


	/**
	 * @return the etage
	 */
	public int getEtage() {
		return etage;
	}


	/**
	 * @param etage the etage to set
	 */
	public void setEtage(int etage) {
		this.etage = etage;
	}
	
	

}

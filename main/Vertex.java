package main;

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
	private static int id;
	private String name;
	private Type type;
	
	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param type
	 */
	public Vertex(String name, Type type) {
		this.setName(name);
		this.setType(type);
		
	}
	
	/**
	 * Constructor
	 */
	public Vertex()
	{
		
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
	public static int getId() {
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
	
	

}

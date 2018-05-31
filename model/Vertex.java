package model;


/**
 * Class representing a Vertex
 * @author Mariam Konate, Jérémy Bouchard
 *
 */
public class Vertex  {
	
	private  int id;
	private String name;
	private Type type;
	private int etage;
	
	
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
	public  int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

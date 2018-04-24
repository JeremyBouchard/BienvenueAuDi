package model;

/**
 * Room usually a classRoom or office extending a vertex
 * @author MariamKonate,JeremyBouchard
 *
 */
public class Room extends Vertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Room(int id, String name, Type type, String number) {
		super(id, name, type);
		this.number = number;
	}

	private String number;

	private Professor professorList;
	/**
	 * Constructor
	 * @param name
	 * @param type
	 */
	public Room(String name, Type type) {
		super(name, type);
	}

	/**
	 * Constructor
	 */
	public Room()
	{
		super();
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the professorList
	 */
	public Professor getProfessorList() {
		return professorList;
	}

	/**
	 * @param professorList the professorList to set
	 */
	public void setProfessorList(Professor professorList) {
		this.professorList = professorList;
	}

	

}

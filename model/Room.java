package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Room usually a classRoom or office extending a vertex
 * adding a list of professor as field
 * @author MariamKonate,JeremyBouchard
 *
 */
public class Room extends Vertex {


	private String number;

	private List<Professor> professorList =new ArrayList<Professor>();

	
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
	public List<Professor> getProfessorList() {
		return professorList;
	}

	/**
	 * @param professorList the professorList to set
	 */
	public void setProfessorList(List<Professor> professorList) {
		this.professorList = professorList;
	}

	

}

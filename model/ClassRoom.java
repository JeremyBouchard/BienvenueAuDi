package model;

/**
 * Class representing a classRoom extending a room
 * adding the string field studentsPromotion
 * @author Mariam Konate, Jérémy Bouchard
 *
 */
public class ClassRoom extends Room {

	private String studentsPromotion;
	
	public ClassRoom() {
		super();
	}

	/**
	 * @return the studentsPromotion
	 */
	public String getStudentsPromotion() {
		return studentsPromotion;
	}

	/**
	 * @param studentsPromotion the studentsPromotion to set
	 */
	public void setStudentsPromotion(String studentsPromotion) {
		this.studentsPromotion = studentsPromotion;
	}

}

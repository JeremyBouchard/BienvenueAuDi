package main;

/**
 * Class representing a classorRom
 * @author MariamKonate
 *
 */
public class ClassRoom extends Room {

	public ClassRoom(int id, String name, Type type) {
		super(id, name, type, name);
	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param name
	 * @param type
	 */
	public ClassRoom(String name, Type type) {
		super(name, type);
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

	private String studentsPromotion;

}

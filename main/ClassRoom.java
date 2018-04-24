package Model;

/**
 * Class representing a classorRom
 * @author MariamKonate
 *
 */
public class ClassRoom extends Room {

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

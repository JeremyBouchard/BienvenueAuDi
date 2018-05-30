package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *	An object of this class will be built once an instance of a course will be read in the 
 *		ADE file.
 * @author Xavier Bouchenard
 */
public class Course implements Serializable{

	/**
	 * Value declared for the serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the course read in the ADE file
	 */
	private String ClassroomName;
	
	/**
	 * List of professor for the course read in the ADE file
	 * 		in the case of an exam
	 */
	private ArrayList<String> ProfessorName;
	
	/**
	 * Start time of the course read in the ADE file
	 */
	private float StartTimeC;
	
	/**
	 * End time of the course read in the ADE file
	 */
	private float EndTimeC;
	
	/**
	 * Stundent promotion
	 */
	private String StudentPromo = null;
	
	/**
	 * Constructor of the Course class
	 * @author Xavier Bouchenard
	 * @param classname		Name of the classroom written in the file
	 * @param PName			Name of the professor assigned for this course
	 * @param timeArray		Start time of the course
	 * @param timeArray2	End time of the course
	 * @param promo			Promotion year
	 */
	public Course(String classname, String PName, float timeArray, float timeArray2, String promo) {
		ClassroomName = classname;
		ProfessorName = new ArrayList<String>();
		ProfessorName.add(PName);
		StartTimeC = timeArray;
		EndTimeC = timeArray2;
		StudentPromo = promo;
	}
	
	/**
	 * Constructor of the Course class
	 * @author Xavier Bouchenard
	 * @param classname		Name of the classroom written in the file
	 * @param PName			List of professors names assigned for this course
	 * @param timeArray		Start time of the course
	 * @param timeArray2	End time of the course
	 * @param promo			Promotion year
	 */
	public Course(String classname, ArrayList<String> PName, float timeArray, float timeArray2, String promo) {
		ClassroomName = classname;
		ProfessorName = PName;
		StartTimeC = timeArray;
		EndTimeC = timeArray2;
		StudentPromo = promo;
	}
	
	/**
	 * Returns the name of the classroom for this course
	 * @author Xavier Bouchenard
	 * @return	Name of the classroom
	 */
	public String getClassroomName() {
		return ClassroomName;
	}
	
	/**
	 * Returns the name of the professor for this course
	 * @author Xavier Bouchenard
	 * @return	List of professor names
	 */
	public ArrayList<String> getProfName() {
		return ProfessorName;
	}
	
	/**
	 * Returns the start time of this course
	 * @author Xavier Bouchenard
	 * @return	Start time of the course
	 */
	public float getStartTime() {
		return StartTimeC;
	}
	
	/**
	 * Returns the end time of this course
	 * @author Xavier Bouchenard
	 * @return	End time of the course
	 */
	public float getEndTime() {
		return EndTimeC;
	}
	
	/**
	 * Gets the student promotion concerned by this course
	 * @return	Promotion year
	 */
	public String getStudentPromo() {
		return StudentPromo;
	}
	
}

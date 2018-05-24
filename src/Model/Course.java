package Model;

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
	private ArrayList<String> ClassroomName;
	
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
	 * ENd time of the course read in the ADE file
	 */
	private float EndTimeC;
	
	/**
	 * Constructor of the Course class
	 * @author Xavier Bouchenard
	 * @param classname		Name of the classroom written in the file
	 * @param PName			Name of the professor assigned for this course
	 * @param timeArray		Start time of the course
	 * @param timeArray2	End time of the course
	 */
	public Course(String classname, String PName, float timeArray, float timeArray2) {
		ClassroomName =  new ArrayList<String>();
		ClassroomName.add(classname);
		ProfessorName = new ArrayList<String>();
		ProfessorName.add(PName);
		StartTimeC = timeArray;
		EndTimeC = timeArray2;
	}
	
	/**
	 * Constructor of the Course class
	 * @author Xavier Bouchenard
	 * @param classname		Name of the classroom written in the file
	 * @param PName			List of professors names assigned for this course
	 * @param timeArray		Start time of the course
	 * @param timeArray2	End time of the course
	 */
	public Course(ArrayList<String> classname, ArrayList<String> PName, float timeArray, float timeArray2) {
		ClassroomName = classname;
		ProfessorName = PName;
		StartTimeC = timeArray;
		EndTimeC = timeArray2;
	}
	
	/**
	 * Returns the name of the classroom for this course
	 * @author Xavier Bouchenard
	 * @return	Name of the classroom
	 */
	public ArrayList<String> ClassName() {
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
	
}

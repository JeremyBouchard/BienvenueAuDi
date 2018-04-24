package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.TreeMap;

import Model.Course;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

/**
 * Used to read and build the daily timetable specific for a department
 * 		In a case of the application exiting, this class could be serialized to store the data in a file which will be read at the PC's restart
 * @author Xavier Bouchenard
 *
 */
public class ADEHandler implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the department written by the user
	 */
	@SuppressWarnings("unused")
	private String dptName = null;
	
	/**
	 * URL generated thanks to a String which contains the URL of the ADE file from ENT
	 */
	private URL urlTimeTableDB = null;
	
	/**
	 * Stores the data of the url connection
	 */
	private URLConnection conn = null;	
	
	/**
	 * Calendar generated on which the courses of the day to get
	 */
	private Calendar ADEcal = null;

	/**
	 * Type which builds a calendar from an input stream
	 */
	private CalendarBuilder Calbuilder;
	
	/**
	 * Stores the courses depending on the starting time
	 */
	private TreeMap<Float, Course> mapCourses = null;
		
	/**
	 * Constructor of the class
	 * @author Xavier Bouchenard
	 * @param dptName	Name of the department written by the user
	 * @param url		URL of the timetable to get
	 */
	public ADEHandler(String dptName, String url) {
		this.dptName = dptName;
		
		mapCourses = new TreeMap<Float, Course>();
		
		try {
			urlTimeTableDB = new URL(url);
			System.out.println("Calendar building in progress");
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			Calbuilder = new CalendarBuilder();
		}catch (Exception e) {
			System.out.println("The build of the calendar failed");			
		}
		
		loadCalendarFromURL();
	}
	
	/**
	 * Enables the loading of a calendar from an URL
	 * @author Xavier Bouchenard
	 * @exception IOException e		An exception of this type will be throwed if a connection's attempt failed
	 * @exception ParserException e		An exception of this type will be throwed if a building's attempt failed
	 */
	private void loadCalendarFromURL() {
		try {
			// Sets a connection stream thanks to the url passed in argument of the constructor
			conn = urlTimeTableDB.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection failed from this url: " + urlTimeTableDB.toString());
		}
		
		try {
			// Opens the connection stream from the set URLConnection 
			InputStream in = conn.getInputStream();
			try {
				ADEcal = Calbuilder.build(in);
			} catch (ParserException e) {
				e.printStackTrace();
				System.out.println("Error occured while building a new calendar from this url: " 
				+ urlTimeTableDB.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The system failed to open a connection from this url " 
			+ urlTimeTableDB.toString());
		}
		
		System.out.println("The loading of the calendar succeed\n");
	}
	
	/**
	 * Returns the daily timetable which will be used by the server's application
	 * @author 	Xavier Bouchenard
	 * @return	Daily timetable built
	 */
	public TreeMap<Float, Course> GetDailyTimetable() {
		return mapCourses;
	}
	
	/**
	 * Generates the timetable of courses for the department for the current day
	 * @author Xavier Bouchenard
	 */
	public void GenerateTimeTableOfDay() {
		//	Date of the first build of this timetable
		LocalDateTime date = LocalDateTime.now();
		//	Contains the elements needed to identify the current day in the ADE file
		String dateTime = "";
		
		String professor;
		String ClassroomName;
		dateTime +=  date.getYear();
		
		if (date.getMonthValue() < 10)	dateTime +=  "0";
		
		dateTime += date.getMonthValue();
		
		if (date.getDayOfMonth() < 10)	dateTime += "0";
		
		dateTime += date.getDayOfMonth();	
		
		//	for all instances of courses in the ADE file, do ...
		for (Object o : ADEcal.getComponents("VEVENT")) {
			Component c = (Component)o;
			// If the current instance of course is a course planned for the day
			if (c.getProperty("DTSTART").getValue().substring(0, dateTime.length()).contains(dateTime)) {
				professor = FindProfessor(c);
				
				float TimeArray[] = SetTimes(c);
				ClassroomName = FindClassName(c);
				
				Course course = new Course(ClassroomName, professor, TimeArray[0], TimeArray[1]);
				mapCourses.put(TimeArray[0], course);
			}			
		}
	}
	
	/**
	 * Parses the component's specifications to extract the name of the professor from the line 
	 * 		defined by the "SUMMARY" tag in the ADE file
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		String which is the name of the professor for this course
	 */
	private String FindProfessor(Component C) {
		//	If the kind of course does not have an assigned professor ...
		if (!C.getProperty("SUMMARY").getValue().equals("P.Col_Réalisation")) {
			//	Parses the info of the line to extract the professor's name
			String[] res = C.getProperty("DESCRIPTION").getValue().split("\n");
			//	Specific case where more information are written
			if (res[4].equals("LabelCPE"))	return res[5];
			else return res[3];
		}
		else return "No professor";	
	}
	
	/**
	 * Parses the component's specifications to extract the name of the classroom from the line
	 * 		defined by the "LOCATION" tag
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		String which is the name of the classroom of the course
	 */
	private String FindClassName(Component C) {
		//	If the kind of course does not have an assigned classroom
		if (!C.getProperty("SUMMARY").getValue().equals("P.Col_Réalisation")) {
			return C.getProperty("LOCATION").getValue();
		}
		else return "No defined room";
	}
	
	/**
	 * Sets the starting and the ending times of the instance of course read in the ADE file
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		An array of float which corresponds of the starting and the ending time of the course
	 */
	@SuppressWarnings("null")
	private float[] SetTimes(Component C) {
		//	Gets the time from the line which is beginning by the "DTSTART" tag at a specific position
		int begin = Integer.parseInt(C.getProperty("DTSTART").getValue().substring(9, 13));
		//	Gets the time from the line which is beginning by the "DTEND" tag at a specific position	
		int end = Integer.parseInt(C.getProperty("DTEND").getValue().substring(9, 13));
		
		float array[] = null;
		
		array[0] = (float) begin/100;
		array[0] = (float) (array[0] + 2.0);
		array[1] = (float) end/100;
		array[1] = (float) (array[1]+2.0);
		
		return array;
	}
}

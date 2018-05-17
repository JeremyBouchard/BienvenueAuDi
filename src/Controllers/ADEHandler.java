package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import Model.Course;
import net.fortuna.ical4j.data.CalendarBuilder;
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
	 * Value declared for the serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the department written by the user
	 */
	private String dptName = null;
	
	/**
	 * URL generated thanks to a String which contains the URL of the ADE file from ENT
	 */
	private URL urlTimeTableDB = null;
	
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
	private TreeMap<Float, ArrayList<Course>> mapCourses = null;
		
	/**
	 * Constructor of the class
	 * @author Xavier Bouchenard
	 * @param dptName	Name of the department written by the user
	 * @param url		URL of the timetable to get
	 * @throws Exception Exception to catch
	 */
	public ADEHandler(String dptName, String url) throws Exception {
		this.dptName = dptName;
		
		mapCourses = new TreeMap<Float, ArrayList<Course>>();
		
		try {
			urlTimeTableDB = new URL(url);
			System.out.println("Calendar building in progress");
		}catch (MalformedURLException e) {
			throw new Exception("Unable to open the connection with this URL: " + url);
		}
		
		try {
			Calbuilder = new CalendarBuilder();
		}catch (Exception e) {
			throw new Exception("The build of the calendar failed");		
		}
		
		if (urlTimeTableDB != null)	loadCalendarFromURL();
	}
	
	/**
	 * Returns the name of the timetable database (name of the department)
	 * @author Xavier Bouchenard
	 * @return	The name of the timetable database (name of the department)
	 */
	public String getdptName() {
		return dptName;
	}
	
	/**
	 * Enables the loading of a calendar from an URL
	 * @author Xavier Bouchenard
	 * @throws Exception exception to catch
	 */
	private void loadCalendarFromURL() throws Exception {
		InputStream in = null;

		try {
				in = urlTimeTableDB.openStream();
			} catch (IOException e) {
				throw new Exception("Unable to open a connection stream from the set URL. "
						+ "Maybe the machine is not connected to internet.");
			}
			if (in != null) {
				try {
					ADEcal = Calbuilder.build(in);
				} catch (IOException e) {
					throw new Exception("Unable to build a calendar from the declared connection stream.");
				}
				System.out.println("The loading of the calendar succeed\n");
			}		
	}
	
	/**
	 * Returns the daily timetable which will be used by the server's application
	 * @author 	Xavier Bouchenard
	 * @return	Daily timetable built
	 */
	public TreeMap<Float, ArrayList<Course>> getDailyTimetable() {
		return mapCourses;
	}
	
	/**
	 * Generates the timetable of courses for the department for the current day
	 * @author Xavier Bouchenard
	 */
	public void GenerateTimeTableOfDay() {
		String dateTime = getDay();
		mapCourses = BuildTimeTable(dateTime);
	}
	
	/**
	 * Sets an update of the timetable if the courses of the new treemap generated and the old treemap are different
	 * @author Xavier Bouchenard
	 */
	public boolean UpdateTimeTable() {
		String dateTime = getDay();
		TreeMap<Float, ArrayList<Course>> mapTemp = null;
		
		mapTemp = BuildTimeTable(dateTime);
		if (mapCourses.hashCode() != mapTemp.hashCode()) {
			mapCourses.clear();
			mapCourses = mapTemp;
			return true;
		}
		else return false;
	}
	
	public void UpdateURL(String URL) throws Exception {
		mapCourses.clear();
		urlTimeTableDB = null;
		
		try {
			urlTimeTableDB = new URL(URL);
			System.out.println("Calendar building in progress");
		}catch (MalformedURLException e) {
			throw new Exception("Unable to open the connection with this URL:" + URL);
		}
		
		if (urlTimeTableDB != null)	loadCalendarFromURL();
	}
	
	/**
	 * Builds the timetable for the current day 
	 * 		=> Can be used for an update or for the first generation of the timetable of the day
	 * @author Xavier Bouchenard
	 * @param dateTime	Formatted string which contains the info of the day, month, year
	 * @return	A TreeMap object in which the courses are sorted according to a starting time
	 */
	private TreeMap<Float, ArrayList<Course>> BuildTimeTable(String dateTime) {
		String professor;
		String ClassroomName;
		TreeMap<Float, ArrayList<Course>> map = new TreeMap<Float, ArrayList<Course>>();
		ArrayList<Course> lCourse = null;
		
		// for all instances of courses in the ADE file, do ...
			for (Object o : ADEcal.getComponents("VEVENT")) {
				Component c = (Component)o;
				// If the current instance of course is a course planned for the day
				if (c.getProperty("DTSTART").getValue().substring(0, dateTime.length()).contains(dateTime)) {
					professor = FindProfessor(c);
					
					float TimeArray[] = SetTimes(c);
					ClassroomName = FindClassName(c);
					
					Course course = new Course(ClassroomName, professor, TimeArray[0], TimeArray[1]);
					
					if (!map.containsKey(TimeArray[0])) {
						lCourse = new ArrayList<Course>();
						lCourse.add(course);
						map.put(TimeArray[0], lCourse);
					}
					else {
						lCourse = map.get(TimeArray[0]);
						lCourse.add(course);
					}
				}			
			}
			System.out.println("The calendar has been generated");
			return map;
	}
	
	/**
	 * Parses the component's specifications to extract the name of the professor from the line 
	 * 		defined by the "SUMMARY" tag in the ADE file
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		String which contains the name of the professor for this course
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
		
		float array[] = {0.0f, 0.0f};
		
		float minutes = 0.0f;
		
		array[0] = begin/100;
		minutes = begin%100;
		array[0] += minutes/100 + 2;
		
		
		array[1] = end/100;
		minutes = end%100;
		array[1] += minutes/100 + 2;
		
		return array;
	}
	
	/**
	 * Creates a string composed of the information of the current day, month, year
	 * @author Xavier Bouchenard 
	 * @return dateTime		The formatted string
	 */
	private String getDay() {
		//	Date of the first build of this timetable
		LocalDateTime date = LocalDateTime.now();
		//	Contains the elements needed to identify the current day in the ADE file
		String dateTime = "";
		
		dateTime +=  date.getYear();		
		if (date.getMonthValue() < 10)	dateTime +=  "0";
		
		dateTime += date.getMonthValue();		
		if (date.getDayOfMonth() < 10)	dateTime += "0";
		
		dateTime += date.getDayOfMonth();
		
		return dateTime;
	}
}

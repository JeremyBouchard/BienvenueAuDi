package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Course;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

/**
 * Used to read and build the daily timetable specific for a department
 * 		In a case of the application exiting, this class could be serialized to store the data in a file which will be read at the PC's restart
 * @author Xavier Bouchenard
 */
public class ADEHandler {
	private static TreeMap<Float, ArrayList<Course>> mapCourses = null;
	private static URL urlTimeTableDB = null;
	private static CalendarBuilder Calbuilder = null;
	private static Calendar ADEcal = null;
		
	/**
	 * Configures the URL which will be used to get the timetable file
	 * @author Xavier Bouchenard
	 * @param url	URL of the timetable to get
	 * @throws Exception 	Exception to catch
	 */
	private static void ConfigurationURL(String url) throws Exception {
		
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
		
	}
	
	/**
	 * Enables the loading of a calendar from an URL
	 * @author Xavier Bouchenard
	 * @throws Exception exception to catch
	 */
	private static void loadCalendarFromURL() throws Exception {
		InputStream in = null;

		try {
				in = urlTimeTableDB.openStream();
			} catch (IOException e) {
				throw new IOException("Unable to open a connection stream from the set URL. "
						+ "Maybe the machine is not connected to internet.");
			}
			if (in != null) {
				try {
					ADEcal = Calbuilder.build(in);
				} catch (IOException e) {
					throw new IOException("Unable to build a calendar from the declared connection stream.");
				}
				System.out.println("The loading of the calendar succeed\n");
			}		
	}
	
	/**
	 * Generates the timetable of courses for the department for the current day
	 * @author Xavier Bouchenard
	 * @param url			URL to get the ADE file
	 * @throws Exception 	Exception throwed when an error occurred 
	 * @return	A TreeMap object in which the courses are sorted according to a starting time
	 */
	public static TreeMap<Float, ArrayList<Course>> GenerateTimeTableOfDay(String url) throws Exception {		
		
		ConfigurationURL(url);
		loadCalendarFromURL();
		
		
		String dateTime = getDay();
		mapCourses = BuildTimeTable(dateTime, ADEcal);
		
		return mapCourses;
	}
	
	
	/**
	 * Builds the timetable for the current day 
	 * 		=> Can be used for an update or for the first generation of the timetable of the day
	 * @author Xavier Bouchenard
	 * @param dateTime	Formatted string which contains the info of the day, month, year
	 * @param ADEcal	Formatted calendar to use
	 * @return	A TreeMap object in which the courses are sorted according to a starting time
	 */
	private static TreeMap<Float, ArrayList<Course>> BuildTimeTable(String dateTime, Calendar ADEcal) {
		ArrayList<String> professor;
		ArrayList<String> ClassroomName;
		TreeMap<Float, ArrayList<Course>> map = new TreeMap<Float, ArrayList<Course>>();
		ArrayList<Course> lCourse = null;
		Course course = null;
		String promo = null;
		
		// for all instances of courses in the ADE file, do ...
			for (Object o : ADEcal.getComponents("VEVENT")) {
				Component c = (Component)o;
				// If the current instance of course is a course planned for the day
				if (c.getProperty("DTSTART").getValue().substring(0, dateTime.length()).contains(dateTime)) {
					professor = FindProfessor(c);
					
					//	Position:	- 0 : Starting time of the course
					//				- 1: Ending time of the course
					float TimeArray[] = SetTimes(c);
					ClassroomName = FindClassName(c);
					
					// Gets the promotion as the last string in the array and deletes
					promo = professor.get(professor.size()-1);
					professor.remove(promo);
					
					// Creates for each Course object created, only one classroom is associated
					if (ClassroomName.size() == professor.size()) {
						for (int i = 0; i < ClassroomName.size(); i++) {
							course = new Course(ClassroomName.get(i), professor.get(i), TimeArray[0], TimeArray[1], promo);
							
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
					else {
						// No matter how many string ClassroomName is composed, a course can take place in just one classroom
						//			but it happens that a course have some professors as an exam or a conference,...
						for (String str : ClassroomName) {
							course = new Course(str, professor, TimeArray[0], TimeArray[1], promo);

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
	private static ArrayList<String> FindProfessor(Component C) {
		int i;
		ArrayList<String> professor = new ArrayList<String>();

		//	Parses the info of the line to extract the professor's name
		String[] res = C.getProperty("DESCRIPTION").getValue().split("\n");
		
		//	If the kind of course does not have an assigned professor ...
		if (!C.getProperty("SUMMARY").getValue().equals("P.Col_Réalisation")) {			
			// The string at the 4th position is the group. The other previous positions are empty because of the split done before
			if (res.length > 4) {
				for (i = 3; i < res.length-1; i++)	{
					if ((!(res[i].contains("3"))) && (!(res[i].contains("4"))) && (!(res[i].contains("5")))) {
						professor.add(res[i]);
					}
				}
				// Addition of the group at the last position in the list
				professor.add(res[2]);
			}
			//	If the previous array is just composed of the group and some blanks cases so 
			else {
			professor.add("No professor");
			professor.add(res[2]);
			}
		}
		else {
			professor.add("No professor");
			professor.add(res[2]);
		}
		
		return professor;
	}
	
	/**
	 * Parses the component's specifications to extract the name of the classroom from the line
	 * 		defined by the "LOCATION" tag
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		String which is the name of the classroom of the course
	 */
	private static ArrayList<String> FindClassName(Component C) {
		ArrayList<String> ClassNames = new ArrayList<String>();
		
		//	If the kind of course does not have an assigned classroom
		if (!C.getProperty("SUMMARY").getValue().equals("P.Col_Réalisation")) {
			// Gets a classroom names list:	- 1 classroom for one course
			//								- > 1 if a collective course or an exam
			String[] tab = C.getProperty("LOCATION").getValue().split(",");
			for (String str : tab) {
				ClassNames.add(str);
			}
		}
		else {
			ClassNames.add("No defined room");
		}
		
		return ClassNames;
	}
	
	/**
	 * Sets the starting and the ending times of the instance of course read in the ADE file
	 * @author Xavier Bouchenard
	 * @param C		Component which is an instance of course read in the ADE file
	 * @return		An array of float which corresponds of the starting and the ending time of the course
	 */
	private static float[] SetTimes(Component C) {
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
	private static String getDay() {
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

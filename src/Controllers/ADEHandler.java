package Controllers;

import java.util.TreeMap;

import Model.Course;

public class ADEHandler {
	private String dptName = null;
	
	private String urlTimeTbDB = null;
	
	private TreeMap<Float, Course> mapCourses = null;
	
	public ADEHandler(String dptName, String url) {
		this.dptName = dptName;
		urlTimeTbDB = url;
		
		mapCourses = new TreeMap<Float, Course>();
	}
	
	public void GenerateTimeTbOfDay() {
		
	}
}

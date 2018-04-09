package main;
public class Room extends Vertex {

	public Room(int id, String name, Type type, String number) {
		super(id, name, type);
		this.number = number;
	}

	private String number;

	private Professor professorList;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Professor getProfessorList() {
		return professorList;
	}

	public void setProfessorList(Professor professorList) {
		this.professorList = professorList;
	}

}

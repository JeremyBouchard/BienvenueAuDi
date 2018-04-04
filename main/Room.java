package main;
public class Room extends Vertex {

	public Room(int id, String name, Type type) {
		super(id, name, type);
	}

	private String number;

	private Professor professorList;

}

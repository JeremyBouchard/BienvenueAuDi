package main;
public class ClassRoom extends Room {

	public ClassRoom(int id, String name, Type type) {
		super(id, name, type, name);
	}

	private String studentsPromotion;

}

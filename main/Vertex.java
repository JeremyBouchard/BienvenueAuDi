package main;
public class Vertex {

	private int id;
	private String name;
	private Type type;
	
	
	public Vertex(int id, String name, Type type) {
		this.name=name;
		this.type=type;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}
	

}

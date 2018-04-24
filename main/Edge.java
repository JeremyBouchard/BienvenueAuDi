package Model;

import org.jgraph.graph.DefaultEdge;

/**
 * Class representing our customized Edge
 * @author MariamKonate, JeremyBouchard
 *
 */
public class Edge extends DefaultEdge{

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private int weight;

	private Direction direction;
	
	private int floor;

	/**
	 * Constructor
	 */
	public Edge()
	{
		
	}
	
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

}

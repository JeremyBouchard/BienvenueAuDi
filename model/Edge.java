package model;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Class representing our customized Edge
 * @author MariamKonate, JeremyBouchard
 *
 */
public class Edge extends DefaultWeightedEdge{

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private Direction direction;
	
	//private int floor;

	/**
	 * Constructor
	 */
	public Edge()
	{

	}
	
	public Vertex Source() {
		return (Vertex) getSource();
	}
	
	public Vertex Destination() {
		return (Vertex) getTarget();
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

//	/**
//	 * @return the floor
//	 */
//	public int getFloor() {
//		return floor;
//	}
//
//	/**
//	 * @param floor the floor to set
//	 */
//	public void setFloor(int floor) {
//		this.floor = floor;
//	}

}

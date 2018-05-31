package model;


import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Class representing our customized Edge
 * It extend the default one provided by JGrapht
 * @author MariamKonate, Jérémy Bouchard
 *
 */
public class Edge extends DefaultWeightedEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Direction direction;

	/**
	 * Constructor
	 */
	public Edge()
	{

	}
	
	/**
	 * Needed because Source field is protected in superclass
	 * @return the source Vertex
	 */
	public Vertex Source() {
		return (Vertex) getSource();
	}
	
	/**
	 * Needed because Target field is protected in superclass
	 * @return the target Vertex
	 */
	public Vertex Destination() {
		return (Vertex) getTarget();
	}


	/**
	 * @return the direction associated with the edge
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


}

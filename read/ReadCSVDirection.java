package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import model.Direction;
import model.Edge;
import model.Vertex;

/**The class extends the abstract class 'ReadCSV' for reading the file of saving directions between classrooms.
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public class ReadCSVDirection extends ReadCSV
{
	
	/**
	 * the directions matrix.
	 */
	private List<List<String>> listDirection=new ArrayList<List<String>>();
	/**
	 * the converting directions matrix.
	 */
	private HashMap<Direction,HashMap<Direction,Direction>> matrixDirection=new HashMap<Direction,HashMap<Direction,Direction>>();
	
	/**
	 * The default constructor.
	 */
	public ReadCSVDirection() {
		this.initializeMatrix();
	}

	/**
	 * initialize the converting matrix.
	 */
	public void initializeMatrix()
	{
		

		HashMap<Direction,Direction> north = new HashMap<Direction,Direction>();
		north.put(Direction.North, Direction.South);
		north.put(Direction.South, Direction.North);
		north.put(Direction.East, Direction.West);
		north.put(Direction.West, Direction.East);
		
		HashMap<Direction,Direction> south = new HashMap<Direction,Direction>();
		south.put(Direction.North, Direction.North);
		south.put(Direction.South, Direction.South);
		south.put(Direction.East, Direction.East);
		south.put(Direction.West, Direction.West);
		
		HashMap<Direction,Direction> east = new HashMap<Direction,Direction>();
		east.put(Direction.North, Direction.East);
		east.put(Direction.South, Direction.West);
		east.put(Direction.East, Direction.South);
		east.put(Direction.West, Direction.North);
		
		HashMap<Direction,Direction> west = new HashMap<Direction,Direction>();
		west.put(Direction.North, Direction.West);
		west.put(Direction.South, Direction.East);
		west.put(Direction.East, Direction.North);
		west.put(Direction.West, Direction.South);

		matrixDirection.put(Direction.North,north);
		matrixDirection.put(Direction.South,south);
		matrixDirection.put(Direction.East,east);
		matrixDirection.put(Direction.West,west);
			
		
	}

	/**
	 * @return the directions matrix.
	 */
	public List<List<String>> getListDirection() {
		return listDirection;
	}

	
	/**
	 * @param listDirection the converting directions matrix.
	 */
	public void setListDirection(List<List<String>> listDirection) {
		this.listDirection = listDirection;
	}

	
	/* (non-Javadoc)
	 * @see lire.ReadCSV#convertCSV()
	 */
	@Override
	public void convertCSV()
	{
		listDirection=new ArrayList<List<String>>();
		File file=new File(this.getPath());
	    BufferedReader br=null;
	    	try {
				br = new BufferedReader(new FileReader(file));
				String line = ""; 
		        try {
					while ((line = br.readLine()) != null) 
					{ 
						List<String> ligne=Arrays.asList(line.split("\\,")) ;
						listDirection.add(ligne);
					}
					if(br!=null)
		            {
		            	br.close();
		                br=null;
		            }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	/**
	 * Get the direction between 'begin' and 'arrive'
	 * @param begin : the begin location.
	 * @param arrive : the destination location.
	 * @return the direction between 'begin' and 'arrive'
	 */
	public String getDirection(String begin,String arrive)
	{
		String begin2=begin.toLowerCase();
		String arrive2=arrive.toLowerCase();
		int line=1;
		int column=1;
		String direction="None";
		rechercherLigne: for(line=1;line<listDirection.size();line++)
		{
			if(listDirection.get(line).get(0).toLowerCase().equals(begin2))
			{
				for(column=1;column<listDirection.get(line).size();column++)
				{
					if(listDirection.get(0).get(column).toLowerCase().equals(arrive2))
					{
						if(!listDirection.get(line).get(column).equals(""))
						{
							direction=listDirection.get(line).get(column);
						}					
						break rechercherLigne;
					}
				}
			}
		}
		return direction;
	}
	
	/** Get the converting direction between 'source' and 'destination'.
	 * @param source : the begin location.
	 * @param destination : the destination location.
	 * @return the converting direction between 'source' and 'destination'.
	 */
	public Direction getRealDirection(Vertex sourceV, Vertex actualV, Vertex destV, DefaultDirectedWeightedGraph<Vertex, Edge> g)
	{
		Direction res;
		Direction sourceD;
		Direction destD;
		sourceD= g.getEdge(actualV, sourceV).getDirection();
		destD= g.getEdge(actualV, destV).getDirection();
		
		if(sourceD==Direction.None) {
			res=destD;
		}else {
			if (destD==Direction.None){
			res=Direction.None;
		}
			res=matrixDirection.get(sourceD).get(destD);
		}
		
		
		return res;
	}
}

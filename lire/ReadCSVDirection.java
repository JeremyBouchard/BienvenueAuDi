package lire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	private List<List<String>> matrixDirection=new ArrayList<List<String>>();
	
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
		
		List<String> first = Arrays.asList("","H","B","G","D");
		List<String> second = Arrays.asList("H","None","H","D","G");
		List<String> third = Arrays.asList("B","H","None","G","D");
		List<String> fourth = Arrays.asList("G","G","D","None","H");
		List<String> fifth = Arrays.asList("D","D","G","H","None");
		matrixDirection.add(first);
		matrixDirection.add(second);
		matrixDirection.add(third);
		matrixDirection.add(fourth);
		matrixDirection.add(fifth);
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
	public String getRealDirection(String source,String destination)
	{
		return matrixDirection.get(matrixDirection.indexOf(source)).get(matrixDirection.indexOf(destination));
	}
}

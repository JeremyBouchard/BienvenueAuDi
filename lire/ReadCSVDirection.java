package lire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSVDirection extends ReadCSV
{
//	//le chemin de fichierCSV
//	private String filePath;	
	//la "matrice" de directions
	private List<List<String>> listDirection=new ArrayList<List<String>>();
	private List<List<String>> matrixDirection=new ArrayList<List<String>>();
	
	public ReadCSVDirection() {
		this.initializeMatrix();
	}

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

	public List<List<String>> getListDirection() {
		return listDirection;
	}

	public void setListDirection(List<List<String>> listDirection) {
		this.listDirection = listDirection;
	}

	/**
	 * convert the fiche CSV to "matrice"
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
	 * get the direction between 'begin' and 'arrive'
	 * @param begin  
	 * @param arrive  
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
	
	public String getRealDirection(String source,String destination)
	{
		return matrixDirection.get(matrixDirection.indexOf(source)).get(matrixDirection.indexOf(destination));
	}
}

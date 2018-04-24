package lire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Type;
import model.Vertex;


public class ReadCSVVertex extends ReadCSV
{
	private List<List<String>> listNoeud=new ArrayList<List<String>>();
	private List<Vertex> listVertex =new ArrayList<Vertex>();
	
	
	
	public List<List<String>> getListNoeud() {
		return listNoeud;
	}



	public void setListNoeud(List<List<String>> listNoeud) {
		this.listNoeud = listNoeud;
	}



	public List<Vertex> getListVertex() {
		return listVertex;
	}



	public void setListVertex(List<Vertex> listVertex) {
		this.listVertex = listVertex;
	}



	@Override
	public void convertCSV() {
		// TODO Auto-generated method stub
		listNoeud=new ArrayList<List<String>>();
		File file=new File(this.getPath());
	    BufferedReader br=null;
	    	try {
				br = new BufferedReader(new FileReader(file));
				String line = ""; 
		        try {
		        	line = br.readLine();
					while ((line = br.readLine()) != null) 
					{ 
						List<String> ligne=Arrays.asList(line.split("\\,")) ;
						listNoeud.add(ligne);
						listVertex.add(new Vertex(Integer.parseInt(ligne.get(3)), ligne.get(0),Type.valueOf(ligne.get(1)), Integer.parseInt(ligne.get(2))));
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
}

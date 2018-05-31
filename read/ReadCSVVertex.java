package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ClassRoom;
import model.Room;
import model.Type;
import model.Vertex;


/**The class extends the abstract class 'ReadCSV' for reading the file of saving the information of classrooms.
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public class ReadCSVVertex extends ReadCSV
{
	/**
	 * save all the information of classrooms in a matrix of string. 
	 */
	private List<List<String>> listNoeud=new ArrayList<List<String>>();
	/**
	 * save all the information of classrooms in a list of 'Vertex'.
	 */
	private List<Vertex> listVertex =new ArrayList<Vertex>();

	/**
	 * The default constructor.
	 */
	public ReadCSVVertex() {
	}

	/**
	 * @return the matrix of all the information of classrooms.
	 */
	public List<List<String>> getListNoeud() {
		return listNoeud;
	}

	/**
	 * @param listNoeud : the matrix of all the information of classrooms.
	 */
	public void setListNoeud(List<List<String>> listNoeud) {
		this.listNoeud = listNoeud;
	}

	/**
	 * @return the matrix of all the information of classrooms.
	 */
	public List<Vertex> getListVertex() {
		return listVertex;
	}

	/**
	 * @param listVertex : the list of 'Vertex' of all the information of classrooms.
	 */
	public void setListVertex(List<Vertex> listVertex) {
		this.listVertex = listVertex;
	}

	/* extract data from the csv to populate the listVertex and the lmatrix of vertex
	 */
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
					Type tmpType = Type.valueOf(ligne.get(1));
					
					Vertex tmpVertex = null;

					//Instantiate the right object depending on the type
					switch (tmpType) {
						case Amphis :
						case SallesMachines:
						case SallesTD:
							tmpVertex = new ClassRoom();
							break;
						case Bureaux:
							tmpVertex = new Room();
							break;
						default:
							tmpVertex = new Vertex();

					}

					tmpVertex.setEtage(Integer.parseInt(ligne.get(2)));
					tmpVertex.setId(Integer.parseInt(ligne.get(3)));
					tmpVertex.setName(ligne.get(0));
					tmpVertex.setType(Type.valueOf(ligne.get(1)));
					listVertex.add(tmpVertex);
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

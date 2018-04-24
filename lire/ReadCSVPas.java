package lire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSVPas extends ReadCSV
{
		//la "matrice" de pas
		private List<List<String>> listPas=new ArrayList<List<String>>();

		public List<List<String>> getListPas() {
			return listPas;
		}

		public void setListPas(List<List<String>> listPas) {
			this.listPas = listPas;
		}

		@Override
		public void convertCSV() 
		{
			// TODO Auto-generated method stub
			listPas=new ArrayList<List<String>>();
			File file=new File(this.getPath());
		    BufferedReader br=null;
		    	try {
					br = new BufferedReader(new FileReader(file));
					String line = ""; 
			        try {
						while ((line = br.readLine()) != null) 
						{ 
							List<String> ligne=Arrays.asList(line.split("\\,")) ;
							listPas.add(ligne);
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
		
		public Integer getPas(String begin,String arrive)
		{
			String begin2=begin.toLowerCase();
			String arrive2=arrive.toLowerCase();
			int line=1;
			int column=1;
			int pas=-1;
			rechercherLigne: for(line=1;line<listPas.size();line++)
			{
				if(listPas.get(line).get(0).toLowerCase().equals(begin2))
				{
					for(column=1;column<listPas.get(line).size();column++)
					{
						if(listPas.get(0).get(column).toLowerCase().equals(arrive2))
						{
							if(!listPas.get(line).get(column).equals(""))
							{
								pas=Integer.parseInt(listPas.get(line).get(column));
							}					
							break rechercherLigne;
						}
					}
				}
			}
			return pas;
		}
}

package lire;

public class Main 
{
	public static void main(String[] args)  
	{
	String path="/Users/MariamKonate/Desktop/ProjetS8/readCSVDirection.csv";
		ReadCSVDirection readCSV=new ReadCSVDirection();
		readCSV.setPath(path);
      	readCSV.convertCSV();
		String direction=readCSV.getDirection("Boole", "VON NEUMAN");
     	String direction2=readCSV.getDirection("cafet", "lovelace");
     	String direction3=readCSV.getDirection("Boole", "lovelace");
		System.out.println("direction: "+direction);
		System.out.println("direction2: "+direction2);
		System.out.println("direction3: "+direction3);
//		String path="C:\\Users\\tudou\\Desktop\\pas.csv";
//		ReadCSVPas readCSV=new ReadCSVPas();
//		readCSV.setPath(path);
//		readCSV.convertCSV();
//		Integer direction=readCSV.getPas("Boole", "VON NEUMAN");
//		String direction2=readCSV.getDirection("cafet", "lovelacE");
//		System.out.println("direction: "+direction);
//		System.out.println("direction2: "+direction2);
	}
}

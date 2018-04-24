package test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lire.ReadCSVDirection; 

public class TestDirection {
	private ReadCSVDirection readCSV=new ReadCSVDirection();

	@Test
	public void testCenvert()
	{
		String path="C:\\Users\\tudou\\Desktop\\direction.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();
		assertEquals(87,readCSV.getListDirection().size());
		assertEquals(87, readCSV.getListDirection().get(0).size());
	}
	
	@Test
	public void testGetDirection()
	{
		String path="C:\\Users\\tudou\\Desktop\\direction.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();
		assertEquals(true, readCSV.getDirection("cafet", "lovelacE").equals("D"));
		assertEquals(true, readCSV.getDirection("cafet", "boole").equals("Pas de direction."));
	}
	

}

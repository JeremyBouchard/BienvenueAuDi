package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lire.ReadCSVVertex;

public class TestVertex 
{
	private ReadCSVVertex readCSV=new ReadCSVVertex();

	@Test
	public void testCenvert()
	{
		String path="C:\\Users\\tudou\\Desktop\\noeud.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();	
		assertEquals(86, readCSV.getListNoeud().size());
		assertEquals(86, readCSV.getListVertex().size());
	}
}

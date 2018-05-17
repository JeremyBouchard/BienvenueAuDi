package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lire.ReadCSVVertex;

/** Unit testing for the class 'ReadCSVVertex'.
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public class TestVertex 
{
	private ReadCSVVertex readCSV=new ReadCSVVertex();

	/**
	 * Test the function 'convertCSV'.
	 */
	@Test
	public void testCenvert()
	{
		String path="C:\\Users\\tudou\\Desktop\\readCSVVertex.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();	
		assertEquals(86, readCSV.getListNoeud().size());
		assertEquals(86, readCSV.getListVertex().size());
	}
}

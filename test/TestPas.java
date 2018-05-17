package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import read.ReadCSVPas;

/** Unit testing for the class 'ReadCSVPas'.
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public class TestPas {
	private ReadCSVPas readCSV=new ReadCSVPas();

	/**
	 * Test the function 'convertCSV'.
	 */
	@Test
	public void testCenvert()
	{
		String path="C:\\Users\\tudou\\Desktop\\pas.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();	
		assertEquals(87,readCSV.getListPas().size());
		assertEquals(87, readCSV.getListPas().get(0).size());
	}
	
	/**
	 * Test the function 'getPas'.
	 */
	@Test
	public void testGetPas()
	{
		String path="C:\\Users\\tudou\\Desktop\\pas.csv";
		readCSV.setPath(path);
		readCSV.convertCSV();
		assertEquals(true, readCSV.getPas("cafet", "lovelacE").equals(31));
		assertEquals(true, readCSV.getPas("cafet", "boole").equals(-1));
	}
}

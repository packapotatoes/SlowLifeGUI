import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellToStringTest {

	Cell testCell;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Test behavior when cell is constructed but its alive status is not set
	 */
	@Test
	public void testConstructor1() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testCell = new Cell();
		// "." is the default output of toString() if setAlive() has not been called, and the argumentless constructor was called
		// in other words, the button text has not been set, but toString will return "." anyway
		assertTrue(testCell.toString().equals("."));
	}
	
	/*
	 * Test behavior when cell is constructed using the second constructor, including an alive status
	 * Tests for the cell being initialized to alive, and to dead
	 */
	@Test
	public void testConstructor2(){
		testCell = new Cell(true);
		
		assertTrue(testCell.toString().equals("X"));
		
		testCell = new Cell(false);
		
		assertTrue(testCell.toString().equals("."));
	}
	
	/*
	 * Tests behavior of toString when a cell is set to alive using setAlive()
	 */
	@Test
	public void testSetAlive(){
		testCell = new Cell();
		testCell.setAlive(true);
		assertTrue(testCell.toString().equals("X"));	// cell should show X for alive
		
		testCell.setAlive(false);
		assertTrue(testCell.toString().equals("."));	// cell should show . for dead
		
		testCell = new Cell(false);
		testCell.setAlive(true);
		assertTrue(testCell.toString().equals("X"));	// cell should show X for alive
		testCell.setAlive(false);
		assertTrue(testCell.toString().equals("."));	// cell should show . for dead
		
		testCell = new Cell(true);
		testCell.setAlive(false);
		assertTrue(testCell.toString().equals("."));	// cell should show . for dead
		testCell.setAlive(true);
		assertTrue(testCell.toString().equals("X"));	// cell should show X for alive
	}

}

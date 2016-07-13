import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class convertToIntTests {
	
	int size = 0;
	private String testMatrix1, testMatrix2, testMatrix3;
	private String testMatrix1_run, testMatrix2_run, testMatrix3_run;	// after one iteration, cell status
	
	private MainPanel _mainPanel;
	
	@Before
	public void setUp() throws Exception {

		testMatrix1 = 	"X..............\n";
		testMatrix1 +=	".X.............\n";
		testMatrix1 +=	"..X............\n";
		testMatrix1 +=	"...X...........\n";
		testMatrix1 +=	"....X..........\n";
		testMatrix1 +=	".....X.........\n";
		testMatrix1 +=	"......X........\n";
		testMatrix1 +=	".......X.......\n";
		testMatrix1 +=	"........X......\n";
		testMatrix1 +=	".........X.....\n";
		testMatrix1 +=	"..........X....\n";
		testMatrix1 +=	"...........X...\n";
		testMatrix1 +=	"............X..\n";
		testMatrix1 +=	".............X.\n";
		testMatrix1 +=	"..............X\n";
		testMatrix1_run = testMatrix1;	// the next iteration of this patter is identical
		
		testMatrix2 = 	"X..............\n";
		testMatrix2 +=	".X.............\n";
		testMatrix2 +=	"..XX...........\n";
		testMatrix2 +=	"...X...........\n";
		testMatrix2 +=	"....X..........\n";
		testMatrix2 +=	".....XXX.......\n";
		testMatrix2 +=	"......XX.......\n";
		testMatrix2 +=	".......X.......\n";
		testMatrix2 +=	"........X......\n";
		testMatrix2 +=	".........X.....\n";
		testMatrix2 +=	"..........X....\n";
		testMatrix2 +=	"...........X...\n";
		testMatrix2 +=	"............X..\n";
		testMatrix2 +=	".............X.\n";
		testMatrix2 +=	"..............X\n";
		
		testMatrix2_run = 	"X..............\n";
		testMatrix2_run +=	".XX............\n";
		testMatrix2_run +=	"..XX...........\n";
		testMatrix2_run +=	"..XXX..........\n";
		testMatrix2_run +=	"....XXX........\n";
		testMatrix2_run +=	".....X.X.......\n";
		testMatrix2_run +=	".....X..X......\n";
		testMatrix2_run +=	"......XXX......\n";
		testMatrix2_run +=	"........X......\n";
		testMatrix2_run +=	".........X.....\n";
		testMatrix2_run +=	"..........X....\n";
		testMatrix2_run +=	"...........X...\n";
		testMatrix2_run +=	"............X..\n";
		testMatrix2_run +=	".............X.\n";
		testMatrix2_run +=	"..............X\n";
		
		testMatrix3 = 	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	".......XX......\n";
		testMatrix3 +=	"......XX.X.....\n";
		testMatrix3 +=	".......XX......\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		testMatrix3 +=	"...............\n";
		
		testMatrix3_run = 	"......X.X......\n";
		testMatrix3_run +=	".......X.......\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"X.............X\n";
		testMatrix3_run +=	".X...........X.\n";
		testMatrix3_run +=	"X.............X\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	"...............\n";
		testMatrix3_run +=	".......X.......\n";
		testMatrix3_run +=	"......X.X......\n";
	
		Thread.sleep(10000);
		
	}

	@After
	public void tearDown() throws Exception {

	}
	
	/*
	 * Test a simple diagonal line - should result in the same pattern after running
	 */
	@Test
	public void test1() {
		
		size = 15;
		_mainPanel = new MainPanel(size);
		_mainPanel.setCells(createTestMatrix1());

		String current = _mainPanel.toString();
		assertTrue( current.equals(testMatrix1) );	// ensure that matrix was properly input
		
		_mainPanel.run();
		
		String afterRun = _mainPanel.toString();
		assertTrue( afterRun.equals(testMatrix1_run));
		
	
	}
	
	/*
	 * Test a slightly more complicated pattern
	 */
	@Test
	public void test2() {
		
		size = 15;
		_mainPanel = new MainPanel(size);
		_mainPanel.setCells(createTestMatrix2());

		String current = _mainPanel.toString();
		System.out.println(current);
		assertTrue( current.equals(testMatrix2) );	// ensure that matrix was properly input
		
		_mainPanel.run();
		String afterRun = _mainPanel.toString();
		System.out.println(afterRun);
		assertTrue( afterRun.equals(testMatrix2_run));	// ensure proper board after one run
	
	}
	
	/*
	 * test an exploder pattern
	 * After running continuously for a while,
	 * this pattern should result in a stable pattern
	 * Also ensures that cells on edges are properly computed
	 */
	@Test
	public void test3() {
		
		size = 15;
		_mainPanel = new MainPanel(size);
		_mainPanel.setCells(createTestMatrix3());

		String current = _mainPanel.toString();
		System.out.println(current);
		assertTrue( current.equals(testMatrix3) );	// ensure that matrix was properly input
		
		// run a bunch of times until stable
		for(int i = 0; i < 50; i++)
			_mainPanel.run();
		String afterRun = _mainPanel.toString();
		System.out.println(afterRun);
		assertTrue( afterRun.equals(testMatrix3_run));	// ensure proper board after one run
	
	}
	
	
	
	private Cell[][] createTestMatrix1(){
		Cell cells[][] = new Cell[size][size];
		
		for (int j = 0; j < size; j++) {
		    for (int k = 0; k < size; k++) {
				cells[j][k] = new Cell();
				
				cells[j][k].setAlive(false);
				if(j == k){
					cells[j][k].setAlive(true);
				}
		    }
		}
		return cells;
	}
	
	private Cell[][] createTestMatrix2(){
		Cell cells[][] = new Cell[size][size];
		
		for (int j = 0; j < size; j++) {
		    for (int k = 0; k < size; k++) {
				cells[j][k] = new Cell();
				
				cells[j][k].setAlive(false);
				if(j == k){
					cells[j][k].setAlive(true);
				}
		    }
		}
		
		cells[5][6].setAlive(true);
		cells[5][7].setAlive(true);
		cells[6][7].setAlive(true);
		cells[2][3].setAlive(true);
		return cells;
	}
	
	private Cell[][] createTestMatrix3(){
		Cell cells[][] = new Cell[size][size];
		
		for (int j = 0; j < size; j++) {
		    for (int k = 0; k < size; k++) {
				cells[j][k] = new Cell();
				
				cells[j][k].setAlive(false);
		    }
		}
		
		cells[7][7].setAlive(true);
		cells[6][7].setAlive(true);
		cells[6][8].setAlive(true);
		cells[7][6].setAlive(true);
		cells[8][7].setAlive(true);
		cells[8][8].setAlive(true);
		cells[7][9].setAlive(true);
		return cells;
	}

}

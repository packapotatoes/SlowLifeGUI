import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class convertToIntTests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	
	int size = 0;
	
	private MainPanel _mainPanel;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(standardOut);
	}

	@Test
	public void test() {
		
		size = 15;
		_mainPanel = new MainPanel(size);
		_mainPanel.setCells( { });
	}
	
	private Cell[][] createTestMatrix(){
		Cell cells[][] = new Cell[size][size];
		
		for (int j = 0; j < size; j++) {
		    for (int k = 0; k < size; k++) {
				cells[j][k] = new Cell();
				
				cells[j][k].setAlive(false);
		    }
		}
		return cells;
	}

}

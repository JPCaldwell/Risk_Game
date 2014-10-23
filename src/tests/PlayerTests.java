package tests;


import static org.junit.Assert.*;
import code.*;
import org.junit.Before;
import org.junit.Test;


public class PlayerTests {

	public Player p;
	
	@Before
	public void setup() {
		p = new Player();
	}
	
	@Test
	public void calculateReinforceTest1() {
		p.addChair(new Department("Biology"));
		p.addChair(new Department("Mathematics"));
		p.addChair(new Department("Chemistry"));
		p.addChair(new Department("Physics"));
		p.addDean(new School("Sciences"));
		int test = p.calculateReinforce();
		assertTrue(test == 30);
	}
	
	@Test
	public void calculateReinforceTest2() {
		p.addChair(new Department("Biology"));
		assertTrue(p.calculateReinforce() == 3);
	}
	
	@Test
	public void calculateReinforce3() {
		assertTrue(p.calculateReinforce() == 0);
	}
	
}

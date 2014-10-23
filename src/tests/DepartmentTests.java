package tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import code.*;


public class DepartmentTests {
	private Department d;
	private Player p;
	
	@Before
	public void setup() {
		p = new Player(1);
		d = new Department("Biology");
		d.setChair(p);
	}
	
	@Test
	public void addStudentsTest1() {
		int expected = 5;
		for(int i = 0; i < 5; i++){
			d.addStudents(new Student(p));
		}
		int actual = d.getNumStudents(p);
		assertTrue(expected == actual);
	}
	
	@Test
	public void addStudentsTest2() {
		assertTrue(d.getNumStudents(p) == 0);
	}
	
	@Test
	public void checkAdjacentTest1() {
		assertTrue(d.checkAdjacent("Mathematics"));
	}
	@Test
	public void checkAdjacentTest2() {
		assertFalse(d.checkAdjacent("Computer Engineering"));
	}
	
	@Test
	public void setChairTest1() {
		Department e = new Department("Mathematics");
		e.setChair(p);
		assertTrue(d.getChair().equals(e.getChair()));
	}
	
	@Test
	public void setChairTest2() {
		Department e = new Department("Mathematics");
		Department f = new Department("Physics");
		e.setChair(p);
		f.setChair(p);
		boolean test1 = e.getChair().equals(d.getChair());
		boolean test2 = f.getChair().equals(d.getChair());
		assertTrue(test1 == test2);
	}
	
	@Test
	public void setChairTest3() {
		Department e = new Department("Mathematics");
		e.setChair(new Player(2));
		assertFalse(e.getChair().equals(d.getChair()));
	}
	
	@Test
	public void removeStudentTest() {
		d.addStudents(new Student(p));
		d.addStudents(new Student(new Player()));
		d.removeStudent(p);
		assertTrue(0 == d.getNumStudents(p) && d.getStudents().size() == 1);
	}
	
	@Test
	public void addPlayerTest1() {
		for(int i = 0; i < 10; i++) {
			d.addPlayer(new Player());
		}
		assertTrue(d.getPlayers().size() == 10);
	}
	
	@Test
	public void addPlayerTest2() {
		assertTrue(d.getPlayers().size() == 0);
	}
	
	@Test
	public void removePlayerTest1() {
		d.addPlayer(p);
		for(int i = 0; i < 3; i++) {
			d.addPlayer(new Player());
		}
		d.removePlayer(p);
		assertTrue(d.getPlayers().size() == 3);
	}
	
	@Test
	public void removePlayerTest2() {
		Player q = new Player(2);
		Player r = new Player(3);
		Player s = new Player(4);
		Player t = new Player(5);
		d.addPlayer(p);
		d.addPlayer(q);
		d.addPlayer(r);
		d.addPlayer(s);
		d.addPlayer(t);
		d.removePlayer(p);
		d.removePlayer(r);
		assertTrue(d.getPlayers().size() == 3);
	}
	
}

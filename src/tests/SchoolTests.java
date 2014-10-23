package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import code.*;
import org.junit.Before;
import org.junit.Test;

public class SchoolTests {
	private School s;
	private Player p;
	private Department e;
	private ArrayList<Department> depList;
	
	@Before
	public void init() {
		s = new School("Sciences");
		p = new Player();
		e = new Department("Biology");
		depList = new ArrayList<Department>();
		depList.add(e);
		depList.add(new Department("Mathematics"));
		depList.add(new Department("Chemistry"));
		depList.add(new Department("Physics"));
		for(Department d: depList) {
			d.setChair(p);
			s.addDepartment(d);
		}
	}
	
	@Test
	public void calculateDeanTest1() {
		s.calculateDean(p);
		ArrayList<School> list = p.getDean();
		Player p2 = new Player();
		p2.addDean(s);
		ArrayList<School> list2 = p2.getDean();
		assertTrue(list.equals(list2));
	}
	
	@Test
	public void calculateDeanTest2() {
		e.setChair(new Player());
		s.calculateDean(p);
		assertFalse(s.getDean().equals(p));
		
	}
}

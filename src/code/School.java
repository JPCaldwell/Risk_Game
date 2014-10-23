package code;

import java.util.ArrayList;

public class School {
	private String name;
	private Player dean;
	private ArrayList<Department> departments;
	
	public School(String s) {
		name = s;
		dean = new Player();
		departments = new ArrayList<Department>();
	}
	// checks to see whether the Player specified by @param p has become Dean of the School
	public void calculateDean(Player p) {
		boolean newPlayerIsDean = true;
		boolean prevDeanStays = true;
		for (Department d: departments) {
			if (!d.getChair().equals(p)) {
				newPlayerIsDean = false;
			}
			if (!d.getChair().equals(dean)) {
				prevDeanStays = false;
			}
		}
		if (!prevDeanStays) {
			dean.removeDean(this);
			dean = new Player();
		}
		if (newPlayerIsDean) {
			dean = p;
			p.addDean(this);
		}
		// debugging
		System.out.println("current dean of " + getName() + " = " + dean.getName());
		System.out.println(getName() + "'s departments:");
		for (Department d: getDepartments()) {
			System.out.println(d.getName());
		}
	}
	
	// accessor and mutator methods
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public Player getDean() {
		return dean;
	}
	
	public void setDean(Player p) {
		dean = p;
	}
	
	public ArrayList<Department> getDepartments() {
		return departments;
	}
	
	public void addDepartment(Department d) {
		departments.add(d);
	}
}

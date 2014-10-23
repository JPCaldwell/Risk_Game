package code;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Department> chairList;
	private ArrayList<School> deanList;
	/**
	 *  constructor used to generate placeholder Players 
	 */
	public Player() {
		name = "";
		chairList = new ArrayList<Department>();
		deanList = new ArrayList<School>();
	}
	/**
	 *  constructor for default use
	 */
	public Player(int i) {
		name = "Player " + i;
		chairList = new ArrayList<Department>();
		deanList = new ArrayList<School>();
	}
	/**
	 * constructor for use with names from command line
	 */
	public Player(String s) {
		name = s;
		chairList = new ArrayList<Department>();
		deanList = new ArrayList<School>();
	}
	/**
	 *  calculates the number of students generated in the reinforce phase
	 */
	public int calculateReinforce() {
		return (chairList.size() * 3) + (deanList.size() * 18);
	}
	
	/*
	 *  accessor and mutator methods
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public ArrayList<Department> getChairList() {
		return chairList;
	}
	
	public void addChair(Department d) {
		chairList.add(d);
	}
	
	public void removeChair(Department d) {
		chairList.remove(d);
	}
	
	public ArrayList<School> getDean() {
		return deanList;
	}
	
	public void addDean(School s) {
		deanList.add(s);
	}
	
	public void clearDean() {
		deanList.clear();
	}
	
	public void removeDean(School s) {
		deanList.remove(s);
	}
}

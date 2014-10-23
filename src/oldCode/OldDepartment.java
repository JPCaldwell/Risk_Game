package oldCode;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class OldDepartment implements Serializable {
	private String _name;
	private String _school;
	private OldPlayer _chair;
	private ArrayList<OldStudent> _studentList;
	private ArrayList<String> _adjacentList;
	private boolean _electionPerformed;

	public OldDepartment(String s){
		_name = s;
		_chair = null;
		_studentList = new ArrayList<OldStudent>();
		_adjacentList = new ArrayList<String>();
		fillSchoolAndAdjacentList(s);
		_electionPerformed = false;
	}
	
	public OldDepartment(String s,OldPlayer p){
		_name = s;
		_chair = p;
		_studentList = new ArrayList<OldStudent>();
		_adjacentList = new ArrayList<String>();
		fillSchoolAndAdjacentList(s);
		_electionPerformed = false;
	}
	
	public void showInfo(ArrayList<OldPlayer> p){
		System.out.println(_name);
		System.out.println("School:"+_school);
		try{
			System.out.println("Chair: "+_chair.getName());
		}
		catch(NullPointerException e){
			System.out.println("Chair:Nobody");
		}
		System.out.print("Adjacent to:");
		for(String s:_adjacentList){
			System.out.print(s+" ");
		}
		System.out.println();
		for(OldPlayer player: p){
			System.out.print(player.getName() + ": " + getStudents(player));
			System.out.println();
		}
		System.out.println();
	}
	
	public String getName(){
		return _name;

	}
	
	public ArrayList<OldStudent> getStudentList(){
		return _studentList;
	}
	
	public void addStudentReinforce(int i, OldPlayer p){
		for(int j = 0; j < i; j++) {
			_studentList.add(new OldStudent(p));
		}
		System.out.println(p.getName() + " now has "+ getStudents(p)+" students in "+_name+".");
	}
	
	public void addStudentMove(int i, OldPlayer p){
		for(int j = 0; j < i; j++) {
			OldStudent s = new OldStudent(p);
			s.setMoved();
			_studentList.add(s);
		}
		System.out.println(p.getName() + " now has "+ getStudents(p)+" students in "+_name+".");
	}
	
	public void removeStudent(int i,OldPlayer p){
		for(int k = 0;k<i;k++){
			for(OldStudent s: _studentList){
				if(s.getOwner().equals(p)){
					_studentList.remove(s);
					//System.out.println(p.getName() + " now has "+ getStudents(p)+" students in "+_name+".");
					break;
				}
			}
		}
	}

	public int getStudents(OldPlayer p) {
		int count = 0;
		for (OldStudent student: _studentList) {
			if (student.getOwner().equals(p)) {
				count++;
			}
		}
		return count;
	}
	
	public void setChair(OldPlayer p){
		_chair = p;
	}
	
	public OldPlayer getChair(){
		return _chair;
	}
	
	public String getSchool() {
		return _school;
	}
	
	public void resetElections(){
		_electionPerformed = false;
	}
	
	public void electionPerformed(){
		_electionPerformed = true;
	}
	
	public boolean getElectionStatus(){
		return _electionPerformed;
	}
	
	public int getNonMoved(OldPlayer p) {
		int count = 0;
		for (OldStudent student: _studentList) {
			if (student.getOwner().equals(p) && !student.getMoved()) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<String> getAdjacentList() {
		return _adjacentList;
	}
	
	public boolean isAdjacent(OldDepartment d) {
		return _adjacentList.contains(d.getName());
	}
	
	public void resetStudents() {
		for(OldStudent s: _studentList) {
			s.resetMoved();
		}
	}
	
	private void fillSchoolAndAdjacentList(String s) {
		if (s == "Music" || s == "Theatre" || s == "Dance" || s == "Visual Arts") {
			_school = "Arts";
			if (s == "Music") {
				_adjacentList.add("Theatre");
				_adjacentList.add("Dance");
				_adjacentList.add("Visual Arts");
				_adjacentList.add("Physics");
			}
			else if (s == "Theatre") {
				_adjacentList.add("Music");
				_adjacentList.add("Dance");
				_adjacentList.add("Visual Arts");
			}
			else if (s == "Dance") {
				_adjacentList.add("Music");
				_adjacentList.add("Theatre");
				_adjacentList.add("Visual Arts");
			}
			else {
				_adjacentList.add("Music");
				_adjacentList.add("Theatre");
				_adjacentList.add("Dance");
				_adjacentList.add("Mechanical Engineering");
			}
			
		}
		else if (s == "Biology" || s == "Chemistry" || s == "Physics" || s == "Mathematics") {
			_school = "Sciences";
			if (s == "Biology") {
				_adjacentList.add("Chemistry");
				_adjacentList.add("Physics");
				_adjacentList.add("Mathematics");
			}
			else if (s == "Chemistry") {
				_adjacentList.add("Biology");
				_adjacentList.add("Physics");
				_adjacentList.add("Mathematics");
				_adjacentList.add("Anesthesia");
			}
			else if (s == "Physics") {
				_adjacentList.add("Biology");
				_adjacentList.add("Chemistry");
				_adjacentList.add("Mathematics");
				_adjacentList.add("Music");
			}
			else {
				_adjacentList.add("Biology");
				_adjacentList.add("Chemistry");
				_adjacentList.add("Physics");
				_adjacentList.add("Religion");
			}
		}
		else if (s == "History" || s == "Linguistics" || s == "Literature" || s == "Religion") {
			_school = "Humanities";
			if (s == "History") {
				_adjacentList.add("Linguistics");
				_adjacentList.add("Literature");
				_adjacentList.add("Religion");
			}
			else if (s == "Linguistics") {
				_adjacentList.add("History");
				_adjacentList.add("Literature");
				_adjacentList.add("Religion");
				_adjacentList.add("Computer Engineering");
			}
			else if (s == "Literature") {
				_adjacentList.add("History");
				_adjacentList.add("Linguistics");
				_adjacentList.add("Religion");
			}
			else {
				_adjacentList.add("History");
				_adjacentList.add("Linguistics");
				_adjacentList.add("Literature");
				_adjacentList.add("Mathematics");
			}
		}
		else if (s == "Computer Engineering" || s == "Electrical Engineering" 
					|| s == "Mechanical Engineering" || s == "Civil Engineering") {
			_school = "Engineering";
			if (s == "Computer Engineering") {
				_adjacentList.add("Electrical Engineering");
				_adjacentList.add("Mechanical Engineering");
				_adjacentList.add("Civil Engineering");
				_adjacentList.add("Linguistics");
			}
			else if (s == "Electrical Engineering") {
				_adjacentList.add("Computer Engineering");
				_adjacentList.add("Mechanical Engineering");
				_adjacentList.add("Civil Engineering");
			}
			else if (s == "Mechanical Engineering") {
				_adjacentList.add("Computer Engineering");
				_adjacentList.add("Electrical Engineering");
				_adjacentList.add("Civil Engineering");
				_adjacentList.add("Visual Arts");
			}
			else {
				_adjacentList.add("Computer Engineering");
				_adjacentList.add("Electrical Engineering");
				_adjacentList.add("Mechanical Engineering");
				_adjacentList.add("Business Law");
			}
		}
		else if (s == "Business Law" || s == "Family Law" || s == "Criminal Law" || s == "Immigration Law") {
			_school = "Law";
			if (s == "Business Law") {
				_adjacentList.add("Family Law");
				_adjacentList.add("Criminal Law");
				_adjacentList.add("Immigration Law");
				_adjacentList.add("Civil Engineering");
			}
			else if (s == "Family Law") {
				_adjacentList.add("Business Law");
				_adjacentList.add("Criminal Law");
				_adjacentList.add("Immigration Law");
			}
			else if (s == "Criminal Law") {
				_adjacentList.add("Business Law");
				_adjacentList.add("Family Law");
				_adjacentList.add("Immigration Law");
				_adjacentList.add("Pathology");
			}
			else {
				_adjacentList.add("Business Law");
				_adjacentList.add("Family Law");
				_adjacentList.add("Criminal Law");
			}
		}
		else {
			_school = "Medicine";
			if (s == "Anesthesia") {
				_adjacentList.add("Otolaryngology");
				_adjacentList.add("Pathology");
				_adjacentList.add("Pediatrics");
				_adjacentList.add("Chemistry");
			}
			else if (s == "Otolaryngology") {
				_adjacentList.add("Anesthesia");
				_adjacentList.add("Pathology");
				_adjacentList.add("Pediatrics");
			}
			else if (s == "Pathology") {
				_adjacentList.add("Anesthesia");
				_adjacentList.add("Otolaryngology");
				_adjacentList.add("Pediatrics");
				_adjacentList.add("Criminal Law");
			}
			else {
				_adjacentList.add("Anesthesia");
				_adjacentList.add("Otolaryngology");
				_adjacentList.add("Pathology");
			}
		}
	}
}
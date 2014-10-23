package code;

import java.util.ArrayList;

public class Department {
	private String name;
	private Player chair;
	private ArrayList<Player> players;
	private ArrayList<Student> students;
	private boolean electionPerformed;
	
	public Department(String s) {
		name = s;
		chair = new Player();
		players = new ArrayList<Player>();
		students = new ArrayList<Student>();
		electionPerformed = false;
	}
	/**
	 *  method used to provide the info for the windows displaying Department information
	 *  
	 *  @return the String used to generate the Department pop-up windows
	 */
	public String printInfo() {
		int[] count = new int[20];
		
		String info = "<html><table border=\"" + 0 + "\" width=\"" + 240 + "\">";
		info = info + "<tr><td><b><u>Department:</u></b></td></tr>";
		info = info + "<tr><td>" + getName() + "</td></tr>";
		info = info + "<tr><td><b><u>Chair:</u></b></td></tr>";
		if (chair.getName().equals("")) {
			info = info + "<tr><td>(none)</td></tr>";
		}
		else {
			info = info + "<tr><td>" + chair.getName() + "</td></tr>";
		}
		info = info + "<tr><td><b><u>Player</u></b></td><td><b><u># Students</u></b></td></tr>";
		for (int i = 0; i < players.size(); i++) {
			for (Student s: students) {
				if (s.getOwner().getName().equals(players.get(i).getName())) {
					count[i]++;
				}
			}
		}
		if (players.size() == 0) {
			info = info + "<tr><td>(none)</td></tr>";
		}
		else {
			for (int i = 0; i < players.size(); i++) {
				info = info + "<tr><td>" + players.get(i).getName() + "</td><td>" + count[i] + "</td></tr>";
			}
		}
		info = info + "</table></html>";
		return info;
	}
	
    /**
     *  checks to see if an apartment is adjacent
     *  
     *  @param dest the String corresponding to the name of the Department being checked for adjacency
     *  @return true if the Department is adjacent, false otherwise  
     */
	public boolean checkAdjacent(String dest) {
		return returnAdjacentList(dest).contains(this.getName());	
	}
	/**
	 *  resets the move status of all students in the Department to indicate that they have not been moved yet
	 */
	public void resetStudents() {
		for (Student s: students) {
			s.resetMoved();
		}
	}
	/**
	 *  checks to see if an election has been called
	 *  
	 *  @return returns true if an election has already been called in this Department,false otherwise
	 */
	public boolean getElection() {
		return electionPerformed;
	}
	/**
	 *  indicates that an election has been called in this Department
	 */
	public void setElection() {
		electionPerformed = true;
	}
	/**
	 *  resets the election status in this Department to indicate an election has not yet been called
	 */
	public void resetElections() {
		electionPerformed = false;
	}
	
	/**
	 * gets the name of the Department
	 * 
	 * @return the String indicating the name of the Department
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of the Department
	 * 
	 * @param s the String indicating the name that should be set
	 */
	public void setName(String s) {
		name = s;
	}
	/**
	 * gets the Player that chairs the Department
	 * 
	 * @return the Player that is chair
	 */
	public Player getChair() {
		return chair;
	}
	/**
	 * sets the chair of the Department
	 * 
	 * @param p the Player that is set as chair
	 */
	public void setChair(Player p) {
		chair = p;
	}
	/**
	 *  returns a list of Players that own students in the Department
	 *  
	 *  @return ArrayList of Players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	/**
	 *  indicates that a Player now owns students in the Department
	 *  
	 *  @param p the Player that has students in the Department
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}
	/**
	 *  indicates that a Player no longer owns students in the Department
	 *  
	 *  @param p the Player that no longer has students in the Department
	 */
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	/**
	 * gets the number of Students in the Department owned by a Player
	 * 
	 * @param p the Player which the Students are being checked for
	 * @return  the number of students the Player owns in the Department
	 */
	public int getNumStudents(Player p) {
		int count = 0;
		for (Student s: students) {
			if (s.getOwner().equals(p)) {
				count++;
			}
		}
		return count;
	}
	/**
	 *  adds a Student to the Department
	 *  
	 * @param s the Student that is added to the Department
	 */
	public void addStudents(Student s) {
		students.add(s);
	}
	/**
	 *  removes a Student from the Department
	 *  
	 * @param p the Player whose Student is being removed
	 */
	public void removeStudent(Player p) {
		boolean removed = false;
		Student temp = null;
		do {
			for (Student s: students) {
				if (s.getOwner().equals(p)) {
					temp = s;
					removed = true;
				}
			}
		} while(!removed);
		students.remove(temp);
	}
	/**
	 *  gets the names of adjacent Departments
	 *  
	 * @param s the name of the Department for which adjacent Departments are being checked
	 * 
	 * @return an ArrayList<String> of names of adjacent Departments
	 */
	private ArrayList<String> returnAdjacentList(String s) {
		ArrayList<String> adjacentList = new ArrayList<String>();

		if (s == "Music" || s == "Theatre" || s == "Dance" || s == "Visual Arts") {
			adjacentList.add("Music");
			adjacentList.add("Theatre");
			adjacentList.add("Dance");
			adjacentList.add("Visual Arts");
			if (s == "Music") {
				adjacentList.add("Physics");
			}
			else if (s == "Visual Arts") {
				adjacentList.add("Mechanical Engineering");
			}

		}
		else if (s == "Biology" || s == "Chemistry" || s == "Physics" || s == "Mathematics") {
			adjacentList.add("Biology");
			adjacentList.add("Chemistry");
			adjacentList.add("Physics");
			adjacentList.add("Mathematics");
			if (s == "Chemistry") {
				adjacentList.add("Anesthesia");
			}
			else if (s == "Physics") {
				adjacentList.add("Music");
			}
			else if (s == "Mathematics"){
				adjacentList.add("Religion");
			}
		}
		else if (s == "History" || s == "Linguistics" || s == "Literature" || s == "Religion") {
			adjacentList.add("History");
			adjacentList.add("Linguistics");
			adjacentList.add("Literature");
			adjacentList.add("Religion");
			if (s == "Linguistics") {
				adjacentList.add("Computer Engineering");
			}
			else if (s == "Religion"){
				adjacentList.add("Mathematics");
			}
		}
		else if (s == "Computer Engineering" || s == "Electrical Engineering" 
				|| s == "Mechanical Engineering" || s == "Civil Engineering") {
			adjacentList.add("Computer Engineering");
			adjacentList.add("Electrical Engineering");
			adjacentList.add("Mechanical Engineering");
			adjacentList.add("Civil Engineering");
			if (s == "Computer Engineering") {
				adjacentList.add("Linguistics");
			}
			else if (s == "Mechanical Engineering") {
				adjacentList.add("Visual Arts");
			}
			else if (s == "Civil Engineering"){
				adjacentList.add("Business Law");
			}
		}
		else if (s == "Business Law" || s == "Family Law" || s == "Criminal Law" || s == "Immigration Law") {
			adjacentList.add("Business Law");
			adjacentList.add("Family Law");
			adjacentList.add("Criminal Law");
			adjacentList.add("Immigration Law");
			if (s == "Business Law") {
				adjacentList.add("Civil Engineering");
			}
			else if (s == "Criminal Law") {
				adjacentList.add("Pathology");
			}
		}
		else {
			adjacentList.add("Anesthesia");
			adjacentList.add("Otolaryngology");
			adjacentList.add("Pathology");
			adjacentList.add("Pediatrics");
			if (s == "Anesthesia") {
				adjacentList.add("Chemistry");
			}
			else if (s == "Pathology") {
				adjacentList.add("Criminal Law");
			}
		}
		return adjacentList;
	}
}

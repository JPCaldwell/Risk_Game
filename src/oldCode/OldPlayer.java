package oldCode;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class OldPlayer implements Serializable {

	private ArrayList<OldDepartment> _chairList;
	private String _name;
	private transient Scanner sc = new Scanner(System.in);

	public OldPlayer(int i){
		_name = "Player"+i;
		_chairList = new ArrayList<OldDepartment>();	
	}

	public void setChair(OldDepartment d){
		_chairList.add(d);
	}

	public void removeChair(OldDepartment d){
		_chairList.remove(d);
	}

	public ArrayList<OldDepartment> getChairList(){
		return _chairList;
	}

	public String getName(){
		return _name;
	}

	public int calculate(){
		System.out.println("You gained "+ ((_chairList.size()*3) + (this.numDean() * 18)) +" students.");
		return _chairList.size() * 3 + this.numDean() * 18;
	}
	
	public int numDean() {
		String[] schoolList = {"Arts", "Sciences", "Humanities", "Engineering", "Law", "Medicine"};
		int deanCount = 0;
		int count;
		for (String s: schoolList) {
			count = 0;
			for (OldDepartment d: _chairList) {
				if (d.getSchool().equals(s)) {
					count++;
				}
				if (count == 4) {
					deanCount++;
				}
			}
		}
		return deanCount;
	}
	
	public int addVotes(OldDepartment d){
		Random randInt = new Random();
		int count = 0;
		for(OldStudent s: d.getStudentList()){
			if(s.getOwner().equals(this)){
				if((randInt.nextInt(100)+1)<67){
					count++;
				}
			}
		}
		return count;
	}

	public void reinforce(int s, ArrayList<OldDepartment> DepList) {
		Scanner inp = new Scanner(System.in);
		for(int i = 1; i <= s; i++) {
			boolean studentPlaced = false;
			OldDepartment depChoice = null;
			while (!studentPlaced) {
				while (depChoice == null) {
					System.out.println("Where do you want to put student number "+i+"?");
					String location = inp.nextLine();
					for (OldDepartment dep: DepList) {
						if (dep.getName().equals(location)) {
							depChoice = dep;
						}
					}
				}
				if (depChoice.getStudents(this) != 0) {
					depChoice.addStudentReinforce(1, this);
					studentPlaced = true;
				}
				else {
					System.out.println("You can't place a student in " + depChoice.getName() + 
							" because you don't have any students there.");
					depChoice = null;
				}
			}
		}
	}

	public void move(ArrayList<OldDepartment> DepList){
		Scanner inp = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			OldDepartment startChoice = null;
			OldDepartment endChoice = null;
			int numToMove;

			System.out.println("What department do you want to move from?");
			String depStart = inp.nextLine();
			for (OldDepartment dep: DepList) {
				if (dep.getName().equals(depStart)) {
					startChoice = dep;
					
				}				
			}
			if(startChoice == null) {
				System.out.println("That is not a valid department.");
				continue;
			}

			System.out.println("What department do you want to move to?");
			String depEnd = inp.nextLine();
			for (OldDepartment dep: DepList) {
				if (dep.getName().equals(depEnd)) {
					endChoice = dep;
				}				
			}
			if(endChoice == null) {
				System.out.println("That is not a valid department.");
				continue;
			}
			if (!startChoice.isAdjacent(endChoice)) {
				System.out.println("These departments are not adjacent");
				continue;
			}

			System.out.println("How many students do you want to move?");
			numToMove = inp.nextInt();
			if(startChoice.getNonMoved(this) < numToMove) {
				System.out.println("There are not enough students in " + startChoice.getName());
			}
			else if(_chairList.contains(startChoice)) {
				if(startChoice.getNonMoved(this) - 1 < numToMove) {
					System.out.println("You must leave at least one student in a department that you chair");
				}
				else {
					startChoice.removeStudent(numToMove, this);
					System.out.println(_name + " now has " + startChoice.getStudents(this) + " students in " + startChoice.getName());
					endChoice.addStudentMove(numToMove,this);
				}
			}
			else {
				startChoice.removeStudent(numToMove, this);
				System.out.println(_name + " now has " + startChoice.getStudents(this) + " students in " + startChoice.getName());
				endChoice.addStudentMove(numToMove,this);
			}

			System.out.println("Are you done moving? y/n");
			String choice = inp.next();
			inp.nextLine();
			if(choice.equals("y")) {
				done = true;
			}	
		}
	}

	public void callElection(ArrayList<OldDepartment> DepList,ArrayList<OldPlayer>PlayerList) {
		Scanner inp = new Scanner(System.in);
		boolean done = false;
		while(!done){
			OldDepartment depElect = null;
			boolean proceed = true;
			System.out.println("In what department do you want to call an election?");
			String depStringInput = inp.nextLine();
			for(OldDepartment d:_chairList){
				if(d.getName().equals(depStringInput)){
					System.out.println("You can't call an election in a department that you chair.");
					proceed = false;
				}
			}
			for(OldDepartment d: DepList){
				if(d.getName().equals(depStringInput)){
					if(d.getStudents(this)==0){
						System.out.println("You don't have any students in that department.");
						proceed = false;
					}
					else{depElect = d;}
				}
			}
			if(depElect==null){
				proceed = false;
				System.out.println("You didn't enter a valid department.");
			}
			else{
				if(depElect.getElectionStatus()==true){
					proceed = false;
					System.out.println("An election already took place in this department.");
				}
			}

			if(proceed){
				OldPlayer leadingPlayer;
				if(depElect.getChair()==null){
					leadingPlayer = this;
					depElect.setChair(leadingPlayer);
					leadingPlayer.setChair(depElect);
					System.out.println("You win the election - there was no opposition!");
				}
				else{
					leadingPlayer = depElect.getChair();
					OldPlayer defendingChair = leadingPlayer;
					int n = leadingPlayer.addVotes(depElect);
					System.out.println(leadingPlayer.getName()+" scored "+n+" votes.");
					for(OldPlayer p: PlayerList){
						if(!p.equals(defendingChair)){
							int m = p.addVotes(depElect);
							System.out.println(p.getName()+" scored "+m+" votes.");
							if(m>n){
								leadingPlayer = p;
								n = m;
							}
						}
					}

					if(leadingPlayer.equals(this)){
						depElect.getChair().removeChair(depElect);
						leadingPlayer.setChair(depElect);
						depElect.setChair(leadingPlayer);
						for(OldStudent s:depElect.getStudentList()){
							s.changeOwner(leadingPlayer);
						}
						System.out.println("You won the election!  All students in this department now belong to you.");
					}
					else{System.out.println("You lost the election.");
					depElect.electionPerformed();


					}
				}
			}
			System.out.println("Do you want to continue the election phase? y/n");
			if(inp.nextLine().equals("n")){
				done = true;
			}

		}



	}
}




//
//if(player.equals(_chair)){
//	if(number>=_studentList.get(i)){
//		System.out.println("You need to leave at least one student in departments that you chair.");
//		return;
//	}
//}
//if(number>_studentList.get(i)){
//	System.out.println("You don't have that many students in this department.");
//    return;
//}
//System.out.println("What department do you want to move to?");
//String dest = input.nextLine();
//if(!_adjacentList.contains(dest)){
//	System.out.println("That's not an adjacent department.");
//	return;
//}
//for(Department dep: DepList){
//	if(dep.getName().equals(dest)){
//		dep.getStudentList().set(i,dep.getStudentList().get(i)+number);
//		_studentList.set(i,_studentList.get(i)-number);
//		}
//}
//

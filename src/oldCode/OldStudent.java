package oldCode;

import java.io.*;

public class OldStudent implements Serializable {
	
	private OldPlayer owner;
	private boolean moved;
	
	public OldStudent(OldPlayer p) {
		owner = p;
	}
	
	/**
	 * changes the Owner of a student
	 * 
	 * @param the Player to become the new owner
	 * @return void
	 */	
	public void changeOwner(OldPlayer newP) {
		owner = newP;
	}
	
	/**
	 * gets the current owner of the Student
	 * 
	 * @return Player
	 */
	public OldPlayer getOwner() {
		return owner;
	}
	
	public void setMoved() {
		moved = true;
	}
	
	public void resetMoved() {
		moved = false;
	}
	
	public boolean getMoved() {
		return moved;
	}
}

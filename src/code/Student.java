package code;

public class Student {
	private Player owner;
	private boolean moved;
	
	public Student(Player p) {
		owner = p;
		moved = false;
	}
	
	
	
	// accessor and mutator methods
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player p) {
		owner = p;
	}
	
	public boolean getMoved() {
		return moved;
	}
	
	public void setMoved() {
		moved = true;
	}
	
	public void resetMoved() {
		moved = false;
	}
}

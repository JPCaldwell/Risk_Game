package oldCode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class OldDriver implements Serializable {
	
	private ArrayList<String> DepStringList;
	private ArrayList<OldDepartment> DepList;
	private ArrayList<OldPlayer> PlayerList;
	private transient Scanner input = new Scanner(System.in);
	private JFrame frame;
	private JLabel label;
	private JTextField text;
	private JButton button;
	private JPanel panel;
	
	/**
	 * instantiates and gives initial values for instance variables
	 * 
	 */
	
	public void guiStart() {
		frame = new JFrame("JFrame");
		text = new JTextField(20);
		panel = new JPanel();
		button = new JButton("This is a button");
		label = new JLabel("test");
		button.addMouseListener(new MyMouseListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(button);
		panel.add(label);
		panel.add(text);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void initialize() {
		
		
		DepStringList = new ArrayList<String>();
		fillDepStringList(DepStringList);
		
		DepList = new ArrayList<OldDepartment>();
		java.util.Collections.shuffle(DepStringList);
		PlayerList = new ArrayList<OldPlayer>();
		
		System.out.println("How many players?");
		//label.setText("How many players?");
		int numPlayers = input.nextInt();
		while (numPlayers < 2 || numPlayers > 20) {
			System.out.println("Invalid number of players. Try again: ");
			numPlayers = input.nextInt();
		}
		
		for (int i = 0; i < numPlayers; i++) {
			PlayerList.add(new OldPlayer(i+1));
		}
		
		for(int k=0;k<numPlayers;k++){
			OldDepartment department = new OldDepartment(DepStringList.get(k),PlayerList.get(k));
			DepList.add(department);
			PlayerList.get(k).setChair(department);
			department.addStudentReinforce(3, PlayerList.get(k));
		}
		
		for(int j=numPlayers;j<DepStringList.size();j++){
			OldDepartment department = new OldDepartment(DepStringList.get(j));
			DepList.add(department);
		}
		
	
	}
	
	/** 
	 * starts the game
	 * 
	 */
	
	public void startGame() {
		Scanner inp = new Scanner(System.in);
		boolean gameOver = false;
		while(!gameOver){
			for(int i=0;i<PlayerList.size();i++){
				for(OldDepartment d: DepList) {
					d.resetStudents();
					d.resetElections();
				}
				
				OldPlayer p = PlayerList.get(i);
			
				for(OldDepartment d: DepList){
					d.showInfo(PlayerList);
				}
				System.out.println("It's your turn, "+p.getName()+".");
				int s = p.calculate();
				p.reinforce(s, DepList);
				p.move(DepList);
				p.callElection(DepList, PlayerList);
				if(p.getChairList().size()==(DepList.size())){
					System.out.println("Game Over."+p.getName()+" wins!");
					gameOver = true;
					break;
				
				}
						
				
			}
			System.out.println("save? y/n");
			String input = inp.next();
			if(input.equals("y")){
				GameSave save = new GameSave(this);
				save.saveGame();
			}
			
			
		}
		
	}
	
	private void fillDepStringList(ArrayList<String> ds) {
		// arts
		DepStringList.add("Music");
		DepStringList.add("Theatre");
		DepStringList.add("Dance");
		DepStringList.add("Visual Arts");
		// sciences
		DepStringList.add("Biology");
		DepStringList.add("Chemistry");
		DepStringList.add("Physics");
		DepStringList.add("Mathematics");
		// humanities
		DepStringList.add("History");
		DepStringList.add("Linguistics");
		DepStringList.add("Literature");
		DepStringList.add("Religion");
		// engineering
		DepStringList.add("Computer Engineering");
		DepStringList.add("Electrical Engineering");
		DepStringList.add("Mechanical Engineering");
		DepStringList.add("Civil Engineering");
		// law
		DepStringList.add("Business Law");
		DepStringList.add("Family Law");
		DepStringList.add("Criminal Law");
		DepStringList.add("Immigration Law");
		// medicine
		DepStringList.add("Anesthesia");
		DepStringList.add("Otolaryngology");
		DepStringList.add("Pathology");
		DepStringList.add("Pediatrics");
	}
	
	public ArrayList<OldDepartment> getDepList() {
		return DepList;
	}

	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			text.getText();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		
	}
	

	
}

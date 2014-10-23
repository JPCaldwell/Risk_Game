package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Game extends JFrame {
	private int state;
	private int numPlayers;
	private ArrayList<School> schoolList;
	private ArrayList<Department> depList;
	private ArrayList<Player> playerList;
	private LinePanel depPanel;
	private JButton bMus, bThe, bDan, bVis,
					bBio, bChe, bPhy, bMat,
					bHis, bLin, bLit, bRel,
					bCom, bEle, bMec, bCiv,
					bBus, bFam, bCri, bImm,
					bAne, bOto, bPat, bPed;
	private ArrayList<JButton> bList;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton playerTurnLabel;
	private JButton reiStateLabel, movStateLabel, eleStateLabel;
	private JButton bCallElections, bEndTurn;
	private int numToReinforce, numReinforced;
	private Player currPlayer;
	private Department origDep;
	private JButton origDepBut;
	private ArrayList<String> playerNames;
	
	public Game() {
		state = 0;
		numPlayers = 0;
		schoolList = new ArrayList<School>();
		depList = new ArrayList<Department>();
		playerList = new ArrayList<Player>();
		playerNames = new ArrayList<String>();
		initUI(false);
	}
	
	public Game(String[] names) {
		state = 0;
		numPlayers = 0;
		schoolList = new ArrayList<School>();
		depList = new ArrayList<Department>();
		playerList = new ArrayList<Player>();
		playerNames = new ArrayList<String>();
		for (String name: names) {
			playerNames.add(name);
		}
		initUI(true);
	}
	
	// constructor for JUnit Testing
	public Game(int players) {
		state = 0;
		numPlayers = 0;
		schoolList = new ArrayList<School>();
		depList = new ArrayList<Department>();
		playerList = new ArrayList<Player>();
		playerNames = new ArrayList<String>();
		initUI(true);
	}
	
	/**
	 * 
	 * 
	 * @param playersPassed
	 */
	public final void initUI(boolean playersPassed) {	
		// setup menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setToolTipText("Start a new game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validateNewPlayerPopup();
			}
		});
		file.add(newGame);
		menuBar.add(file);
		setJMenuBar(menuBar);
		
		// setup department panel
		depPanel = new LinePanel();
		depPanel.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				depPanel.repaint();
			}
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
			@Override
			public void componentMoved(ComponentEvent arg0) {	
			}
			@Override
			public void componentShown(ComponentEvent arg0) {		
			}
		});
		depPanel.setLayout(new GridLayout(13, 13));
		bList = new ArrayList<JButton>();
		bList.add(bMus = new JButton("Music"));
		bList.add(bThe = new JButton("Theatre"));
		bList.add(bDan = new JButton("Dance"));
		bList.add(bVis = new JButton("Visual Arts"));
		bList.add(bBio = new JButton("Biology"));
		bList.add(bChe = new JButton("Chemistry"));
		bList.add(bPhy = new JButton("Physics"));
		bList.add(bMat = new JButton("Mathematics"));
		bList.add(bHis = new JButton("History"));
		bList.add(bLin = new JButton("Linguistics"));
		bList.add(bLit = new JButton("Literature"));
		bList.add(bRel = new JButton("Religion"));
		bList.add(bCom = new JButton("Computer Engineering"));
		bList.add(bEle = new JButton("Electrical Engineering"));
		bList.add(bMec = new JButton("Mechanical Engineering"));
		bList.add(bCiv = new JButton("Civil Engineering"));
		bList.add(bBus = new JButton("Business Law"));
		bList.add(bFam = new JButton("Family Law"));
		bList.add(bCri = new JButton("Criminal Law"));
		bList.add(bImm = new JButton("Immigration Law"));
		bList.add(bAne = new JButton("Anesthesia"));
		bList.add(bOto = new JButton("Otolaryngology"));
		bList.add(bPed = new JButton("Pediatrics"));
		bList.add(bPat = new JButton("Pathology"));
		for (int i = 0; i < 169; i++) {
			switch(i) {
			case 14: depPanel.add(bHis); break;
			case 15: depPanel.add(bRel); break;
			case 23: depPanel.add(bMat); break;
			case 24: depPanel.add(bBio); break;
			case 27: depPanel.add(bLin); break;
			case 28: depPanel.add(bLit); break;
			case 36: depPanel.add(bChe); break;
			case 37: depPanel.add(bPhy); break;
			case 59: depPanel.add(bPed); break;
			case 60: depPanel.add(bAne); break;
			case 72: depPanel.add(bPat); break;
			case 73: depPanel.add(bOto); break;
			case 95: depPanel.add(bImm); break;
			case 96: depPanel.add(bCri); break;
			case 108: depPanel.add(bBus); break;
			case 109: depPanel.add(bFam); break;
			case 131: depPanel.add(bCom); break;
			case 132: depPanel.add(bCiv); break;
			case 140: depPanel.add(bThe); break;
			case 141: depPanel.add(bMus); break;
			case 144: depPanel.add(bEle); break;
			case 145: depPanel.add(bMec); break;
			case 153: depPanel.add(bVis); break;
			case 154: depPanel.add(bDan); break;
			default: depPanel.add(new JLabel("")); break;
			}
		}
		DepButtonListener depButtonListener = new DepButtonListener();
		for (JButton b: bList) {
			b.addMouseListener(depButtonListener);
			b.setBackground(new Color(192,192,192));
		}
		add(depPanel);
		depPanel.setVisible(false);
		
		// bottom panel
		JPanel bottomOuterPanel = new JPanel();
		bottomOuterPanel.setLayout(new BoxLayout(bottomOuterPanel, BoxLayout.X_AXIS));
		JPanel bottomInnerPanel = new JPanel();
		bottomInnerPanel.setLayout(new BoxLayout(bottomInnerPanel, BoxLayout.Y_AXIS));
			// left info box
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setColumns(30);
		textArea.setRows(5);
		scrollPane.setPreferredSize(new Dimension(500, 100));
		scrollPane.setAlignmentX(RIGHT_ALIGNMENT);
			// panel for state buttons
		JPanel stateRowPanel = new JPanel();
		stateRowPanel.setLayout(new BoxLayout(stateRowPanel, BoxLayout.X_AXIS));
			// current state labels
				// player label for whose turn it is
		playerTurnLabel = new JButton("");
		playerTurnLabel.setPreferredSize(new Dimension(180, 20));
		playerTurnLabel.setAlignmentX(LEFT_ALIGNMENT);
		playerTurnLabel.setEnabled(false);
				// reinforce label
		reiStateLabel = new JButton("Reinforce");
		reiStateLabel.setOpaque(true);
		reiStateLabel.setPreferredSize(new Dimension(90,20));
		reiStateLabel.setEnabled(false);
				// move label
		movStateLabel = new JButton("Move");
		movStateLabel.setOpaque(true);
		movStateLabel.setPreferredSize(new Dimension(90,20));
		movStateLabel.setEnabled(false);
				// election label
		eleStateLabel = new JButton("Elections");
		eleStateLabel.setOpaque(true);
		eleStateLabel.setMinimumSize(new Dimension(90,20));
		eleStateLabel.setEnabled(false);
			// change state buttons
		bCallElections = new JButton("Call Elections");
		bEndTurn = new JButton("End Turn");
		StateChangeButtonListener scbl = new StateChangeButtonListener();
		bCallElections.addMouseListener(scbl);
		bEndTurn.addMouseListener(scbl);
		stateRowPanel.add(playerTurnLabel);
		stateRowPanel.add(reiStateLabel);
		stateRowPanel.add(movStateLabel);
		stateRowPanel.add(eleStateLabel);
		stateRowPanel.add(bCallElections);
		stateRowPanel.add(bEndTurn);
		stateRowPanel.add(Box.createHorizontalGlue());
		bottomInnerPanel.add(Box.createVerticalGlue());
		bottomInnerPanel.add(stateRowPanel);
		bottomInnerPanel.add(scrollPane);
		bottomOuterPanel.add(bottomInnerPanel);
		bottomOuterPanel.add(Box.createHorizontalGlue());
		add(bottomOuterPanel, BorderLayout.SOUTH);
		
		// final changes to main window
		setTitle("Call of Uni"); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		// start game if players passed in
		if (playersPassed) {
			startGameWithPlayers();
		}
	}
	
	/**
	 * 
	 * 
	 * @author wymyczak
	 *
	 */
	class DepButtonListener extends MouseAdapter {
		final int defaultTimeout = ToolTipManager.sharedInstance().getInitialDelay();
		public void mouseClicked(MouseEvent e) {
			JButton b = (JButton) e.getComponent();
			if (e.getButton() == MouseEvent.BUTTON1) {
				Border origBorder = b.getBorder();
				switch(state) {
				// add reinforce students to selected department
				case 0: doReinforceClick(b.getText()); break;
				// check that player has students in selected move origin department
				case 1: if (doMoveOne(b.getText())) {
							b.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
						}
						origDepBut = b;
						break;
				// do move if destination department is connected to origin
				case 2: doMoveTwo(b.getText()); 
						origDepBut.setBorder(origBorder);
						break;
				case 3: doElection(b.getText());
						break;
				}
			}
		}
		public void mouseEntered(MouseEvent e) {
			ToolTipManager.sharedInstance().setInitialDelay(0);
			JButton b = (JButton) e.getSource();
			b.setToolTipText(showDepInfo(b.getText()));
		}
		public void mouseExited(MouseEvent e) {
			ToolTipManager.sharedInstance().setInitialDelay(defaultTimeout);
		}
	}
	
	class StateChangeButtonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JButton b = (JButton) e.getComponent();
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (b.getText().equals("Call Elections")) {
					state = 3;
					changeStateButtons();
					textArea.append(currPlayer.getName() + " is now in election phase\n");
				}
				if (b.getText().equals("End Turn")) {
					newTurn();
				}
			}
		}
	}
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (args.length != 0) {
					Game game = new Game(args);
					game.setVisible(true);
				}
				else {
					Game game = new Game();
					game.setVisible(true);
				}
			}
		});
	}
	
	/**
	 * Initializes all the variables in the game.
	 * 
	 */
	private void setupGame() {
		// reset ArrayLists and remove object references for previous game, if any
		
		// initialize schools
		for (String s: returnSchoolList()) {
			schoolList.add(new School(s));
		}
		// initialize departments
		for (int i = 0; i < 6; i++) {
			School s = schoolList.get(i);
			for (int j = 0; j < 4; j++) {
				String[] name = returnDepartmentList(s.getName());
				depList.add(new Department(name[j]));
				s.addDepartment(depList.get((i*4)+j));
			}
		}
		// initialize players
		if (playerNames.isEmpty()) {
			for (int i = 0; i < numPlayers; i++) {
				playerList.add(new Player(i+1));
			}
		}
		else {
			for (String name: playerNames) {
				playerList.add(new Player(name));
			}
		}
		// assign random department to each player
		java.util.Collections.shuffle(depList);
		for (int i = 0; i < numPlayers; i++) {
			depList.get(i).setChair(playerList.get(i));
			depList.get(i).addPlayer(playerList.get(i));
			playerList.get(i).addChair(depList.get(i));
		}
		for (Player p: playerList) {
			for (int i = 0; i < 3; i++) {
				p.getChairList().get(0).addStudents(new Student(p));
			}
		}
		// assign initial variables
		state = 0;
		changeStateButtons();
		currPlayer = playerList.get(0);
		playerTurnLabel.setText(currPlayer.getName());
		playerTurnLabel.setBackground(getPlayerColor(playerList.indexOf(currPlayer)));
		numReinforced = 0;
		for (Department d: depList) {
			d.resetStudents();
			d.resetElections();
		}
		numToReinforce = currPlayer.calculateReinforce();
		updateDepColors();
		printInitialState();
		textArea.append(printGameState());
		depPanel.repaint();
	}
	
	/**
	 * Sets the number of players in the game when a user clicks File>New Game in the GUI.
	 */
	// validation method for when someone clicks File>New Game
	private void validateNewPlayerPopup() {
		// validate input
		numPlayers = 0;
		boolean isInt = true;
		boolean cancelClicked = false;
		int loopCount = 0;
		String tempString = "";
		do {
			do {
				if (loopCount == 0) {
					tempString = JOptionPane.showInputDialog("How many players?");
				}
				else {
					tempString = JOptionPane.showInputDialog("Invalid input. Please try again.\n\nHow many players?");
				}
				// skip if player clicked cancel
				if (tempString != null) {
					isInt = true;
					try {
						numPlayers = Integer.parseInt(tempString);
					}
					// catch if input is not an int
					catch (NumberFormatException e) {
						isInt = false;
						numPlayers = 0;
					}
					loopCount++;
				}
				else {
					cancelClicked = true;
				}
			} while (!isInt && !cancelClicked);
		} while (((numPlayers < 2) || (numPlayers > 20)) && !cancelClicked);
		if (!cancelClicked) {
			setupGame();
			depPanel.setVisible(true);
			pack();
		}
	}
	
	private void startGameWithPlayers() {
		numPlayers = playerNames.size();
		setupGame();
		depPanel.setVisible(true);
		pack();
	}
	
	/**
	 * Handles the reinforce event at the beginning of each player's turn. Each player
	 * is given students based on the number of departments and schools they own, and
	 * the player then has to click a department where they have students in order to
	 * make a valid reinforce. Additionally, the player cannot reinforce more students
	 * than the number they are given at the start of their turn.
	 * 
	 * @param 	depName the name of the department the player clicked to reinforce.
	 */
	private void doReinforceClick(String depName) {
		Department dep = getDepartment(depName);
		// check if dept has current player in it
		if (dep.getPlayers().contains(currPlayer)) {
			int available = numToReinforce - numReinforced;
			// validate input
			int count = -1;
			int loopCount = 0;
			boolean isInt = true;
			boolean cancelClicked = false;
			String tempString = "";
			do {
				do {
					if (loopCount == 0) {
						tempString = JOptionPane.showInputDialog("How many students do you want to add to "
								+ depName + "?" + "\n\nYou have " + available + " students to reinforce.");
					}
					else {
						tempString = JOptionPane.showInputDialog("Invalid input. Please try again.\n\n" +
								"How many students do you want to add to " + depName + "?" + "\n\nYou have " + available + 
								" students to reinforce.");
					}
					// skip if player clicked cancel
					if (tempString != null) {
						isInt = true;
						try {
							count = Integer.parseInt(tempString);
						}
						// catch if input is not an int
						catch (NumberFormatException e) {
							isInt = false;
							count = -1;
						}
						loopCount++;
					}
					else {
						cancelClicked = true;
					}
				} while (!isInt && !cancelClicked);
			} while (((count < 0) || (count > available)) && !cancelClicked);
			// add students if player didn't click cancel
			if (!cancelClicked) {
				for (int i = 0; i < count; i++) {
					dep.addStudents(new Student(currPlayer));
				}
				// check if reinforce state is over
				numReinforced = numReinforced + count;
				if (numReinforced == numToReinforce) {
					state = 1;
					changeStateButtons();
					textArea.append("" + currPlayer.getName() + " is now in move phase\n");
				}
			}
		}
	}

	/**
	 * Handles the first part of the move event in the game. When the current player is
	 * in the move phase, they first click a department where they have students, and this
	 * department serves as the source of the move. 
	 * 
	 * @param 	depName the name of the department the player clicked on to serve as the 
	 * 			source of the move.
	 * @return	true if the player has students in the department clicked.
	 */
	private boolean doMoveOne(String depName) {
		Department dep = getDepartment(depName);
		boolean owned = false;
		if (dep.getPlayers().contains(currPlayer)) {
			for (Student s: dep.getStudents()) {
				if (!s.getMoved()) {
					origDep = dep;
					state = 2;
					owned = true;
				}
			}
		}
		return owned;
	}

	/**
	 * Handles the second part of the move event. When the current player clicks a
	 * department to serve as the destination of the move, checks are made to make
	 * sure the source department and destination department are adjacent to each
	 * other. Additionally, if the move is possible, the player is asked how many
	 * students they would like to move from the source department, and must enter 
	 * a valid number so that the player is not moving more students than they have
	 * in the source department, and so that the player is leaving at least one student
	 * in the source department if they are the chair of that department.
	 * 
	 * @param 	depName the name of the department the player clicked on to serve as
	 * 			the destination department.
	 */
	private void doMoveTwo(String depName) {
		// skip if department is not adjacent
		if (origDep.checkAdjacent(depName) && !origDep.getName().equals(depName)) {
			Department dep = getDepartment(depName);
			int numToMove = -1;
			// count number of students available to move
			int numAvail = 0;
			// set one student to moved if currPlayer is chair
			if (currPlayer.getChairList().contains(origDep)) {
				boolean stuMov = false;
				int i = 0;
				while (!stuMov) {
					if (origDep.getStudents().get(i).getOwner().equals(currPlayer)) {
						origDep.getStudents().get(i).setMoved();
						stuMov = true;
					}
				}
			}
			for (Student s: origDep.getStudents()) {
				if (s.getOwner().equals(currPlayer) && !s.getMoved()) {
					numAvail++;
				}
			}
			// skip department if no moves are available
			if (numAvail > 0) {
				// validate input
				boolean isInt = true;
				boolean cancelClicked = false;
				int loopCount = 0;
				String tempString = "";
				do {
					do {
						if (loopCount == 0) {
							tempString = JOptionPane.showInputDialog("How many students do you want to move to " + depName + "?" + 
									"\n\nYou have " + numAvail + " students to move.");
						}
						else {
							tempString = JOptionPane.showInputDialog("Invalid input. Please try again.\n\nHow many students do you want to move to " + 
									depName + "?" + "\n\nYou have " + numAvail + " students to move.");
						}
						// skip if player clicked cancel
						if (tempString != null) {
							isInt = true;
							try {
								numToMove = Integer.parseInt(tempString);
							}
							// catch if input is not an int
							catch (NumberFormatException e) {
								isInt = false;
								numToMove = -1;
							}
							loopCount++;
						}
						else {
							cancelClicked = true;
						}
					} while (!isInt && !cancelClicked);
				} while (((numToMove < 0) || (numToMove > numAvail)) && !cancelClicked);
				// valid input and OK entered
				if (!cancelClicked) {
					// perform move
					for (int i = 0; i < numToMove; i++) {
						origDep.removeStudent(currPlayer);
						Student s = new Student(currPlayer);
						dep.addStudents(s);
						s.setMoved();
					}
					// check if dest dep has player. Add if not
					if (!dep.getPlayers().contains(currPlayer)) {
						dep.addPlayer(currPlayer);
					}
				}
			}
		}
		state = 1;
	}
	
	/**
	 * Handles the election event of the game. 
	 * 
	 * @param depName
	 */
	private void doElection(String depName) {
		Department dep = getDepartment(depName);
		// check if dept hasn't had an election called yet and has current player in it
		int forCount = 0;
		int againstCount = 0;
		// perform election
		if (!dep.getElection() && dep.getPlayers().contains(currPlayer) && !dep.getChair().equals(currPlayer)) {
			for (Student s: dep.getStudents()) {
				double vote = Math.random();
				if (s.getOwner().equals(currPlayer) && (vote < .667)) {
					forCount++;
				}
				else if (!s.getOwner().equals(currPlayer) && (vote < .333)) {
					forCount++;
				}
				else {
					againstCount++;
				}
			}
			// change variables if currPlayer wins elections
			if (forCount > againstCount) {
				textArea.append(currPlayer.getName() + " won the election at " + dep.getName() + 
									"\nwith " + forCount + " votes for and " + againstCount + 
									" votes against\n");
				dep.getChair().removeChair(dep);
				dep.setChair(currPlayer);
				currPlayer.addChair(dep);
				for (Student s: dep.getStudents()) {
					if (!s.getOwner().equals(currPlayer)) {
						s.setOwner(currPlayer);
					}
				}
				for (Player p: dep.getPlayers()) {
					if (!p.equals(currPlayer)) {
						dep.removePlayer(p);
					}
				}
				updateDepColors();
				// check if any player is eliminated
				ArrayList<Player> tempRemove = new ArrayList<Player>();
				for (Player p: playerList) {
					int depCount = 0;
					for (Department d: depList) {
						if (d.getPlayers().contains(p)) {
							depCount++;
						}
					}
					if (depCount == 0) {
						tempRemove.add(p);
					}
				}
				if (!tempRemove.isEmpty()) {
					for (Player p: tempRemove) {
						playerList.remove(p);
					}
				}
				// update dean status of school dep is contained in
				for (School s: schoolList) {
					String prevDean = s.getDean().getName();
					if (s.getDepartments().contains(dep)) {
						s.calculateDean(currPlayer);
						if (!s.getDean().getName().equals(prevDean) && !s.getDean().getName().equals("")) {
							textArea.append(s.getDean().getName() + " is now dean of " + s.getName() + "\n");
						}
						else if (!s.getDean().getName().equals(prevDean) && s.getDean().getName().equals("")) {
							textArea.append(prevDean + " is no longer dean of " + s.getName() + "\n");
						}
					}
				}
				if (playerList.size() == 1) {
					endGame(currPlayer);
				}
				dep.setElection();
			}
			else if ((forCount == againstCount) || (forCount < againstCount)) {
				textArea.append(currPlayer.getName() + " lost the election at " + dep.getName() + 
						"\nwith " + forCount + " votes for and " + againstCount + 
						" votes against\n");
				dep.setElection();
			}
		}
		else if (dep.getChair().equals(currPlayer)) {
			textArea.append("You are already chair of " + dep.getName() + "\n");
		}
	}
	
	private void newTurn() {
		state = 0;
		changeStateButtons();
		changePlayer();
		playerTurnLabel.setText(currPlayer.getName());
		playerTurnLabel.setBackground(getPlayerColor(playerList.indexOf(currPlayer)));
		numReinforced = 0;
		for (Department d: depList) {
			d.resetStudents();
			d.resetElections();
		}
		numToReinforce = currPlayer.calculateReinforce();
		textArea.append(printGameState());
	}
	
	private void changePlayer() {
		int n = 0;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).equals(currPlayer)) {
				n = i;
			}
		}
		if ((n+1) == playerList.size()) {
			currPlayer = playerList.get(0);
		}
		else {
			currPlayer = playerList.get(n+1);
		}
	}
	
	private void endGame(Player p) {
		state = 4;
		JOptionPane.showMessageDialog(depPanel, p.getName() + " is the winner!");
	}
	
	// method to print initial department gains at start of game
	private void printInitialState() {
		String message = "";
		for (int i = 0; i < numPlayers; i++) {
			message = "" + playerList.get(i).getName() + " now owns " + depList.get(i).getName() + "\n";
			textArea.append(message);
		}
	}
	
	// method to print whose turn it is and relevant information for current game state in info box
	private String printGameState() {
		String message = "";
		switch (state) {
		case 0: message = "" + currPlayer.getName() + "'s turn\n";
				message = message + currPlayer.getName() + " gained " + numToReinforce + " reinforcements\n";
				message = message + currPlayer.getName() + " is in reinforce phase\n";
				break;
		}
		return message;
	}
	
	// method to print department showInfo popups
	private String showDepInfo(String depName) {
		String depInfo = "0";
		for (Department d: depList) {
			if (d.getName().equals(depName)) {
				depInfo = d.printInfo();
			}
		}
		return depInfo;
	}
	
	private void changeStateButtons() {
		Border origBorder = bLin.getBorder();
		switch (state) {
		case 0: reiStateLabel.setEnabled(true);
				reiStateLabel.setBorder(new MatteBorder(5,5,5,5,Color.YELLOW));
				movStateLabel.setEnabled(false);
				movStateLabel.setBorder(origBorder);
				eleStateLabel.setEnabled(false);
				eleStateLabel.setBorder(origBorder);
				break;
		case 1:	reiStateLabel.setEnabled(false);
				reiStateLabel.setBorder(origBorder);
				movStateLabel.setEnabled(true);
				movStateLabel.setBorder(new MatteBorder(5,5,5,5,Color.YELLOW));
				eleStateLabel.setEnabled(false);
				eleStateLabel.setBorder(origBorder);
				break;
		case 3:	reiStateLabel.setEnabled(false);
				reiStateLabel.setBorder(origBorder);
				movStateLabel.setEnabled(false);
				movStateLabel.setBorder(origBorder);
				eleStateLabel.setEnabled(true);
				eleStateLabel.setBorder(new MatteBorder(5,5,5,5,Color.YELLOW));
				break;
		}
	}
	
	private void updateDepColors() {
		for (JButton b: bList) {
			for (Player p: playerList) {
				if (getDepartment(b.getText()).getChair().equals(p)) {
					b.setBackground(getPlayerColor(playerList.indexOf(p)));
				}
			}
		}
	}
	
	private Color getPlayerColor(int i) {
		Color[] colors = {new Color(255,0,0), 	// red			1
				  new Color(0,255,0), 	// lime			2
				  new Color(0,0,255), 	// blue			3
				  new Color(255,255,0), // yellow		4
				  new Color(0,255,255), // cyan			5
				  new Color(255,0,255), // magenta		6
				  new Color(128,0,0), 	// maroon 		7
				  new Color(128,128,0), // olive		8
				  new Color(0,128,0), 	// green		9
				  new Color(128,0,128), // purple		10
				  new Color(0,128,128), // teal			11
				  new Color(0,0,128), 	// navy			12
				  new Color(220,20,60), // crimson		13
				  new Color(124,252,0),	// lawn green	14
				  new Color(65,105,225),// royal blue	15
				  new Color(240,230,140),// khaki		16
				  new Color(143,188,143),// dark sea gr 17
				  new Color(255,192,203),// pink		18
				  new Color(250,128,114),// salmon		19
				  new Color(85,107,47),	// dark olive g 20
				  };
		return colors[i];
	}
	
	public class LinePanel extends JPanel {
		@Override public void paintChildren(Graphics g) {
			super.paintChildren(g);
			g.drawLine(bLin.getX()+bLin.getWidth()/2,bLin.getY()+bLin.getHeight(),bCom.getX()+bCom.getWidth()/2,bCom.getY());
			g.drawLine(bPhy.getX()+bPhy.getWidth()/2,bPhy.getY()+bPhy.getHeight(),bMus.getX()+bMus.getWidth()/2,bMus.getY());
			g.drawLine(bRel.getX()+bRel.getWidth(),bRel.getY()+bRel.getHeight()/2,bMat.getX(),bMat.getY()+bMat.getHeight()/2);
			g.drawLine(bMec.getX()+bMec.getWidth(),bMec.getY()+bMec.getHeight()/2,bVis.getX(),bVis.getY()+bVis.getHeight()/2);
			g.drawLine(bCiv.getX()+bCiv.getWidth(),bCiv.getY(),bBus.getX(),bBus.getY()+bBus.getHeight());
			g.drawLine(bCri.getX()+bCri.getWidth(),bCri.getY(),bPat.getX(),bPat.getY()+bPat.getHeight());
			g.drawLine(bAne.getX()+bAne.getWidth(),bAne.getY(),bChe.getX(),bChe.getY()+bChe.getHeight());
		}
	}
	
	// method to return a department object from depList based on name
	private Department getDepartment(String depName) {
		Department dep = new Department("");
		for (Department d: depList) {
			if (d.getName().equals(depName)) {
				dep = d;
			}
		}
		return dep;
	}
	
	private String[] returnSchoolList() {
		String[] schoolNames = {"Arts", "Sciences", "Humanities", "Engineering", "Law", "Medicine"};
		return schoolNames;
	}
	
	private String[] returnDepartmentList(String s) {
		String[] departmentNames = new String[4];
		
		if (s.equals("Arts")) {
			departmentNames[0] = "Music";
			departmentNames[1] = "Theatre";
			departmentNames[2] = "Dance";
			departmentNames[3] = "Visual Arts";
		}
		else if (s.equals("Sciences")) {
			departmentNames[0] = "Biology";
			departmentNames[1] = "Chemistry";
			departmentNames[2] = "Physics";
			departmentNames[3] = "Mathematics";
		}
		else if (s.equals("Humanities")) {
			departmentNames[0] = "History";
			departmentNames[1] = "Linguistics";
			departmentNames[2] = "Literature";
			departmentNames[3] = "Religion";
		}
		else if (s.equals("Engineering")) {
			departmentNames[0] = "Computer Engineering";
			departmentNames[1] = "Electrical Engineering";
			departmentNames[2] = "Mechanical Engineering";
			departmentNames[3] = "Civil Engineering";
		}
		else if (s.equals("Law")) {
			departmentNames[0] = "Business Law";
			departmentNames[1] = "Family Law";
			departmentNames[2] = "Criminal Law";
			departmentNames[3] = "Immigration Law";
		}
		else {
			departmentNames[0] = "Anesthesia";
			departmentNames[1] = "Otolaryngology";
			departmentNames[2] = "Pathology";
			departmentNames[3] = "Pediatrics";
		}	
		return departmentNames;
	}
}

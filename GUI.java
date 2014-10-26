import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class GUI {

	
	//whether we go first or second
	private int turnOrder;
	
	//client for sending messages
	private Client client;
	
	//private JLabel label;
	
	private JButton button;
	
	//Check for duplicate
	//private JCheckBox checkBox;
	
	//Store hints
	private JPanel hintPanel;
	
	//Store p2 hints
	private JPanel p2hintPanel;
	
	//Frame for grid
	private JFrame gameGrid;
	
	//Holds the buttons
	private JPanel gameButtonGrid;
	
	//Hold the p2 buttons
	private JPanel p2gameButtonGrid;
	
	//Holds buttons for multiplayer
	private JPanel gridMult;
	
	//Label that indicates the status of the game (your turn, waiting on opponent, etc.)
	private String turnIndicator;
	
	//Store the move of multiplayer
	private int multMove = 0;
	
	//Panel for player 2
	private JPanel p2Panel;
	
	//Mastermind game
	private MasterMindGame mmg;
	
	//P2 mastermind game
	private MasterMindGame p2mmg;

	//private JFrame startScreen;
	
	//Input
	private Integer inputToUse = -1;
	
	//Timer
	private JLabel inputTimer;
	
	//Int for timer
	private int secPassed = 0;
	private int minPassed = 0;
	
	//Show the input selected
	private JLabel inputIndicator;
	
	//Show that the board is not used
	//private JLabel player2;
	
	//Show how many players
	private int player;

	//AI
	private AI ai;

	//Panel for input
	private JPanel inputLabel;
	
	//Control for timer to only initiate once
	private boolean timerSet;
	
	//Global timer
	Timer t;
	
	//StartScreen frame
	private JFrame startFrame;
	
	//Instructions frame
	private JFrame instFrame;
	
	//Multiplayer solution selectrion frame
	private JFrame multiplayerFrame;
	
	//Constructor for GUI
	public GUI() {
		//mmg = game;
		ai = null;
		timerSet = false;
	}
	
	public void setTurn(int turn) {
		turnOrder = turn;
	}
	
	public int getTurnOrder() {
		return turnOrder;
	}
	
	//Create a new client
	public void createClient() throws Exception {
		client = new Client("wagner.orchestra.cse.unsw.EDU.AU", 25000);
	}
	
	//Return the client
	public Client getClient() {
		return client;
	}
	
	//Set the game
	public void setGame(MasterMindGame s) {
		mmg = s;
	}
	
	//Return the game
	public MasterMindGame getGame() {
		return mmg;
	}
		
	//Set single player
	public void setNumPlayer(int num) {
		player = num;
	}
	
	//gets the turn indicator panel
	public void setTurnLabel(String s)
	{
		turnIndicator = s;
	}
	
	public void createStartScreen() {
		JPanel startPanel = new JPanel();
		JPanel singlePanel = new JPanel();
		JPanel aiPanel = new JPanel();
		JPanel multiPanel = new JPanel();
		JLabel startSingleLabel = new JLabel("Single Player");
		JLabel startAILabel = new JLabel("Vs AI");
		JLabel startMultiLabel = new JLabel("Multiplayer");
		
		//Action Listener for start screen
		StartScreenHandler ssHandler = new StartScreenHandler(this);
		
		//Single PLayer Buttons
		JButton singleEasyButton = new JButton("Easy");
		singleEasyButton.addActionListener(ssHandler);
		singleEasyButton.setActionCommand("newSE");
		
		JButton singleMediumButton = new JButton("Medium");
		singleMediumButton.addActionListener(ssHandler);
		singleMediumButton.setActionCommand("newSM");
		JButton singleHardButton = new JButton("Hard");
		singleHardButton.addActionListener(ssHandler);
		singleHardButton.setActionCommand("newSH");
		
		//AI Buttons
		JButton AIEasyButton = new JButton("Easy");
		AIEasyButton.addActionListener(ssHandler);
		AIEasyButton.setActionCommand("newAIE");
		JButton AIMediumButton = new JButton("Medium");
		AIMediumButton.addActionListener(ssHandler);
		AIMediumButton.setActionCommand("newAIM");
		JButton AIHardButton = new JButton("Hard");
		AIHardButton.addActionListener(ssHandler);
		AIHardButton.setActionCommand("newAIH");
		
		//Multiplayer Button
		JButton MultiButton = new JButton("New");
		MultiButton.setActionCommand("newMulti");
		MultiButton.addActionListener(ssHandler);
		JRadioButton duplicateToggle = new JRadioButton("Allow Duplicates in Solution");
		duplicateToggle.addActionListener(ssHandler);
		
		startPanel.setLayout(new GridLayout(3,3));
			
		singlePanel.add(startSingleLabel);
		singlePanel.add(singleEasyButton);
		singlePanel.add(singleMediumButton);
		singlePanel.add(singleHardButton);
		
		aiPanel.add(startAILabel);
		aiPanel.add(AIEasyButton);
		aiPanel.add(AIMediumButton);
		aiPanel.add(AIHardButton);
		
		multiPanel.add(startMultiLabel);
		multiPanel.add(MultiButton);
		multiPanel.add(duplicateToggle);
		
		startPanel.add(singlePanel);
		startPanel.add(aiPanel);
		startPanel.add(multiPanel);
		
		//Add title for game
		JPanel titlePanel = new JPanel();
		JLabel heading = new JLabel("Mastermind");
		heading.setFont(new Font("TimesRoman", Font.BOLD, 28));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(heading);
		
		//Add instructions button
		JPanel instPanel = new JPanel();
		JButton instButton = new JButton("Rules");
		instButton.addActionListener(ssHandler);
		instButton.setName("inst");
		instButton.setVisible(true);
		instButton.setActionCommand("rules");
		instPanel.setLayout(new FlowLayout());
		instPanel.add(instButton);
		
		startFrame = new JFrame("Mastermind");
		startFrame.setPreferredSize(new Dimension(400,300));
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		startFrame.setLayout(new BorderLayout());
		startFrame.add(startPanel, BorderLayout.CENTER);
		startFrame.add(titlePanel, BorderLayout.NORTH);
		startFrame.add(instPanel, BorderLayout.SOUTH);
		startFrame.pack();
		startFrame.setResizable(true);
		startFrame.setVisible(true);
			
	}	
	
	//Creates the gui
	public void createGUI() {
		
		//initializing the game grid
		gameButtonGrid = new JPanel();
		gameButtonGrid.setLayout(new GridLayout(mmg.getMaxGuessAmt(),4));
		gameButtonGrid.setPreferredSize(new Dimension(320,320));
		Integer i = 1;
		Integer x = 0;
		Integer y = 0;
		
		hintPanel = new JPanel();
		hintPanel.setLayout(new GridLayout(mmg.getMaxGuessAmt()*2,2));
		hintPanel.setPreferredSize(new Dimension (80,320));
		for(int k = 0; k < mmg.getMaxGuessAmt() * 4; k++) {
			//int m = mmg.getMaxGuessAmt()*4-k;
			int mod = mmg.getMaxGuessAmt() - k/4;
			button = new JButton();
			button.setName("" + mod);
			button.setPreferredSize(new Dimension(10,10)); //was 40, 40
			hintPanel.add(button, k);
			button.setText(button.getName());
			button.setEnabled(false);
			button.setToolTipText("Hint for guess " + mod);
		}
		
		GridHandler gridButtonHandler = new GridHandler(mmg, this);
		HotkeyHandler hotkey = new HotkeyHandler(this);
		//adding the buttons to the grid
		while (i <= mmg.getMaxGuessAmt() * 4) {
			
			button = new JButton();
			//Dummy used to make sure user doesn't double click
			button.setText("clear");
			button.setForeground(Color.gray);
			button.setBackground(Color.gray);
			
			//Give them a label?
			String label = "";
			label = label.concat(x.toString());
			label = label.concat(y.toString());
			button.setName(label);
			//button.setText(label);
			
			//Set size
			button.setPreferredSize(new Dimension(30, 30));
			button.addActionListener(gridButtonHandler);
			button.addKeyListener(hotkey);
			gameButtonGrid.add(button);
			i++;
			
			//Lock buttons above the guess row
			if (i < (mmg.getMaxGuessAmt() * 4 - 2)) {
				button.setEnabled(false);
			}
			
			if (x < 3) {
				x++;
			} else {
				x = 0;
				y++;
			}
		}
				
		inputLabel = new JPanel();
		//making room for the input label	
		inputLabel.setLayout(new FlowLayout());
		inputLabel.setForeground(Color.white);		
		
		//creating label to indicate selected input	
		JPanel inputLabel = new JPanel();	
		JLabel title = new JLabel("Selected Input:");	
		title.setForeground(Color.black);
		JLabel input = new JLabel("None");
		input.setForeground(Color.black);		
		
		this.inputIndicator = input;
		
		//Make timer
		//creating label to indicate selected input	
		JPanel timerPanel = new JPanel();	
		JLabel timerLabel = new JLabel("Time is: ");	
		timerLabel.setForeground(Color.black);
		inputTimer = new JLabel("0 sec passed");
				
		//Only instantiate timer once
		if (timerSet == false) {
			setupTimer();
			timerSet = true;
		} else {
			resetTimer();
		}
				
		timerLabel.setForeground(Color.black);		
		timerPanel.add(timerLabel, BorderLayout.NORTH);		
		timerPanel.add(inputTimer, BorderLayout.SOUTH);	
		
		//Add all the stuff onto the input label
		inputLabel.add(title, BorderLayout.NORTH);		
		inputLabel.add(input, BorderLayout.SOUTH);	
		inputLabel.add(timerPanel, BorderLayout.EAST);
		
		//Making the input grid
		JPanel inputGrid = new JPanel();
		inputGrid.setLayout(new GridLayout(3,3));
		inputGrid.setPreferredSize(new Dimension(200,200));
		inputGrid.setOpaque(false);
		
		InputColourHandler inputHandler = new InputColourHandler(this);
		
		//Set up inputs
		button = new JButton();
		button.setName("red");
		button.setText("RED");
		button.setBackground(Color.red);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);			
		
		button = new JButton();
		button.setName("green");
		button.setText("GREEN");
		button.setBackground(Color.green);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("blue");
		button.setBackground(Color.blue);
		button.setText("BLUE");
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("yellow");
		button.setText("YELLOW");
		button.setBackground(Color.yellow);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("white");
		button.setText("WHITE");
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("pink");
		button.setText("PINK");
		button.setBackground(Color.pink);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		//input panel consists of the input grid and the input label		
		JPanel inputPanel = new JPanel();		
		inputPanel.setLayout(new BorderLayout());		
		inputPanel.add(inputLabel, BorderLayout.NORTH);	
		inputPanel.add(inputGrid, BorderLayout.CENTER);
		
		//Move timer here 
		
		
		//making miscellaneous buttons		
		JPanel miscButtons	= new JPanel();	
		MiscHandler miscButtonHandler = null;
		
		//Check if versing ai
		if (ai != null) {
			miscButtonHandler = new MiscHandler(mmg, this, ai);	
		} else {
			miscButtonHandler = new MiscHandler(mmg, this, null);	
		}
		
		miscButtons.setLayout(new GridLayout(4,1));
		miscButtons.setPreferredSize(new Dimension(100, 180));
		
		button = new JButton("Check");	
		button.setPreferredSize(new Dimension(100, 50));		
		button.addActionListener(miscButtonHandler);	
		button.setToolTipText("Click on this button to check if you are correct!");	
		button.addKeyListener(hotkey);
		miscButtons.add(button);		
		
		button = new JButton("Clear");		
		button.setPreferredSize(new Dimension(100, 50));		
		button.addActionListener(miscButtonHandler);
		button.setToolTipText("Click on this button to clear your current guess!");
		button.addKeyListener(hotkey);
		miscButtons.add(button);
		
		//Check if multiplayer
		if (this.getClient() == null) {
			button = new JButton("Reset");		
			button.setPreferredSize(new Dimension(100, 50));		
			button.addActionListener(miscButtonHandler);
			button.setToolTipText("Click on this button to reset the whole puzzle!");
			button.addKeyListener(hotkey);
			miscButtons.add(button);
		} else {
			button = new JButton("Exit");		
			button.setPreferredSize(new Dimension(100, 50));		
			button.addActionListener(miscButtonHandler);
			button.setToolTipText("Click on this button to go back to start menu!");
			button.addKeyListener(hotkey);
			miscButtons.add(button);
		}
		
		
		//Add instructions button
		button = new JButton("Rules");
		button.setPreferredSize(new Dimension(100, 50));
		button.addActionListener(miscButtonHandler);
		button.setToolTipText("Click on this button to look at instructions!");
		button.addKeyListener(hotkey);
		miscButtons.add(button);
		//Duplicate here!!!
		//checkBox = new JCheckBox("Allow duplicates");
		//miscButtons.add(checkBox);
		//miscButtons.setPreferredSize(new Dimension(230, 150));
		
		//creating label to indicate selected input	
		JPanel player1Panel = new JPanel();	
		JLabel playerLabel = new JLabel("Player 1");	
		playerLabel.setForeground(Color.black);
		player1Panel.setLayout(new FlowLayout());
		player1Panel.add(playerLabel);
		//inputTimer = new JLabel("0 sec passed");
		
		//Only instantiate timer once
//		//if (timerSet == false) {
//		//	setupTimer();
//			timerSet = true;
//		} else {
//			resetTimer();
//		}
		
//		timerLabel.setForeground(Color.black);		
//		timerPanel.add(timerLabel, BorderLayout.NORTH);		
//		timerPanel.add(inputTimer, BorderLayout.SOUTH);		
				
		//initializing the final frame
		JPanel finalFrame = new JPanel();
		gameButtonGrid.setOpaque(false);
		inputPanel.setOpaque(false);
		miscButtons.setOpaque(false);
		inputIndicator.setOpaque(false);
		finalFrame.setLayout(new BorderLayout());
		
		//adding panels to the window
		finalFrame.add(gameButtonGrid, BorderLayout.CENTER);	
		finalFrame.add(inputPanel, BorderLayout.SOUTH);
		finalFrame.add(hintPanel, BorderLayout.EAST);
		finalFrame.add(miscButtons, BorderLayout.WEST);
		finalFrame.add(player1Panel, BorderLayout.NORTH);
		finalFrame.setSize(700,700);	
		
		//Creating label to waste space
		//Only created if more than 1 player
		if (player == 2) {
			JPanel player2Panel = new JPanel();	
			JLabel name = new JLabel("Player");	
			name.setForeground(Color.black);
			JLabel label = new JLabel("2");
			label.setForeground(Color.black);		
			player2Panel.add(name, BorderLayout.NORTH);		
			player2Panel.add(label, BorderLayout.SOUTH);
			
			//Create a space waster - no longer a space waster!!
			JPanel turnIndicatorPanel = new JPanel();
			JLabel turnIndicatorLabel = new JLabel(turnIndicator); //TODO Modify this label to show whos turn it is!! 
			turnIndicatorPanel.add(turnIndicatorLabel, BorderLayout.CENTER);
			turnIndicatorPanel.setPreferredSize(new Dimension(200, 230));
			
			
			//Create space waster
			JPanel temp2 = new JPanel();
			JLabel t2 = new JLabel();
			temp2.add(t2, BorderLayout.CENTER);
			temp2.setLayout(new GridLayout(3, 3));
			temp2.setPreferredSize(new Dimension(50, 200));
			
			//initialize player 2 frame
			p2Panel = new JPanel();
			initialisep2Grid();
			p2gameButtonGrid.setOpaque(false);
			p2hintPanel.setOpaque(false);
			player2Panel.setOpaque(false);
			turnIndicatorPanel.setOpaque(false);
			temp2.setOpaque(false);
			p2Panel.setLayout(new BorderLayout());
			
			//Add to frame
			p2Panel.add(p2gameButtonGrid, BorderLayout.CENTER);
			p2Panel.add(p2hintPanel, BorderLayout.EAST);
			p2Panel.add(player2Panel, BorderLayout.NORTH);
			p2Panel.add(turnIndicatorPanel, BorderLayout.SOUTH);
			p2Panel.add(temp2, BorderLayout.WEST);
			p2Panel.setSize(700,700);	
		}
		
		//Title for game
		JPanel titlePanel = new JPanel();
		JLabel titleOfGame = new JLabel();
		titleOfGame.setText("Mastermind - The Game of Colours");
		titleOfGame.setFont(new Font("TimesRoman", Font.BOLD, 28));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleOfGame);
		
		//Final frame
		JFrame mastermindFrame = new JFrame("Mastermind");
		mastermindFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		mastermindFrame.setLayout(new BorderLayout());
		
		if (player == 2) {
			mastermindFrame.add(finalFrame, BorderLayout.WEST);
			mastermindFrame.add(p2Panel, BorderLayout.CENTER);
		} else {
			mastermindFrame.add(finalFrame, BorderLayout.CENTER);
		}
		
		mastermindFrame.add(titlePanel, BorderLayout.NORTH);
		mastermindFrame.pack();
		mastermindFrame.setResizable(true);
		mastermindFrame.setBackground(Color.orange);
		this.gameGrid = mastermindFrame;
		
		//Make the grid for mastermind
		this.gameGrid.setVisible(true);
		
		//if we're going second, we need to get the first move from opponent before we make ours
		if (this.getTurnOrder() != 0 && this.client != null)
		{
			this.setTurnLabel("waiting for opponent to send our answer");
			try {
				ArrayList<Integer> opponentMove = this.getClient().receiveMove();
				System.out.println("got guess from opponent: " + opponentMove);
				//update our opponent's screen (on our end) accordingly
				boolean opponentWon = this.colourScreenMult(opponentMove); //TODO : get the hint 
			} catch (Exception exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		}
	}

	//Initialises the player 2 grid
	private void initialisep2Grid() {

		Integer x = 0;
		Integer y = 0;
		int i = 1;
		
		GridLayout newGrid = new GridLayout(mmg.getMaxGuessAmt(), 4);
		p2gameButtonGrid = new JPanel();
		p2gameButtonGrid.setLayout(newGrid);
		p2gameButtonGrid.setPreferredSize(new Dimension(320,320));

		while (i <= mmg.getMaxGuessAmt()*4) {
			
			button = new JButton();
			//Dummy used to make sure user doesn't double click
			button.setText("clear");
			button.setForeground(Color.gray);
			button.setBackground(Color.gray);
			
			//Give them a label?
			String label = "";
			label = label.concat(x.toString());
			label = label.concat(y.toString());
			button.setName(label);
			
			//Set size
			button.setPreferredSize(new Dimension(30, 30));
			p2gameButtonGrid.add(button);
			i++;
			
			//Lock all buttons for second player
			button.setEnabled(false);
			
			if (x < 3) {
				x++;
			} else {
				x = 0;
				y++;
			}
		}
		
		//Set up hints for player 2
		p2hintPanel = new JPanel();
		p2hintPanel.setLayout(new GridLayout(mmg.getMaxGuessAmt() * 2,2));
		p2hintPanel.setPreferredSize(new Dimension (80,320));
		
		for(int k = 0; k < mmg.getMaxGuessAmt() * 4; k++) {
			int mod = mmg.getMaxGuessAmt() - k/4;
			button = new JButton();
			button.setName("" + mod);
			button.setPreferredSize(new Dimension(10,10)); //was 40, 40
			p2hintPanel.add(button, k);
			button.setText(button.getName());
			button.setEnabled(false);
			button.setToolTipText("Hint for guess " + mod);
		}
	}

	public void setInputToUse (Integer input)
	{
		this.inputToUse = input;
	}
	/**
	 * @return the input currrently selected
	 */
	public Integer getInputToUse()
	{
		return this.inputToUse;
	}
	/**
	 * @param input the required input to display
	 */
	public void setInputIndicator(String input)
	{
		this.inputIndicator.setText(input);
	}

	//Show puzzle
	public void showPuzzle()
	{
		this.gameGrid.setVisible(true);
	}
	/**
	 * hides the current puzzle
	 */
	public void hidePuzzle()
	{
		this.gameGrid.setVisible(false);
	}

	//Checks the current guess
	public boolean currGuess() {
		boolean answer = false;
		
		answer = mmg.guessCheck();
		
		return answer;
	}
	
	//Sets a hint to GUI
	public void setHint(int row, ArrayList<Integer> hints) {
		if(this.hintPanel == null) {
			System.out.println("Null apparently");
		} else {
//			System.out.println(this.hintPanel.getComponentCount());
			int hintval = mmg.getMaxGuessAmt()*4 - 1;
			
			int i = 0;
			
			while (i != 4) {
				button = (JButton) this.hintPanel.getComponent(hintval - row*4 - i);
				//button.setText(hintAnswer);
				if (hints.get(i) == 2) {
					button.setBackground(Color.black);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is correct and in the correct position!");
				} else if (hints.get(i) == 1) {
					button.setBackground(Color.white);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is correct and in the wrong position!");
				} else {
					button.setBackground(Color.gray);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is not correct and not in the correct position!");

				}
				
				button.setText("");
				i++;
			}
			
		}
	}
	
	//Clears a row
	public void clearRow(int row) {
			
		int x = 0;
		int y = 0;
		
		int last = mmg.getMaxGuessAmt() * 4 - 1;
		//Clear the colours
		while (x != 4) {
			y = 4 * row + x;
			button = (JButton) this.gameButtonGrid.getComponent(last - y);
			button.setBackground(Color.gray);
			button.setText("clear");
			x++;
		}
		
		//Clear the guess
		mmg.clearGuess();
		
	}

	//Unlock the next row of buttons
	public void unlockNextRow(int curGuessAmt) {
		int x = 0;
		int y = 0;
		
		//Clear the colours
		int last = mmg.getMaxGuessAmt() * 4 - 1;
		
		while (x != 4) {
			y = curGuessAmt*4 + x;
			button = (JButton) this.gameButtonGrid.getComponent(last - y);
			//button.setBackground(Color.gray);
			button.setEnabled(true);
			//button.setText("clear");
			x++;
		}
		
		x = 0;
		
		while (x != 4) {
			y = curGuessAmt - 1 + x;
			button = (JButton) this.gameButtonGrid.getComponent(last - y);
			//button.setBackground(Color.gray);
			button.setEnabled(false);
			//button.setText("clear");
			x++;
		}
		
		//Clear the guess
		mmg.clearGuess();
	}

	//Not enough guesses
	public void guessError() {
		JOptionPane.showMessageDialog(null, "You don't have enough guesses!", "Error Code 1", JOptionPane.ERROR_MESSAGE);
	}

	//Can't click on the same thing twice
	public void showError() {
		JOptionPane.showMessageDialog(null, "You need to clear first before you can change the colour!", "Error Code 2", JOptionPane.ERROR_MESSAGE);
	}

	//Clicked check when guess was empty
	public void playfulError() {
		JOptionPane.showMessageDialog(null, "You didn't even try to guess :(!", "Error Code 3", JOptionPane.ERROR_MESSAGE);
	}
	
	//Clicked clear when guess was empty
	public void showEmptyError() {
		JOptionPane.showMessageDialog(null, "No point clearing it when it's empty", "Error Code 4", JOptionPane.ERROR_MESSAGE);
	}
	
	//Can't click on the same thing twice
	public void notEnoughError() {
		JOptionPane.showMessageDialog(null, "Not enough colours!", "Error Code 5", JOptionPane.ERROR_MESSAGE);
	}
	
	//Sets ai
	public void setAI(AI ai) {
		this.ai = ai;
	}

	//Sets the AI hint to the hint
	public void setAIHint(ArrayList<Integer> arrayList, int row) {
		if (this.p2hintPanel == null) {
			System.out.println("Null apparently");
		} else {
//			System.out.println(this.hintPanel.getComponentCount());
//			int t = mmg.getMaxGuessAmt() - 1;
//			button = (JButton) this.p2hintPanel.getComponent(t - row);
//			button.setText(aiHint);
			System.out.println(this.p2hintPanel.getComponentCount());
			int hintval = mmg.getMaxGuessAmt()*4 - 1;
			
			System.out.println(arrayList.size());
			
			
			int i = 0;
			
			while (i != 4) {
				button = (JButton) this.p2hintPanel.getComponent(hintval - row*4 - i);
				//button.setText(hintAnswer);
				System.out.println(arrayList.get(i));
				if (arrayList.get(i) == 2) {
					button.setBackground(Color.black);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is correct and in the correct position!");
				} else if (arrayList.get(i) == 1) {
					button.setBackground(Color.white);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is correct and in the wrong position!");
				} else {
					button.setBackground(Color.gray);
					button.setOpaque(true);
					button.setToolTipText("This means one of your colours is not correct and not in the correct position!");

				}
				
				button.setText("");
				i++;
			}
		}
	}

	//Sets the ai move
	public void setAIMove(ArrayList<Integer> aiMove, int curMove) {
		int x = 0;
		int y = 0;
		
		int last = mmg.getMaxGuessAmt() * 4 - 1;
		
		//Clear the colours
		while (x != 4) {
			y = curMove*4 + x;
			button = (JButton) this.p2gameButtonGrid.getComponent(last - y);
			//button.setBackground(Color.gray);
			//button.setEnabled(true);
			//button.setText("clear");
			button.setText("");
			
			int colourSel = aiMove.get(3 - x);
			
			if (colourSel == 0) {
				button.setBackground(Color.red);
				button.setOpaque(true);
					//System.out.println("Set to red");
//					source.setText("RED");
			} else if (colourSel == 1) {
				button.setBackground(Color.green);
				button.setOpaque(true);
//					source.setText("GREEN");
					//System.out.println("Set to green");
			} else if (colourSel == 2) {
				button.setBackground(Color.blue);
				button.setOpaque(true);
//					source.setText("BLUE");
			} else if (colourSel == 3) {
				button.setBackground(Color.yellow);
				button.setOpaque(true);
//					source.setText("YELLOW");
			} else if (colourSel == 4) {
				button.setBackground(Color.white);
				button.setOpaque(true);
//					source.setText("WHITE");
			} else if (colourSel == 5) {
				button.setBackground(Color.black);
				button.setOpaque(true);
//					source.setText("BLACK");
			}
			x++;
		}
	}

	//Change the input of timer
	public void setTimer() {
		secPassed++;
		if (secPassed == 60) {
			minPassed++;
			secPassed = 0;
		}
		inputTimer.setText(minPassed + " min " + secPassed + " sec");
	}

	//Reset the timer
	public void resetTimer() {
		secPassed = 0;
		minPassed = 0;
		inputTimer.setText(minPassed + " min " + secPassed + " sec");
	}
	
	//Sets up timer
	public void setupTimer() {
		//Timer updates every second
		TimerHandler tHandle = new TimerHandler(this);
		t = new Timer(1000, tHandle);
		t.start();
		t.setRepeats(true);
	}

	//Return seconds passed
	public int getSec() {
		return secPassed;
	}

	//Return minutes passed
	public int getMin() {
		return minPassed;
	}

	//Stops timer
	public void disableTime() {
		t.stop();
	}
	
	//Restart the timer
	public void restartTimer() {
		//Check if its running first
		if (t.isRunning()) {
			
		} else {
			t.restart();
		}
	}
	
	//Hides start screen
	public void hideStart() {
		startFrame.setVisible(false);
	}

	//Shows start screen
	public void showStart() {
			startFrame.setVisible(true);
	}
		
	//Show the instruction screen
	public void showInstScreen() {
		//JLabel correctText = new JLabel(new ImageIcon("images/success.png"));
		instFrame = new JFrame();
		
		//JPanel instPanel = new JPanel();
		//instPanel.setLayout
		
		//May need to edit this for your computer!!!
		ImageIcon img = new ImageIcon("images/Inst1P.png");
		JLabel icon = new JLabel(img);
		//JButton next = new JButton("Next");
		//next.setName("Next");
		
		//instFrame.add(txt);
		instFrame.add(icon);
		//instFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		instFrame.setLayout(new FlowLayout());
		instFrame.setSize(700,700);	
		instFrame.setLocation(400, 100);
		instFrame.pack();
		instFrame.setVisible(true);
	}

	//Used for multi player
	public void showSelectionScreen() {
		//JLabel correctText = new JLabel(new ImageIcon("images/success.png"));
		
		//Make solution grid
		gridMult = new JPanel();
		gridMult.setLayout(new GridLayout(1, 4));
		gridMult.setPreferredSize(new Dimension(300,100));
		
		GridHandler gridButtonHandler = new GridHandler(p2mmg, this);
		HotkeyHandler hotkey = new HotkeyHandler(this);
		
		Integer x = 0;
		int i = 0;
		
		while (i != 4) {
			button = new JButton();
			//Dummy used to make sure user doesn't double click
			button.setText("clear");
			button.setForeground(Color.gray);
			button.setBackground(Color.gray);
			
			//Get coordinates
			String label = "";
			label = label.concat(x.toString());
			button.setName(label);
			
			//Set size
			button.setPreferredSize(new Dimension(30, 30));
			button.addActionListener(gridButtonHandler);
			button.addKeyListener(hotkey);
			gridMult.add(button);
			x++;
			i++;
		}
		
		//creating label to indicate selected input	
		JPanel inputLabelMult = new JPanel();	
		
		JLabel title = new JLabel("Selected Input:");	
		title.setForeground(Color.black);
		JLabel input = new JLabel("None");
		input.setForeground(Color.black);
		
		inputLabelMult.setLayout(new FlowLayout());
		inputLabelMult.add(title);
		inputLabelMult.add(input);
		
		this.inputIndicator = input;
		
		InputColourHandler inputHandler = new InputColourHandler(this);
		
		//Create input grid
		JPanel inputGridMult = new JPanel();
		inputGridMult.setLayout(new GridLayout(2, 3));
		//Set up inputs
		button = new JButton();
		button.setName("red");
		button.setText("RED");
		button.setBackground(Color.red);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);			
				
		button = new JButton();
		button.setName("green");
		button.setText("GREEN");
		button.setBackground(Color.green);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);		
				
		button = new JButton();
		button.setName("blue");
		button.setBackground(Color.blue);
		button.setText("BLUE");
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);		
				
		button = new JButton();
		button.setName("yellow");
		button.setText("YELLOW");
		button.setBackground(Color.yellow);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);		
				
		button = new JButton();
		button.setName("white");
		button.setText("WHITE");
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);		
				
		button = new JButton();
		button.setName("pink");
		button.setText("PINK");
		button.setBackground(Color.pink);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.addKeyListener(hotkey);
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGridMult.add(button);	
		
		//all Input
		JPanel allInput = new JPanel();
		allInput.setLayout(new BorderLayout());
		allInput.add(inputGridMult, BorderLayout.SOUTH);
		allInput.add(inputLabelMult, BorderLayout.NORTH);
		
		//Add send solution
		MultHandler m = new MultHandler(this);
		JPanel misc = new JPanel();
		misc.setLayout(new GridLayout(1,3));
		
		JButton send = new JButton("Send Solution");
		send.setName("send");
		send.addActionListener(m);
		send.setPreferredSize(new Dimension(50, 50));
		
		JButton clear = new JButton("Clear");
		clear.setName("clear");
		clear.addActionListener(m);
		clear.setPreferredSize(new Dimension(50, 50));
		
		misc.add(send);
		misc.add(clear);
		misc.setPreferredSize(new Dimension(200, 50));

		//Panel for heading
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout());
		JLabel heading = new JLabel("Mastermind - Choose a set for your opponent!");
		heading.setFont(new Font("TimesRoman", Font.BOLD, 20));
		head.add(heading);
		
		//Add all the stuff onto the frame
		multiplayerFrame = new JFrame();	
		multiplayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		multiplayerFrame.setLayout(new BorderLayout());
		multiplayerFrame.setSize(800,800);	
		
		//multiplayerFrame.add(inputLabelMult, BorderLayout.EAST);	
		multiplayerFrame.add(allInput, BorderLayout.SOUTH);
		multiplayerFrame.add(gridMult, BorderLayout.CENTER);
		multiplayerFrame.add(head, BorderLayout.NORTH);
		multiplayerFrame.add(misc, BorderLayout.WEST);
		
		
		multiplayerFrame.pack();
		multiplayerFrame.setVisible(true);
	}

	//Clears the guess
	public void clearRowMult() {
		int x = 0;
		int y = 0;
		
		int last = 3;
		//Clear the colours
		while (x != 4) {
			y = x;
			button = (JButton) this.gridMult.getComponent(last - y);
			button.setBackground(Color.gray);
			button.setText("clear");
			x++;
		}
		
		//Clear the guess
		mmg.clearGuess();
	}

	//Hides the multiplayer screen
	public void hideMultScreen() {
		multiplayerFrame.setVisible(false);
	}
	
	//Used by multiplayer only requires guess
	//Adds the guess to the opponents game and sets the screen to display it
	public boolean colourScreenMult(ArrayList<Integer> guess) {
		
		boolean answer = false;
		
		int x = 0;
		int y = 0;
		
		int last = mmg.getMaxGuessAmt() * 4 - 1;
		
		//Set the colours
		//
		while (x != 4) {
			y = 4 * multMove + x;
			button = (JButton) this.p2gameButtonGrid.getComponent(last - y);
			int colour = guess.get(3 - x);
			if (colour == mmg.RED) {
				button.setBackground(Color.red);
			} else if (colour == mmg.BLUE) {
				button.setBackground(Color.blue);
			} else if (colour == mmg.GREEN) {
				button.setBackground(Color.green);
			} else if (colour == mmg.YELLOW) {
				button.setBackground(Color.yellow);
			} else if (colour == mmg.PINK) {
				button.setBackground(Color.pink);
			} else if (colour == mmg.WHITE) {
				button.setBackground(Color.white);
			}
			button.setText("");
			x++;
		}
		
		//Try the guess on the opponents board
		int j = 0;
		
		//Add the guess to the game
		while (j != guess.size()) {
			p2mmg.addToEndGuess(guess.get(j));
			j++;
		}
		
		//Make the guess
		answer = p2mmg.guessCheck();
		
		//Grab hints
		ArrayList<Integer> hint = p2mmg.guessRes();
		
		//Set hints (doesn't matter where to grab as both players have same difficulty/ same guess amount
		int hintval = mmg.getMaxGuessAmt()*4 - 1;
		
		int i = 0;
		
		while (i != 4) {
			button = (JButton) this.p2hintPanel.getComponent(hintval - multMove*4 - i);
			//button.setText(hintAnswer);
			System.out.println(hint.get(i));
			if (hint.get(i) == 2) {
				button.setBackground(Color.black);
				button.setOpaque(true);
				//button.setToolTipText("This means one of your colours is correct and in the correct position!");
			} else if (hint.get(i) == 1) {
				button.setBackground(Color.white);
				button.setOpaque(true);
				//button.setToolTipText("This means one of your colours is correct and in the wrong position!");
			} else {
				button.setBackground(Color.gray);
				button.setOpaque(true);
				//button.setToolTipText("This means one of your colours is not correct and not in the correct position!");
			}
			
			button.setText("");
			i++;
		}
		
		//Increment other player's move
		multMove++;
		
		return answer;
	}

	//Sets player 2 with a game
	public void setGameP2(MasterMindGame p2Game) {
		p2mmg = p2Game;
	}
	
	//Gets player 2 game
	public MasterMindGame getGameP2() {
		return p2mmg;
	}
}

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;  //notice javax

public class GUI extends JFrame{

	private JLabel label;
	
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
	
	//Mastermind game
	private MasterMindGame mmg;

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
	private JLabel player2;

	//AI
	private AI ai;

	//Panel for input
	private JPanel inputLabel;
	
	//Constructor for GUI
	public GUI() {
		//mmg = game;
		ai = null;
	}
	
	//Set the game
	public void setGame(MasterMindGame s) {
		mmg = s;
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
			gameButtonGrid.add(button);
			i++;
			
			//Lock buttons above the guess row
			if (i < 30) {
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
		inputLabel.add(title, BorderLayout.NORTH);		
		inputLabel.add(input, BorderLayout.SOUTH);		
		this.inputIndicator = input;
		
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
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);			
		
		button = new JButton();
		button.setName("green");
		button.setText("GREEN");
		button.setBackground(Color.green);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);		
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("blue");
		button.setBackground(Color.blue);
		button.setText("BLUE");
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("yellow");
		button.setText("YELLOW");
		button.setBackground(Color.yellow);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("white");
		button.setText("WHITE");
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);	
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("black");
		button.setText("BLACK");
		button.setBackground(Color.black);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		button.setToolTipText("Click on this button then on the grid to change the colour!");
		inputGrid.add(button);		
		
		//input panel consists of the input grid and the input label		
		JPanel inputPanel = new JPanel();		
		inputPanel.setLayout(new BorderLayout());		
		inputPanel.add(inputLabel, BorderLayout.NORTH);	
		inputPanel.add(inputGrid, BorderLayout.CENTER);
			
		//making miscellaneous buttons		
		JPanel miscButtons	= new JPanel();	
		MiscHandler miscButtonHandler = null;
		
		//Check if versing ai
		if (ai != null) {
			miscButtonHandler = new MiscHandler(mmg, this, ai);	
		} else {
			miscButtonHandler = new MiscHandler(mmg, this, null);	
		}
		
		miscButtons.setLayout(new GridLayout(1,3));
		//miscButtons.setSize(new Dimension(100, 300));
		
		button = new JButton("Check");	
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);	
		button.setToolTipText("Click on this button to check if you are correct!");		
		miscButtons.add(button);		
		
		button = new JButton("Reset");		
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);
		button.setToolTipText("Click on this button to reset the whole puzzle!");
		miscButtons.add(button);
		
		button = new JButton("Clear");		
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);
		button.setToolTipText("Click on this button to clear your current guess!");
		miscButtons.add(button);
		
		//Duplicate here!!!
		//checkBox = new JCheckBox("Allow duplicates");
		//miscButtons.add(checkBox);
		miscButtons.setPreferredSize(new Dimension(260, 200));
		
		//creating label to indicate selected input	
		JPanel timerPanel = new JPanel();	
		JLabel timerLabel = new JLabel("Time is: ");	
		timerLabel.setForeground(Color.black);
		inputTimer = new JLabel("0 sec passed");
		
		//Timer updates every second
		TimerHandler tHandle = new TimerHandler(this);
		Timer t = new Timer(1000, tHandle);
		t.start();
		t.setRepeats(true);
		
		timerLabel.setForeground(Color.black);		
		timerPanel.add(timerLabel, BorderLayout.NORTH);		
		timerPanel.add(inputTimer, BorderLayout.SOUTH);		
				
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
		finalFrame.add(timerPanel, BorderLayout.NORTH);
		finalFrame.setSize(700,700);	
		
		//creating label to waste space
		JPanel player2Panel = new JPanel();	
		JLabel name = new JLabel("Player");	
		name.setForeground(Color.black);
		JLabel label = new JLabel("2");
		label.setForeground(Color.black);		
		player2Panel.add(name, BorderLayout.NORTH);		
		player2Panel.add(label, BorderLayout.SOUTH);		
				
		//Create a space waster
		JPanel temp = new JPanel();
		JLabel t1 = new JLabel();
		temp.add(t1, BorderLayout.CENTER);
		temp.setPreferredSize(new Dimension(200, 230));
		
		JPanel temp2 = new JPanel();
		JLabel t2 = new JLabel();
		temp2.add(t2, BorderLayout.CENTER);
		temp2.setLayout(new GridLayout(3, 3));
		temp2.setPreferredSize(new Dimension(50, 200));
		
		//initialize player 2 frame
		JPanel p2Panel = new JPanel();
		initialisep2Grid();
		p2gameButtonGrid.setOpaque(false);
		p2hintPanel.setOpaque(false);
		player2Panel.setOpaque(false);
		temp.setOpaque(false);
		temp2.setOpaque(false);
		p2Panel.setLayout(new BorderLayout());
		
		//Add to frame
		p2Panel.add(p2gameButtonGrid, BorderLayout.CENTER);
		p2Panel.add(p2hintPanel, BorderLayout.EAST);
		p2Panel.add(player2Panel, BorderLayout.NORTH);
		p2Panel.add(temp, BorderLayout.SOUTH);
		p2Panel.add(temp2, BorderLayout.WEST);
		p2Panel.setSize(700,700);	
		
		JFrame mastermindFrame = new JFrame("Mastermind");
		mastermindFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		mastermindFrame.setLayout(new BorderLayout());
		mastermindFrame.add(finalFrame, BorderLayout.WEST);
		mastermindFrame.add(p2Panel, BorderLayout.CENTER);
		mastermindFrame.pack();
		mastermindFrame.setResizable(true);
		this.gameGrid = mastermindFrame;
		
		//Make the grid for mastermind
		this.gameGrid.setVisible(true);
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
				button = (JButton) this.hintPanel.getComponent(hintval - row - i);
				//button.setText(hintAnswer);
				if (hints.get(i) == 2) {
					button.setBackground(Color.black);
					button.setToolTipText("This means one of your colours is correct and in the correct position!");
				} else if (hints.get(i) == 1) {
					button.setBackground(Color.white);
					button.setToolTipText("This means one of your colours is correct and in the wrong position!");
				} else {
					button.setBackground(Color.gray);
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
				button = (JButton) this.p2hintPanel.getComponent(hintval - row - i);
				//button.setText(hintAnswer);
				System.out.println(arrayList.get(i));
				if (arrayList.get(i) == 2) {
					button.setBackground(Color.black);
					button.setToolTipText("This means one of your colours is correct and in the correct position!");
				} else if (arrayList.get(i) == 1) {
					button.setBackground(Color.white);
					button.setToolTipText("This means one of your colours is correct and in the wrong position!");
				} else {
					button.setBackground(Color.gray);
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
			
			int colourSel = aiMove.get(x);
			
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

	

}

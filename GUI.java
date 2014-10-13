import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  //notice javax

public class GUI extends JFrame{

	private JLabel label;
	
	private JButton button;
	
	private JPanel hintPanel;
	
	//private JButton pressme = new JButton("Press Me");
	
	private JFrame gameGrid;
	
	//Holds the buttons
	private JPanel gameButtonGrid;
	
	private MasterMindGame mmg;

	//private JFrame startScreen;

	private Integer inputToUse = -1;
	
	//private int buttonLimit = 28;
	
	private JLabel inputIndicator;
	
	//Constructor for GUI
	public GUI(MasterMindGame game) {
		mmg = game;
	}
	
	//Creates the gui
	public void createGUI() {
		
		//disabled buttons will have red text
		//UIManager.put("Button.disabledText", Color.red);
		//initializing the sudoku grid
		gameButtonGrid = new JPanel();
		gameButtonGrid.setLayout(new GridLayout(8,4));
		gameButtonGrid.setPreferredSize(new Dimension(500,500));
		Integer i = 1;
		Integer x = 0;
		//Integer y = 0;
		
		hintPanel = new JPanel();
		hintPanel.setLayout(new GridLayout(8,1));
		hintPanel.setPreferredSize(new Dimension (80,500));
		for(int k = 0; k < 8; k++) {
			int m = 8-k;
			button = new JButton();
			button.setName("" + m);
			button.setPreferredSize(new Dimension(50,50));
			hintPanel.add(button, k);
			button.setText(button.getName());
			button.setEnabled(false);
		}
		
		GridHandler gridButtonHandler = new GridHandler(mmg, this);
		//adding the input positions as buttons to the grid
		while (i <= 32) {
			
			//using the value from the puzzle as the text displayed on the button
			//Integer value = puzzle.getValueAtPosition(x, y);
			button = new JButton();
			//Dummy used to make sure user doesn't double click
			button.setText("clear");
			button.setForeground(Color.gray);
			button.setBackground(Color.gray);
			
			//button.setOpaque(false);
			//button.setContentAreaFilled(false);
			//button.setBorderPainted(false);
			
			//Give them a label?
			String label = "";
			label = label.concat(x.toString());
			//label = label.concat(y.toString());
			button.setName(label);
			//button.setText(label);
			
			//Set size
			button.setPreferredSize(new Dimension(30, 30));
			button.addActionListener(gridButtonHandler);
			gameButtonGrid.add(button);
			i++;
			
			x++;
//			if (x < 4) {
//				x++;
//			} else {
//				x = 0;
//				y++;
//			}
		}
				
		JPanel inputLabel = new JPanel();
		label = new JLabel("");
				
		//making room for the input label	
		inputLabel.setLayout(new FlowLayout());
		inputLabel.setOpaque(false);
		inputLabel.setForeground(Color.white);		
		inputLabel.add(label);
		JPanel inputGrid = new JPanel();
		
		//making the input grid
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
		inputGrid.add(button);			
		
		button = new JButton();
		button.setName("green");
		button.setText("GREEN");
		button.setBackground(Color.green);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("blue");
		button.setBackground(Color.blue);
		button.setText("BLUE");
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("yellow");
		button.setText("YELLOW");
		button.setBackground(Color.yellow);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("white");
		button.setText("WHITE");
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("black");
		button.setText("BLACK");
		button.setBackground(Color.black);	
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		//input panel consists of the input grid and the input label		
		JPanel inputPanel = new JPanel();		
		inputPanel.setLayout(new BorderLayout());		
		inputPanel.add(inputLabel, BorderLayout.NORTH);	
		inputPanel.add(inputGrid, BorderLayout.CENTER);
			
		//making miscellaneous buttons		
		JPanel miscButtons	= new JPanel();		
		MiscHandler miscButtonHandler = new MiscHandler(mmg, this);		
		miscButtons.setLayout(new GridBagLayout());		
		button = new JButton("Check");	
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);		
		//button.setBackground(Color.white);		
		//button.setOpaque(false);		
		//button.setContentAreaFilled(false);		
		//button.setBorderPainted(false);		
		miscButtons.add(button);				
		button = new JButton("Reset");		
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);
		//button.setBackground(Color.white);		
		//button.setOpaque(false);		
		//button.setContentAreaFilled(false);		
		//button.setBorderPainted(false);	
		miscButtons.add(button);
		miscButtons.add(button);				
		button = new JButton("Clear");		
		button.setPreferredSize(new Dimension(100, 70));		
		button.addActionListener(miscButtonHandler);
		//button.setBackground(Color.white);		
		//button.setOpaque(false);		
		//button.setContentAreaFilled(false);		
		//button.setBorderPainted(false);	
		miscButtons.add(button);
		
		//creating label to indicate selected input	
		JPanel inputIndicator = new JPanel();	
		JLabel title = new JLabel("Selected Input:");	
		title.setForeground(Color.black);
		JLabel input = new JLabel("None");
		input.setForeground(Color.black);		
		inputIndicator.add(title, BorderLayout.NORTH);		
		inputIndicator.add(input, BorderLayout.SOUTH);		
		this.inputIndicator = input;
		
		
				
		//initializing the final frame with an image
		//ImageIcon bg = new ImageIcon("sudoku/images/puzzlebackground.png");
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
		finalFrame.add(inputIndicator, BorderLayout.NORTH);
		finalFrame.setSize(700,700);	
		
		JFrame sudokuFrame = new JFrame("Mastermind");		//creating the Mastermind window
		sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//exit when the 'x' button is clicked
		sudokuFrame.add(finalFrame);
		sudokuFrame.pack();
		sudokuFrame.setResizable(false);
		this.gameGrid = sudokuFrame;
		
		//Make the grid for mastermind
		this.gameGrid.setVisible(true);
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
	public void setHint(int row, String hintAnswer) {
		if(this.hintPanel == null) {
			System.out.println("Null apparently");
		} else {
//			System.out.println(this.hintPanel.getComponentCount());
			
			button = (JButton) this.hintPanel.getComponent(7 - row);
			button.setText(hintAnswer);
		}
	}
	
	//Clears a row
	public void clearRow(int row) {
			
		int x = 0;
		int y = 0;
		
		//Clear the colours
		while (x != 4) {
			y = row + x;
			button = (JButton) this.gameButtonGrid.getComponent(31 - y);
			button.setBackground(Color.gray);
			x++;
		}
		
		//Clear the guess
		mmg.clearGuess();
		
	}
}

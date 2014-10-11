import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  //notice javax

public class GUI extends JFrame{

	private JLabel label;
	
	private JButton button;
	
	//private JPanel pane;
	
	//private JButton pressme = new JButton("Press Me");
	
	private JFrame gameGrid;
	
	private MasterMindGame mmg;

	//private JFrame startScreen;

	private Integer inputToUse = -1;
	
	private int buttonLimit = 28;
	
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
		JPanel gameGrid = new JPanel();
		gameGrid.setLayout(new GridLayout(8,4));
		gameGrid.setPreferredSize(new Dimension(500,500));
		Integer i = 1;
		Integer x = 0;
		Integer y = 0;
		
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
			label = label.concat(y.toString());
			button.setName(label);
			
			//Set size
			button.setPreferredSize(new Dimension(30, 30));
			button.addActionListener(gridButtonHandler);
			gameGrid.add(button);
			i++;
			
			if (x < 4) {
				x++;
			} else {
				x = 0;
				y++;
			}
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
		button.setBackground(Color.red);		
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);			
		
		button = new JButton();
		button.setName("green");
		button.setBackground(Color.green);		
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("blue");
		button.setBackground(Color.blue);		
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("yellow");
		button.setBackground(Color.yellow);		
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("white");
		button.setBackground(Color.white);		
		button.setPreferredSize(new Dimension(40, 40));			
		button.addActionListener(inputHandler);			
		inputGrid.add(button);		
		
		button = new JButton();
		button.setName("black");
		button.setBackground(Color.black);		
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
		button.setPreferredSize(new Dimension(120, 70));		
		button.addActionListener(miscButtonHandler);		
		button.setBackground(Color.white);		
		button.setOpaque(false);		
		button.setContentAreaFilled(false);		
		button.setBorderPainted(false);		
		miscButtons.add(button);				
		button = new JButton("Reset");		
		button.setPreferredSize(new Dimension(120, 70));		
		button.addActionListener(miscButtonHandler);
		button.setBackground(Color.white);		
		button.setOpaque(false);		
		button.setContentAreaFilled(false);		
		button.setBorderPainted(false);	
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
		gameGrid.setOpaque(false);
		inputPanel.setOpaque(false);
		miscButtons.setOpaque(false);
		inputIndicator.setOpaque(false);
		finalFrame.setLayout(new BorderLayout());
		
		//adding panels to the window
		finalFrame.add(gameGrid, BorderLayout.NORTH);	
		finalFrame.add(inputPanel, BorderLayout.EAST);
		finalFrame.add(miscButtons, BorderLayout.WEST);
		finalFrame.add(inputIndicator, BorderLayout.CENTER);
		finalFrame.setSize(700,700);	
		
		JFrame sudokuFrame = new JFrame("Mastermind - In Game");		//creating the sudoku window
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
}

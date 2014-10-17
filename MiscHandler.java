import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MiscHandler implements ActionListener{
	/**
	 * puzzle which the gui is displaying
	 */
	private MasterMindGame puzzle;
	/**
	 * puzzle containing this listener
	 */
	private GUI gui;

	//Add AI
	private AI ai;
	
	//Variable to stop the user from clearing after game is won
	private boolean noClear = false;
	
	public MiscHandler(MasterMindGame puzzle, GUI gui, AI ai)
	{
		this.puzzle = puzzle;
		this.gui = gui;
		this.ai = ai;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) 
	{
		JFrame miscFrame = new JFrame();
		
		if (event.getActionCommand() == "Check")
		{
			//If they don't have enough guesses
			if (puzzle.getFullGuess().size() != 4) {
				if (puzzle.getFullGuess().size() == 0) {
					gui.playfulError();
				} else {
					//gui.showError();
					gui.guessError();
				}
			} else {
				//calling checkPuzzle method to determine whether puzzle is solved
				boolean correct = gui.currGuess();
				//displaying appropriate message
				if (correct == true) {
					//JLabel correctText = new JLabel(new ImageIcon("images/success.png"));
					JPanel text = new JPanel();
					JLabel correctText = new JLabel("Congratulations! You solved the puzzle!");
					JLabel time = new JLabel("Time taken is " + this.gui.getMin() + " min " + this.gui.getSec() + " sec!");
					text.add(correctText, BorderLayout.NORTH);
					text.add(time, BorderLayout.SOUTH);
					miscFrame.add(text, BorderLayout.CENTER);
					miscFrame.setTitle("Success");
					miscFrame.pack();
					miscFrame.setVisible(true);
					miscFrame.setSize(new Dimension(300, 300));
					//To hintPanel button
					gui.setHint(puzzle.getCurGuessAmt() - 1, puzzle.guessRes());
					//gui.setClearButton();
					noClear = true;
					
				} else {
					//Supply hints
					System.out.println("Wrong");
					gui.setHint(puzzle.getCurGuessAmt() - 1, puzzle.guessRes());
					gui.unlockNextRow(puzzle.getCurGuessAmt());
					
					
				}
				
				if (ai != null) {
					gui.setAIHint(ai.getAIHint(), ai.curHint());
					gui.setAIMove(ai.getAIMove(), ai.curMove());
				}
			}
		}
		
		//Resets the board
		if (event.getActionCommand() == "Reset")
		{
			this.gui.hidePuzzle();
			
			//refresh the gui
			this.gui.createGUI();
			
			//Reset the game will generate new game with random values
			puzzle.resetGame();
			
			this.gui.setGame(puzzle);
			
			//If ai was being played
			if (ai != null) {
				ai.resetGame();
				//ai.playGame();
			}
			
			this.gui.showPuzzle();
		}
		
		//Clears the row
		if (event.getActionCommand() == "Clear")
		{
			//this.gui.hidePuzzle();
			if (puzzle.getFullGuess().size() == 0) {
				gui.showEmptyError();
			} else {
				if (noClear) {
					JButton source = (JButton) event.getSource();
					source.setEnabled(false);
				} else {
					int row = puzzle.getCurGuessAmt();
					
					gui.clearRow(row);
				}
			}
		}
	}

}

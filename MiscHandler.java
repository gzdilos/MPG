import java.awt.BorderLayout;
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
				gui.showError();
			} else {
				//calling checkPuzzle method to determine whether puzzle is solved
				boolean correct = gui.currGuess();
				//displaying appropriate message
				if (correct == true) {
					JLabel correctText = new JLabel(new ImageIcon("images/success.png"));
					miscFrame.add(correctText, BorderLayout.CENTER);
					miscFrame.setTitle("Success");
					miscFrame.pack();
					miscFrame.setVisible(true);
					//To hintPanel button
					gui.setHint(puzzle.getCurGuessAmt() - 1, "BBBB");
					//gui.setClearButton();
					noClear = true;
					
				} else {
					//Supply hints
					System.out.println("Wrong");
					gui.setHint(puzzle.getCurGuessAmt() - 1, puzzle.convertHintToString());
					gui.unlockNextRow(puzzle.getCurGuessAmt());
					
					
				}
				
				if (ai != null) {
					gui.setAIHint(puzzle.convertAHintToString(ai.getAIHint()), ai.curHint());
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
			
			this.gui.showPuzzle();
		}
		
		//Clears the row
		if (event.getActionCommand() == "Clear")
		{
			//this.gui.hidePuzzle();
			if (noClear) {
				JButton source = (JButton) event.getSource();
				source.setEnabled(false);
			} else {
				int row = puzzle.getCurGuessAmt();
				
				gui.clearRow(row);
			}
			//refresh the gui
			//this.gui.createGUI();
					
			//Reset the game will generate new game with random values
			//puzzle.resetGame();
					
			//this.gui.showPuzzle();
		}
	}

}

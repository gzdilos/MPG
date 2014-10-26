import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
		JButton source = (JButton) event.getSource();
		
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
				if (this.gui.getClient() != null)
				{
					try {
						this.gui.getClient().makeMove(puzzle.getFullGuess());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//calling checkPuzzle method to determine whether puzzle is solved
				boolean correct = gui.currGuess();
				System.out.println("After guessing the cur (misc) is " + puzzle.getCurGuessAmt());
				
				//displaying appropriate message
				if (correct == true) {
					//JLabel correctText = new JLabel(new ImageIcon("images/success.png"));
					if (!noClear) {
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
						
						//Disable button and stop counter
						source.setEnabled(false);
						this.gui.disableTime();
					} 
					
				} else 	{
					//Max guesses used
					if (puzzle.getCurGuessAmt() >= 8) {
						JPanel text = new JPanel();
						JLabel correctText = new JLabel("You did not manage to solve the puzzle!");
						JLabel time = new JLabel("Time taken is " + this.gui.getMin() + " min " + this.gui.getSec() + " sec!");
						text.add(correctText, BorderLayout.NORTH);
						text.add(time, BorderLayout.SOUTH);
						miscFrame.add(text, BorderLayout.CENTER);
						miscFrame.setTitle("Success");
						miscFrame.pack();
						miscFrame.setVisible(true);
						miscFrame.setSize(new Dimension(300, 300));
						
						noClear = true;
						
						//Disable button and stop counter
						source.setEnabled(false);
						this.gui.disableTime();
					} else {
						
						System.out.println("Wrong hint then After guessing the cur is " + puzzle.getCurGuessAmt());
						//gui.setHint(puzzle.getCurGuessAmt() - 1, puzzle.guessRes());
						
						if (puzzle.getCurGuessAmt() != 8) {
							gui.unlockNextRow(puzzle.getCurGuessAmt());
						}
						
					}
					
					//Supply hints
					System.out.println("Wrong");
					gui.setHint(puzzle.getCurGuessAmt() - 1, puzzle.guessRes());
				}
				
				if (ai != null) {
					int x = ai.curMove() + 1;
					if (x < ai.winMove()) {
						gui.setAIHint(ai.getAIHint(), ai.curHint());
						gui.setAIMove(ai.getAIMove(), ai.curMove());
					}
				}
				//check if we are doing a multiplayer game
				if (this.gui.getClient() != null)
				{
					try {
						//now it is the opponents turn to guess
						this.gui.setTurnLabel("Waiting for opponent to make a guess...");
						//receive move from opponent
						ArrayList<Integer> opponentMove = this.gui.getClient().receiveMove();
						System.out.println("got guess from opponent: " + opponentMove);
						
						//check if it is the disconnect message
						if (opponentMove.contains(-1))
						{
							this.gui.getClient().closeConnection();
							this.gui.removeClient();
							this.gui.setTurnLabel("Opponent disconnected... please press 'Exit'...");
						}
						
						//update our opponent's screen (on our end) accordingly
						boolean opponentWon = this.gui.colourScreenMult(opponentMove);
						//now it is our turn
						this.gui.setTurnLabel("Your turn to guess!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		//Resets the board
		if (event.getActionCommand() == "Reset")
		{
			this.gui.hidePuzzle();
			
			//refresh the gui
			this.gui.createGUI();
			this.gui.restartTimer();
			
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
		
		//Exits the board
		if (event.getActionCommand() == "Exit")
		{
			//Send disconnect message to opponent
			if (this.gui.getClient() != null) {
				ArrayList<Integer> disconnectMessage = new ArrayList<Integer>();
				disconnectMessage.add(-1);
				disconnectMessage.add(-1);
				disconnectMessage.add(-1);
				disconnectMessage.add(-1);
				try {
					this.gui.getClient().makeMove(disconnectMessage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Close connection //TODO
				this.gui.getClient().closeConnection();
				
				this.gui.removeClient();
				
			}
			
			//Hide the puzzle
			this.gui.hidePuzzle();
			
			//Show start screen
			this.gui.showStart();
		}
		
		//Clears the row
		if (event.getActionCommand() == "Clear")
		{
			//this.gui.hidePuzzle();
			if (noClear) {
				//JButton source = (JButton) event.getSource();
				source.setEnabled(false);
			} else {
				if (puzzle.getFullGuess().size() == 0) {
					gui.showEmptyError();
				} else {
					int row = puzzle.getCurGuessAmt();
					gui.clearRow(row);
				}
			}
		}
		
		//Show instructions
		if (event.getActionCommand() == "Rules") {
			//Instructions
			System.out.println("Instructions pressed");
			this.gui.showInstScreen();
			//this.gui.hideStart();
		}
	}

}

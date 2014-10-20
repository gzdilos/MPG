import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;

public class StartScreenHandler implements ActionListener{
	
	private GUI gui;
	
	public final int RED = 0;
	public final int GREEN = 1;
	public final int BLUE = 2;
	public final int YELLOW = 3;
	public final int WHITE = 4;
	public final int BLACK = 5;
	
	private final int EASY_GUESSES = 10;
	private final int MEDIUM_GUESSES = 8;
	private final int HARD_GUESSES = 6;
	
	private boolean useDuplicate;
	
	ArrayList<Integer> sampleTest = new ArrayList<Integer>();
	
	public StartScreenHandler(GUI gui) {
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		JRadioButton toggleEvent = new JRadioButton();
		//boolean useDuplicate = false;
		
		//Sample solution
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		Object source = event.getSource();
		
		if (source.getClass().isInstance(toggleEvent)) {
			toggleEvent = (JRadioButton) event.getSource();
			useDuplicate = toggleEvent.isSelected();
			System.out.println("Duplicates Selected:" + useDuplicate);
		} else {
			//New single player easy game
			if (event.getActionCommand() == "newSE") {
				MasterMindGame s = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				//System.out.println("Duplicates Selected:" + useDuplicate);
				this.gui.setGame(s);
				this.gui.setNumPlayer(1);
				//gui.createGUI();
			}
			
			//New single player medium game
			if (event.getActionCommand() == "newSM") {
				MasterMindGame s = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				this.gui.setGame(s);
				this.gui.setNumPlayer(1);
				//gui.createGUI();
			}
			
			//New single player hard game
			if (event.getActionCommand() == "newSH") {
				MasterMindGame s = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				this.gui.setGame(s);
				this.gui.setNumPlayer(1);
				//gui.createGUI();
			}
			
			//New game vs AI easy game
			if (event.getActionCommand() == "newAIE") {
				MasterMindGame s = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				this.gui.setGame(s);
				this.gui.setNumPlayer(2);
				AI ai = new AI(s2,0);
				ai.playGame();
				this.gui.setAI(ai);
				this.gui.createGUI();
			}
			
			//New game vs AI medium game
			if (event.getActionCommand() == "newAIM") {
				MasterMindGame s = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				this.gui.setGame(s);
				this.gui.setNumPlayer(2);
				AI ai = new AI(s2,1);
				ai.playGame();
				this.gui.setAI(ai);
				//gui.createGUI();
			}
			
			//New game vs AI hard game
			if (event.getActionCommand() == "newAIH") {
				MasterMindGame s = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				gui.setGame(s);
				gui.setNumPlayer(2);
				AI ai = new AI(s2,2);
				ai.playGame();
				gui.setAI(ai);
				//gui.createGUI();
			}
			
			//Multi player game
			if (event.getActionCommand() == "newMulti") {
				this.gui.setNumPlayer(2);
			}
			
			//Hide start screen
			this.gui.hideStart(); 
			
			//Create GUI
			this.gui.createGUI();
		}
	}	
}
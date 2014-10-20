import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class StartScreenHandler implements ActionListener{
	
	private MasterMindGame puzzle;
	private GUI gui;
	
	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int BLUE = 2;
	public final static int YELLOW = 3;
	public final static int WHITE = 4;
	public final static int BLACK = 5;
	
	private final int EASY_GUESSES = 10;
	private final int MEDIUM_GUESSES = 8;
	private final int HARD_GUESSES = 6;
	ArrayList<Integer> sampleTest = new ArrayList<Integer>();
	
	public StartScreenHandler(MasterMindGame puzzle, GUI gui) {
		this.puzzle = puzzle;
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		JRadioButton toggleEvent = new JRadioButton();
		boolean useDuplicate = false;
		
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		Object source = event.getSource();
		
		if(source.getClass().isInstance(toggleEvent)) {
			toggleEvent = (JRadioButton) event.getSource();
			useDuplicate = toggleEvent.isSelected();
			System.out.println("Duplicates Selected:" + useDuplicate);
		}else {
			if (event.getActionCommand() == "newSE") {
				MasterMindGame s = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				gui.setGame(s);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newSM") {
				MasterMindGame s = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				gui.setGame(s);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newSH") {
				MasterMindGame s = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				gui.setGame(s);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newAIE") {
				MasterMindGame s = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, EASY_GUESSES, useDuplicate);
				gui.setGame(s);
				AI ai = new AI(s2,0);
				ai.playGame();
				gui.setAI(ai);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newAIM") {
				MasterMindGame s = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, MEDIUM_GUESSES, useDuplicate);
				gui.setGame(s);
				AI ai = new AI(s2,0);
				ai.playGame();
				gui.setAI(ai);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newAIH") {
				MasterMindGame s = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				MasterMindGame s2 = new MasterMindGame(sampleTest, HARD_GUESSES, useDuplicate);
				gui.setGame(s);
				AI ai = new AI(s2,0);
				ai.playGame();
				gui.setAI(ai);
				gui.createGUI();
			}
			if (event.getActionCommand() == "newMulti") {
				gui.createGUI();
			}
			
		}
	}	
}
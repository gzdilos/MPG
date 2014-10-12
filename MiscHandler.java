import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

	public MiscHandler(MasterMindGame puzzle, GUI gui)
	{
		this.puzzle = puzzle;
		this.gui = gui;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) 
	{
		JFrame miscFrame = new JFrame();
		
		if (event.getActionCommand() == "Check")
		{
			//calling checkPuzzle method to determine whether puzzle is solved
			boolean correct = gui.currGuess();
			//displaying appropriate message
			if (correct == true)
			{
				JLabel correctText = new JLabel(new ImageIcon("src/images/success.png"));
				miscFrame.add(correctText, BorderLayout.CENTER);
				miscFrame.setTitle("Success");
				
				//To hintPanel button
				gui.setHint(0, "BBBB");
				
			}else
			{
				//Supply hints
				gui.setHint(0, "XXXX");
				
			}
			miscFrame.pack();
			miscFrame.setVisible(true);
		}
		
		//Not implemented yet
		if (event.getActionCommand() == "Reset")
		{
			this.gui.hidePuzzle();
			int x = 0;
			int y = 0;
			
			//Clear the board
			/*
			while (y < 9)
			{
				while (x < 9)
				{
					//setting value at position back to 0 if it is a changeable position
					if (this.puzzle.canBeChanged(x, y) == true)
					{
						this.puzzle.clearPosition(x, y);
					}
					x++;
				}
				if (x >= 9)
				{
					y++;
					x = 0;
				}
			}
			*/
			
			this.gui.createGUI();
			//refresh the gui
			this.gui.showPuzzle();
		}
	}

}

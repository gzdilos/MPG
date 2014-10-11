
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class GridHandler implements ActionListener{
	/**
	 * the puzzle being displayed on the gui containing this listener
	 */
	private MasterMindGame mmg;
	/**
	 * the gui displaying this listener
	 */
	private GUI gui;
	
	/**
	 * @param puzzle
	 * @param gui
	 */
	public GridHandler (MasterMindGame puzzle, GUI gui)
	{
		mmg = puzzle;
		this.gui = gui;
	}
		
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		
		
		//obtaining the x and y coordinates of the button from the label
		JButton source = (JButton) event.getSource();

		Integer colourSel = this.gui.getInputToUse();
		
		if (source.getText().contentEquals("clear")) {
			if (colourSel == 0) {
				source.setBackground(Color.red);
			} else if (colourSel == 1) {
				source.setBackground(Color.green);
			} else if (colourSel == 2) {
				source.setBackground(Color.blue);
			} else if (colourSel == 3) {
				source.setBackground(Color.yellow);
			} else if (colourSel == 4) {
				source.setBackground(Color.white);
			} else if (colourSel == 5) {
				source.setBackground(Color.black);
			}
			
			if (colourSel < 0) {
				//Invalid colour
			} else {
				mmg.addToGuess(colourSel);
				source.setText("");
			}
		} else {
			
		}
		
		//String newValue = this.gui.getInputToUse().toString();
		//source.setText(newValue);
		
		//coordinates are in the label of the button
		//String label = source.getName();
		//char[] coordinates = label.toCharArray();
		//int xCoordinate = coordinates[0] - 48;
		//int yCoordinate = coordinates[1] - 48;
		//System.out.println("setting (" + xCoordinate + ", " + yCoordinate + ") to " + this.gui.getInputToUse());
		//changing required value
		//this.puzzle.changeValueAtPosition(xCoordinate, yCoordinate, this.gui.getInputToUse());
	}

}

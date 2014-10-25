
import java.awt.Color;
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
		
	//Event for when a grid button is pressed
	public void actionPerformed(ActionEvent event) {
		
		JButton source = (JButton) event.getSource();
		String s = source.getName();
		
		//Get coordinates of label
		Integer colourSel = this.gui.getInputToUse();
		char[] coordinates = s.toCharArray();
		int xCoordinate = coordinates[0] - 48;
		
		if (source.getText().contentEquals("clear")) {
			if (colourSel == mmg.RED) {
				source.setBackground(Color.red);
				source.setOpaque(true);
				//System.out.println("Set to red");
//				source.setText("RED");
			} else if (colourSel == mmg.GREEN) {
				source.setBackground(Color.green);
				source.setOpaque(true);
//				source.setText("GREEN");
				//System.out.println("Set to green");
			} else if (colourSel == mmg.BLUE) {
				source.setBackground(Color.blue);
				source.setOpaque(true);
//				source.setText("BLUE");
			} else if (colourSel == mmg.YELLOW) {
				source.setBackground(Color.yellow);
				source.setOpaque(true);
//				source.setText("YELLOW");
			} else if (colourSel == mmg.WHITE) {
				source.setBackground(Color.white);
				source.setOpaque(true);
//				source.setText("WHITE");
			} else if (colourSel == mmg.PINK) {
				source.setBackground(Color.pink);
				source.setOpaque(true);
//				source.setText("BLACK");
			}
			
			if (colourSel < 0) {
				//Invalid colour
			} else {
//				System.out.println("x is " + xCoordinate);
				mmg.addGuessPos(colourSel, xCoordinate);
				source.setText("");
			}
		} else {
			gui.showError();
		}
		
	}

}

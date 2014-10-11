import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class InputColourHandler implements ActionListener {
	
	/**
	 * the gui containing this listener
	 */
	private GUI gui;
	public InputColourHandler (GUI gui)
	{
		this.gui = gui;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		//input required stored as a char
		JButton source = (JButton) event.getSource();
		String s = source.getName();
		
		if (s.contains("red")) {
			//source.setBackground(Color.red);
			this.gui.setInputIndicator("red");
			this.gui.setInputToUse(0);
		} else if (s.contains("green")) {
			//source.setBackground(Color.green);
			this.gui.setInputIndicator("green");
			this.gui.setInputToUse(1);
		} else if (s.contains("blue")) {
			//source.setBackground(Color.blue);
			this.gui.setInputIndicator("blue");
			this.gui.setInputToUse(2);
		} else if (s.contains("yellow")) {
			//source.setBackground(Color.yellow);
			this.gui.setInputIndicator("yellow");
			this.gui.setInputToUse(3);
		} else if (s.contains("white")) {
			//source.setBackground(Color.white);
			this.gui.setInputIndicator("white");
			this.gui.setInputToUse(4);
		} else if (s.contains("black")) {
			//source.setBackground(Color.black);
			this.gui.setInputToUse(5);
			this.gui.setInputIndicator("black");
		}
		//subtract 48 to get the int value
		//Integer input = inputAsChar[0] - 48;
		
		//update the input value and the input indicator
		//this.gui.setInputToUse(input);
		//this.gui.setInputIndicator(input.toString());
		//System.out.println("Input selected is " + input);
	}

}


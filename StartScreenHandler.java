import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class StartScreenHandler implements ActionListener  {
	
	private GUI gui;
	
	public StartScreenHandler (GUI gui)
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
		
		if (s.contains("Easy")) {
			this.gui.hideStart();
			this.gui.createGUI();
		}
	}
}

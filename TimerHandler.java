import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandler implements ActionListener{

	private GUI gui;

	public TimerHandler(GUI gui2) {
		this.gui = gui2;
	}

	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("Action :OOO");
		gui.setTimer();
	}
	
}

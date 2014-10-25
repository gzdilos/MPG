import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


public class MultHandler implements ActionListener{
	
	private GUI gui;
	
	public MultHandler(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton)e.getSource();
		String s = sourceButton.getName();
		MasterMindGame m = gui.getGame();
		
		if (s.contains("send")) {
			
			//Assuming user sets enough colours sol will be the solution to send
			ArrayList<Integer> sol = m.getFullGuess();

			if (sol.size() != 4) {
				this.gui.notEnoughError();
			} else {
				try {
					//Send opponents answer to them
					this.gui.getClient().sendInitial(sol);
					
					// if we went first, we need to receive our answer now
					if (this.gui.getTurnOrder() == 0)
					{
						MasterMindGame game = new MasterMindGame(Client.receiveInitial(), 8, true);
						this.gui.setGame(game);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//We make a new game for second screen
				MasterMindGame p2Game = new MasterMindGame(sol, 8, true);
				this.gui.setGameP2(p2Game);
				
				//Hide start screen
				this.gui.hideStart(); 
				this.gui.hideMultScreen();
				
				//Create GUI
				this.gui.createGUI();
			}
		} else if (s.contains("clear")) {
			if (m.getFullGuess().size() == 0) {
				gui.showEmptyError();
			} else {
				gui.clearRowMult();
			}
		}
	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;


public class MultHandler implements ActionListener{
	
	private GUI gui;
	
	public MultHandler(GUI gui) {
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton)e.getSource();
		String s = sourceButton.getName();
		
		//Use the dummy game to make a guess
		MasterMindGame m = gui.getGameP2();
		
		if (s.contains("send")) {
			
			//Assuming user sets enough colours sol will be the solution to send
			ArrayList<Integer> sol = m.getFullGuess();
			
			//System.out.println("size of sol is " + sol.size());
			
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
				
				//if we're going second, we need to get the first move from opponent before we make ours
				if (this.gui.getTurnOrder() != 0)
				{
					this.gui.setTurnLabel("waiting for opponent to send our answer");
					try {
						ArrayList<Integer> opponentMove = this.gui.getClient().receiveMove();
						System.out.println("got guess from opponent: " + opponentMove);
						//update our opponent's screen (on our end) accordingly
						boolean opponentWon = this.gui.colourScreenMult(opponentMove); //TODO : get the hint 
					} catch (Exception exc) {
						// TODO Auto-generated catch block
						exc.printStackTrace();
					}
				}
				
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

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
				
				//We make a new game for second screen
				MasterMindGame p2Game = new MasterMindGame(sol, 8, true);
				this.gui.setGameP2(p2Game);
				
				//set up networking
//				try {
//					this.gui.createClient();
//					this.gui.getClient();
//					int turn = Client.connect();
//					ArrayList<Integer> ourAnswer = new ArrayList<Integer>();
//					ArrayList<Integer> initial = new ArrayList<Integer>();
//					initial.add(1);
//					initial.add(1);
//					initial.add(1);
//					initial.add(1);
//					if (turn == 0)
//					{
//						//need to ask user to input combination for opponent to guess
//						//for now, I will just send a hard coded combination
//						Client.sendInitial(initial);
//						//now we wait for opponent to send our move
//						ourAnswer = Client.receiveInitial();
//					}else
//					{
//						//if we are going second, reverse the order of sending/receiving
//						ourAnswer = Client.receiveInitial();
//						Client.sendInitial(initial);
//					}
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				//Hide start screen
				this.gui.hideStart(); 
				this.gui.hideMultScreen();
				
				//Solution you received goes here
				ArrayList<Integer> receivedSol = null;
				
				//Create a new mastermind game
				MasterMindGame game = new MasterMindGame(receivedSol, 8, true);
				this.gui.setGame(game);
				
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

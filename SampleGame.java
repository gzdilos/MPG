import java.util.ArrayList;
import java.util.Random;

public class SampleGame {

	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int BLUE = 2;
	public final static int YELLOW = 3;
	public final static int WHITE = 4;
	public final static int BLACK = 5;
	public final static int guessNum = 8;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<Integer> sampleTest = new ArrayList<Integer>();
		
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		//Make a game
		MasterMindGame s = new MasterMindGame(sampleTest, guessNum);
		
		//Make  gui
		GUI gui = new GUI(s);
		
		//Gui gui = new Gui();
		//gui.createStartScreen();
		//gui.showStartScreen();
		
		//Create the gui
		gui.createGUI();
		
		//System.out.println("Solution is");
		//printArray(sampleTest);
		
		//AI ai = new AI(s, 0);
		
		//ai.playGame();
		//Call AI to run game
		//aiPlayGame(s);
	}

	//Prints the array
	private static void printArray(ArrayList<Integer> guess) {
		int i = 0;
		
		while (i != guess.size()) {
			int colour = guess.get(i);
			
			if (colour == RED) {
				System.out.print("red ");
			} else if (colour == BLUE) {
				System.out.print("blue ");
			} else if (colour == GREEN) {
				System.out.print("green ");
			} else if (colour == YELLOW) {
				System.out.print("yellow ");
			} else if (colour == BLACK) {
				System.out.print("black ");
			} else if (colour == WHITE) {
				System.out.print("white ");
			}
			i++;
		}
		System.out.println();
	}

	
}

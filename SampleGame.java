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

		boolean firstTest = runTests();
		
		//firstTest = false;
		
		if (firstTest == true) {
			//Make a random solution
			ArrayList<Integer> sampleTest = new ArrayList<Integer>();
			
			/*
			Random randomGenerator = new Random();
			
			int i = 0;
			
		    while (i != 4) {
		    	int randomInt = randomGenerator.nextInt(6);
		      
		    	//Tries to choose a different colour each time
		    	if (containsColour(randomInt, sampleTest)) {
		    		i--;
		    	} else {
		    		sampleTest.add(randomInt);
		    	}
		      
		    	i++;
		    }*/
		    
			sampleTest.add(BLACK);
			sampleTest.add(RED);
			sampleTest.add(GREEN);
			sampleTest.add(BLUE);
			
			//Make  gui
			GUI gui = new GUI();
			
			MasterMindGame s = new MasterMindGame(sampleTest, guessNum);
			MasterMindGame s2 = new MasterMindGame(sampleTest, guessNum);
			
			gui.setGame(s);
			
			//Set AI as easy
			AI ai = new AI(s2, 0);
			ai.playGame();
			
			gui.setAI(ai);
			//Gui gui = new Gui();
			//gui.createStartScreen();
			//gui.showStartScreen();
			
			//Create the gui
			gui.createGUI();
			
			
			//System.out.println("Solution is");
			printArray(sampleTest);
		
		}
	}

	//Test the system
	private static boolean runTests() {
		boolean answer = true;
		
		ArrayList<Integer> sampleTest = new ArrayList<Integer>();
	    
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		MasterMindGame newGame = new MasterMindGame(sampleTest, 8);
		
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(BLACK);
		newGame.addToEndGuess(BLUE);
		newGame.addToEndGuess(GREEN);
		
		newGame.guessCheck();
		
		ArrayList<Integer> hints = newGame.guessRes();
		
		int i = 0;
		
		while (i != hints.size()) {
			if (hints.get(i) != 1) {
				answer = false;
			}
			i++;
		}
		
		if (answer) {
			System.out.println("Passed test 1!");
		} else {
			System.out.println("Failed test 1!");
		}
		
		return answer;
	}

	//Checks if the guess has a colour that is the same
	private static boolean containsColour(int randomInt, ArrayList<Integer> guess) {
		boolean answer = false;
				
		int x = 0;
				
		while (x != guess.size() && answer == false) {
			int temp = guess.get(x);
				
			if (temp == randomInt) {
				answer = true;
			}
			x++;
		}
				
		return answer;
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

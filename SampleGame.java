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
		
		firstTest = true;
		
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
			
			//May create difficulties 
			//Easy = 10 guesses
			//Medium = 8 guesses
			//Hard = 6 guesses
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
		boolean answer = false;
		
		//Measures the amt of each type of hint
		//2 cor color cor pos
		//1 cor color wro pos
		//0 wro color wro pos
		int twos = 0;
		int ones = 0;
		int zeros = 0;
		
		ArrayList<Integer> sampleTest = new ArrayList<Integer>();
	    
		//Solution
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		MasterMindGame newGame = new MasterMindGame(sampleTest, 8);
		int i = 0;
		
		//Try first test
		while (i != 4) {
			newGame.addToEndGuess(WHITE);
			i++;
		}
		//Do the check
		newGame.guessCheckDup();
		//Get hints
		ArrayList<Integer> hints = newGame.guessRes();
		
		i = 0;
		
		//Count the ones zeros and twos
		while (i != hints.size()) {
			if (hints.get(i) == 0) {
				zeros++;
			} else if (hints.get(i)==1) {
				ones++;
			} else {
				twos++;
			}
			i++;
		}
		
		//Should have 4 zeros
		if (zeros == 4) {
			System.out.println("Passed test 1!");
			answer = true;
		} else {
			System.out.println("Failed test 1!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be zeros 4");
		}
	
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
		
		//Try Next Test
		newGame.addToEndGuess(BLACK);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(RED);
		
		//Check
		newGame.guessCheckDup();
		hints = newGame.guessRes();
		
		i = 0;
		while (i != hints.size()) {
			if (hints.get(i) == 0) {
				zeros++;
			} else if (hints.get(i)==1) {
				ones++;
			} else {
				twos++;
			}
			i++;
		}
		
		//Should have 2 twos
		if (twos == 2) {
			System.out.println("Passed test 2!");
			answer = true;
		} else {
			System.out.println("Failed test 2!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be twos 2");
		}
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
				
		//Try Next Test
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(BLACK);
		newGame.addToEndGuess(BLACK);
				
		//Check
		newGame.guessCheckDup();
		hints = newGame.guessRes();
				
		i = 0;
		while (i != hints.size()) {
			if (hints.get(i) == 0) {
				zeros++;
			} else if (hints.get(i)==1) {
				ones++;
			} else {
				twos++;
			}
			i++;
		}
				
		//Should have 2 ones
		if (ones == 2) {
			System.out.println("Passed test 3!");
			answer = true;
		} else {
			System.out.println("Failed test 3!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be ones 2");
		}
			
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
						
		//Try Next Test
		newGame.addToEndGuess(BLACK);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(BLUE);
						
		//Check
		newGame.guessCheckDup();
		hints = newGame.guessRes();
						
		i = 0;
		while (i != hints.size()) {
			if (hints.get(i) == 0) {
				zeros++;
			} else if (hints.get(i)==1) {
				ones++;
			} else {
				twos++;
			}
			i++;	
		}
						
		//Should have 4 twos
		if (twos == 4) {
			System.out.println("Passed test 4!");
			answer = true;
		} else {
			System.out.println("Failed test 4!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be twos 4");
		}
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
								
		//Try Next Test
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(BLACK);
		newGame.addToEndGuess(BLUE);
		newGame.addToEndGuess(GREEN);
								
		//Check
		newGame.guessCheckDup();
		hints = newGame.guessRes();
								
		i = 0;
		while (i != hints.size()) {
			if (hints.get(i) == 0) {
				zeros++;
			} else if (hints.get(i)==1) {
				ones++;
			} else {
				twos++;
			}
			i++;	
		}
								
		//Should have 4 ones
		if (ones == 4) {
			System.out.println("Passed test 5!");
			answer = true;
		} else {
			System.out.println("Failed test 5!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be ones 4");
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

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author Geoffrey
 *
 */
public class SampleGame {

	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int BLUE = 2;
	public final static int YELLOW = 3;
	public final static int WHITE = 4;
	public final static int PINK = 5;
	public final static int guessNum = 8;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		playSound();
		boolean firstTest = runTestsDup();
		firstTest = runTests();
		firstTest = true;
		
		if (firstTest == true) {
			//Make a random solution
			ArrayList<Integer> sampleTest = new ArrayList<Integer>();
		    
			sampleTest.add(PINK);
			sampleTest.add(RED);
			sampleTest.add(GREEN);
			sampleTest.add(BLUE);
			
			//Make  gui
			GUI gui = new GUI();
			
			//CREATE START SCREEN GUI FIRST
			gui.createStartScreen();
			//gui.showStartScreen();
			
			printArray(sampleTest);
		
		}
	}

	//Test duplicate colour guesses and colour solutions
	private static boolean runTestsDup() {
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
		sampleTest.add(PINK);
		sampleTest.add(PINK);
		sampleTest.add(GREEN);
		sampleTest.add(GREEN);
		
		MasterMindGame newGame = new MasterMindGame(sampleTest, 8, true);
		int i = 0;
		
		//Try first test
		while (i != 4) {
			newGame.addToEndGuess(WHITE);
			i++;
		}
		//Do the check
		newGame.guessCheck();
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
		
		//Should have 1 two
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
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(RED);
		
		//Check
		newGame.guessCheck();
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
		
		//Should have 1 twos and 1 ones
		if (twos == 1 && ones == 1) {
			System.out.println("Passed test 2!");
			answer = true;
		} else {
			System.out.println("Failed test 2!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be twos 1 ones 1");
		}
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
				
		//Try Next Test
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(PINK);
				
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 3!");
			answer = true;
		} else {
			System.out.println("Failed test 3!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be ones 4");
		}
			
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
						
		//Try Next Test
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(GREEN);
						
		//Check
		newGame.guessCheck();
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
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(BLUE);
		newGame.addToEndGuess(GREEN);
								
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 5!");
			answer = true;
		} else {
			System.out.println("Failed test 5!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be twos 2");
		}
		
		return answer;
	}

	//Test the system with single colours
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
		sampleTest.add(PINK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		MasterMindGame newGame = new MasterMindGame(sampleTest, 8, true);
		int i = 0;
		
		//Try first test
		while (i != 4) {
			newGame.addToEndGuess(WHITE);
			i++;
		}
		//Do the check
		newGame.guessCheck();
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
			System.out.println("Passed test 6!");
			answer = true;
		} else {
			System.out.println("Failed test 6!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be zeros 4");
		}
	
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
		
		//Try Next Test
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(RED);
		
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 7!");
			answer = true;
		} else {
			System.out.println("Failed test 7!");
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
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(PINK);
				
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 8!");
			answer = true;
		} else {
			System.out.println("Failed test 8!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be ones 2");
		}
			
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
						
		//Try Next Test
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(GREEN);
		newGame.addToEndGuess(BLUE);
						
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 9!");
			answer = true;
		} else {
			System.out.println("Failed test 9!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be twos 4");
		}
		
		//Reset values
		ones = 0;
		twos = 0;
		zeros = 0;
								
		//Try Next Test
		newGame.addToEndGuess(RED);
		newGame.addToEndGuess(PINK);
		newGame.addToEndGuess(BLUE);
		newGame.addToEndGuess(GREEN);
								
		//Check
		newGame.guessCheck();
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
			System.out.println("Passed test 10!");
			answer = true;
		} else {
			System.out.println("Failed test 10!");
			System.out.println("Received twos " + twos +" ones " + ones + " zeros " + zeros);
			System.out.println("Should be ones 4");
		}
		
		return answer;
	}

	//Checks if the guess has a colour that is the same
//	private static boolean containsColour(int randomInt, ArrayList<Integer> guess) {
//		boolean answer = false;
//				
//		int x = 0;
//				
//		while (x != guess.size() && answer == false) {
//			int temp = guess.get(x);
//				
//			if (temp == randomInt) {
//				answer = true;
//			}
//			x++;
//		}
//				
//		return answer;
//	}
	/**
	 * 
	 */
	public static void playSound() {
	    try {
	    	File soundFile = new File("src/bgm.wav");
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        System.out.println("Playing " + soundFile.getName());
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
		
	//Prints the array
	/**
	 * @param guess
	 */
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
			} else if (colour == PINK) {
				System.out.print("pink ");
			} else if (colour == WHITE) {
				System.out.print("white ");
			}
			i++;
		}
		System.out.println();
	}

	
}

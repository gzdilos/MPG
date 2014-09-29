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
		// TODO Auto-generated method stub
		ArrayList<Integer> sampleTest = new ArrayList<Integer>();
		
		sampleTest.add(BLACK);
		sampleTest.add(RED);
		sampleTest.add(GREEN);
		sampleTest.add(BLUE);
		
		//Make a game
		MasterMindGame s = new MasterMindGame(sampleTest, guessNum);
		
		System.out.println("Solution is");
		printArray(sampleTest);
		
		//Call AI to run game
		aiPlayGame(s);
	}

	private static void aiPlayGame(MasterMindGame s) {

		int guessAmt = s.getMaxGuessAmt();
		
		int j = 0;
		
		while (j != guessAmt) {
			makeAGuess(s, j);
			j++;
		}
	}

	private static void makeAGuess(MasterMindGame s, int j) {
		Random randomGenerator = new Random();
		
		ArrayList<Integer> guess = new ArrayList<Integer>();

		boolean temp;
		
		int i = 0;
		
	    while (i < 4) {
	      int randomInt = randomGenerator.nextInt(6);
	      
	      if (containsColour(randomInt, guess)) {
	    	  i--;
	      } else {
	    	  guess.add(randomInt);
	      }
	      i++;
	    }
	    
	    System.out.println("Guess was " + (j+1));
	    printArray(guess);
	    temp = s.guessCheck(guess);
	    
	    System.out.println("Guess " + (j+1) + " was " + temp);

	}

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

	private static void printArray(ArrayList<Integer> guess) {
		int i = 0;
		
		while (i != guess.size()) {
			int colour = guess.get(i);
			
			if (colour == RED) {
				System.out.print("Red ");
			} else if (colour == BLUE) {
				System.out.print("Blue ");
			} else if (colour == GREEN) {
				System.out.print("Green ");
			} else if (colour == YELLOW) {
				System.out.print("Yellow ");
			} else if (colour == BLACK) {
				System.out.print("Black ");
			} else if (colour == WHITE) {
				System.out.print("White ");
			}
			i++;
		}
		System.out.println();
	}

	
}

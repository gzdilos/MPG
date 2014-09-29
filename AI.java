import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private MasterMindGame g;
	private int difficulty;
	private ArrayList<String> colourList;
	
	private final int EASY = 0;
	private final int MEDIUM = 1;
	private final int HARD = 2;
	//Define difficulty
	//0 is easy
	//1 is medium
	//2 is hard
	public AI (MasterMindGame g, int difficulty) {
		this.g = g;
		this.difficulty = difficulty;
		colourList = g.getColourList();
	}
	
	public void playGame() {
		if (difficulty == EASY) {
			playEasyGame();
		} else if (difficulty == MEDIUM) {
			playMediumGame();
		} else if (difficulty == HARD) {
			playHardGame();
		}
	}

	//Plays like a god
	private void playHardGame() {
		// TODO Auto-generated method stub
		
	}

	//Plays with a strategy
	private void playMediumGame() {
		// TODO Auto-generated method stub
		
	}

	//Noob player
	private void playEasyGame() {
		int guessAmt = g.getMaxGuessAmt();
		
		int j = 0;
		
		while (j != guessAmt) {
			makeAGuess(g, j);
			j++;
		}
	}
	
	private void makeAGuess(MasterMindGame s, int j) {
		Random randomGenerator = new Random();
		
		ArrayList<Integer> guess = new ArrayList<Integer>();

		boolean temp;
		
		int i = 0;
		
	    while (i < 4) {
	      int randomInt = randomGenerator.nextInt(6);
	      
	      //Tries to choose a different colour each time
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
	private void printArray(ArrayList<Integer> guess) {
		int i = 0;
			
		while (i != guess.size()) {
			int colour = guess.get(i);
			
			System.out.print(colourList.get(colour) + " ");
			
			i++;
		}
		System.out.println();
	}
}

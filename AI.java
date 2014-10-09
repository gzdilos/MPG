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
		// Try to get the colours needed
		// Consider amount of colours and amount of guesses
		int guessAmt = g.getMaxGuessAmt();
		ArrayList<ArrayList<Integer>> allGuesses = new ArrayList<ArrayList<Integer>>();
		
		//Replace object with format of hints
		ArrayList<Object> hints = new ArrayList<Object>();
		
		if (guessAmt < colourList.size() + 2) {
			//Use a different strategy
		} else {
			//Guess all colours
			ArrayList<Integer> allColours = getColours(allGuesses);
		}
		
		// Organize those colours
		// Make a guess on which colour is right position
		
	}

	private ArrayList<Integer> getColours(ArrayList<ArrayList<Integer>> allGuesses) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		int i = 0;
		
		while(i != colourList.size()) {
			
			int j = 0;
			
			while (j != g.getSolutionSize()) {
				answer.add(i);
				j++;
			}
			
			//Check if this colour is contained
			//Need to look at hints
			
			
			i++;
		}
		
		
		return answer;
	}

	//Noob player
	private void playEasyGame() {
		int guessAmt = g.getMaxGuessAmt();
		
		ArrayList<ArrayList<Integer>> allGuesses = new ArrayList<ArrayList<Integer>>();
		
		int j = 0;
		
		while (j != guessAmt) {
			boolean correct = makeAGuess(g, j, allGuesses);
			
			if (correct) {
				//Stop guessing?
				j = guessAmt;
			}
			j++;
		}
	}
	
	private boolean makeAGuess(MasterMindGame s, int j, ArrayList<ArrayList<Integer>> allGuesses) {
		Random randomGenerator = new Random();
		ArrayList<Integer> guess = new ArrayList<Integer>();

		boolean temp;
		
		int i = 0;
		
	    while (i < s.getSolutionSize()) {
	      int randomInt = randomGenerator.nextInt(6);
	      
	      //Tries to choose a different colour each time
	      if (containsColour(randomInt, guess)) {
	    	  i--;
	      } else {
	    	  guess.add(randomInt);
	      }
	      
	      //Check if we made a guess that we already did
	      if (isDuplicate(guess, allGuesses)) {
	    	  //Reset
	    	  i = 0;
	      } else {
	    	  //Add to list
	    	  allGuesses.add(guess);
	      }
	      
	      i++;
	    }
	    
	    System.out.println("Guess was " + (j+1));
	    printArray(guess);
	    temp = s.guessCheck(guess);
	    
	    System.out.println("Guess " + (j+1) + " was " + temp);
	    return temp;
	}
	
	//Check if there is a duplicate guess
	private boolean isDuplicate(ArrayList<Integer> guess, ArrayList<ArrayList<Integer>> allGuesses) {
		boolean answer = false;
		int i = 0;
		
		//Go through all the guesses made
		while (i != allGuesses.size()) {
			
			int j = 0;
			ArrayList<Integer> aGuess = allGuesses.get(i);
			
			boolean isSame = true;
			//Check each entry in the guess to see if numbers are same
			while (j != guess.size() && isSame == true) {
				
				if (guess.get(j) != aGuess.get(j)) {
					isSame = false;
				}
				j++;
			}
			i++;
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

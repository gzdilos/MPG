import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private MasterMindGame g;
	private int difficulty;
	private ArrayList<String> colourList;
	private ArrayList<ArrayList<Integer>> allGuesses;
	private ArrayList<ArrayList<Integer>> allHints;
	
	private final int EASY = 0;
	private final int MEDIUM = 1;
	private final int HARD = 2;
	
	//Gives current AI move
	private int curAIMove;
	//Gives current AI hint
	private int curAIHint;
	//Define difficulty
	//0 is easy
	//1 is medium
	//2 is hard
	public AI (MasterMindGame g, int difficulty) {
		this.g = g;
		this.difficulty = difficulty;
		colourList = g.getColourList();
		allGuesses = new ArrayList<ArrayList<Integer>>();
		allHints = new ArrayList<ArrayList<Integer>>();
		curAIMove = 0;
		curAIHint = 0;
	}
	
	public void playGame() {
		if (difficulty == EASY) {
			playEasyGame();
		} else if (difficulty == MEDIUM) {
			g.setDuplicateTrue();
			playMediumGame();
		} else if (difficulty == HARD) {
			playHardGame();
			//playMediumGame();
		}
	}

	//Plays like a god
	private void playHardGame() {
		//Get solution
		ArrayList<Integer> sol = g.getSolution();
		
		//Store guess
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		boolean gotAnswer = false;
		
		int i = 0;
			
		while (i != 4 && !gotAnswer) {
				
			int l = 0;
				
			//Add elements from solution
			while (l != (i + 1)) {
				temp.add(sol.get(l));
				l++;
			}
				
			//Generate num of random values to go with it
			temp = generateCol(temp, 3 - i);
				
			//Add guesses to memory
			allGuesses.add(temp);
				
			//Add guess to puzzle to guess
			int k = 0;
				
			while (k != 4) {
				g.addToEndGuess(temp.get(k));
				k++;
			}
			
			//Make a guess
			gotAnswer = g.guessCheck();
			
			//Get the hints
			ArrayList<Integer> hint = g.guessRes();
			allHints.add(hint);
			
			//Clear the guess
			temp = new ArrayList<Integer>();
			i++;
		}

	}

	//Generates num number of values to add to the arraylist
	private ArrayList<Integer> generateCol(ArrayList<Integer> temp, int num) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		answer = temp;
		
		Random randomGenerator = new Random();
		
		int i = 0;
		
	    while (i != num) {
	    	int randomInt = randomGenerator.nextInt(6);
	      
	    	answer.add(randomInt);
	      
	    	i++;
	    }
		
		return answer;
	}

	//Get one move of AI
	public ArrayList<Integer> getAIMove() {
		int i = curAIMove;
		
		curAIMove++;
		
		return allGuesses.get(i);
	}
	
	//Get one move of AI
	public ArrayList<Integer> getAIHint() {
		int i = curAIHint;
			
		curAIHint++;
			
		return allHints.get(i);
	}
	
	//Plays with a strategy
	private void playMediumGame() {
		// Try to get the colours needed
		// Consider amount of colours and amount of guesses
		int guessAmt = g.getMaxGuessAmt();
		//ArrayList<ArrayList<Integer>> allGuesses = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> allColours = new ArrayList<Integer>();
		
		if (guessAmt < colourList.size() + 2) {
			//Use a different strategy
		} else {
			//Guess all colours
			allColours = getColours();
			System.out.println("Colours are ");
			printArray(allColours, false);
		}
		
		// Organize those colours
		// Make a guess on which colour is right position
		if (allColours == null) {
			//Got correct a solution
		} else {
			//Try a combination of the values as a guess
			attemptCombinations(allColours);
			
		}
		
		
	}

	//Try different combinations since we have all the colours
	private void attemptCombinations(ArrayList<Integer> allColours) {

		int i = 0;
		
		allGuesses.add(allColours);
		
		while(i != allColours.size()) {
			g.addToEndGuess(allColours.get(i));
			i++;
		}
		
		//First useful guess
		boolean correct = g.guessCheck();
		//int count = 0;
		allHints.add(g.guessResAI());
		
		if (!correct) {
			//Hint from the guess result
			int correctPos = getCorPos(allColours);
			
			System.out.println("corPos is " + correctPos);
			
			while (correctPos != 4 && allGuesses.size() != g.getMaxGuessAmt()) {
				if (correctPos == 0) {
					//Case 0 correct
					correctPos = zeroCor(allGuesses.size());
				} else if (correctPos == 1) {
					//Case 1 correct
					correctPos = oneCor(allGuesses.size());
				} else {
					//Case 2 correct
					correctPos = twoCor(allGuesses.size());
				}
				//count++;
				System.out.println("Size of allGuess is " + allGuesses.size());
			}
		}
		
	}

	//One colour is correct
	private int oneCor(int size) {
		ArrayList<Integer> oldGuess = allGuesses.get(size - 1);
		int amtCor = 0;
		
		ArrayList<Integer> hint = g.guessResAI();
		
		int j = 0;
		int corPos = 0;
		
		//Get the position of the value thats correct
		while (j != hint.size()) {
			if (hint.get(j) == 2) {
				corPos = j;
			}
			j++;
		}
		
		//Shuffle remaining values left
		int i = 1;
		
		while (i != oldGuess.size()) {
			if (i == corPos) {
				//g.addToGuess(oldGuess.get(i));
				i++;
			} else {
				g.addToEndGuess(oldGuess.get(i));
			}
			i++;
		}
		
		if (corPos != 0) {
			g.addToGuess(oldGuess.get(0), 0);
		} else {
			g.addToEndGuess(oldGuess.get(0));
		}
		
		g.addToGuess(oldGuess.get(corPos), corPos);
		allGuesses.add(g.getFullGuess());
		
		boolean correct = g.guessCheck();
		
		if (correct) {
			amtCor = 4;
			allHints.add(g.guessRes());
		} else {
			amtCor = getCorPos(g.guessResAI());
			allHints.add(g.guessRes());
		}
		
		return amtCor;
	}

	//Two colours is correct
	private int twoCor(int size) {
		ArrayList<Integer> oldGuess = allGuesses.get(size - 1);
		int amtCor = 0;
		
		ArrayList<Integer> hint = g.guessResAI();
		
		int j = 0;
		int corPos1 = -1;
		int corPos2 = -1;
		
		while (j != hint.size()) {
			if (hint.get(j) == 2 && corPos1 == -1) {
				corPos1 = j;
			} else if (hint.get(j) == 2){
				corPos2 = j;
			}
			j++;
		}
		
		int i = 0;
		
		while (i != g.getSolutionSize()) {
			if (i == corPos1 || i == corPos2) {
				i++;
			} else {
				g.addToEndGuess(oldGuess.get(i));
			}
			i++;
		}
		
		if (corPos1 != 0 && corPos2 != 0) {
			g.addToGuess(oldGuess.get(0), 0);
		} else {
			g.addToEndGuess(oldGuess.get(0));
		}
		
		g.addToGuess(oldGuess.get(corPos1), corPos1);
		g.addToGuess(oldGuess.get(corPos2), corPos2);
		
		allGuesses.add(g.getFullGuess());
		boolean correct = g.guessCheck();
		
		if (correct) {
			amtCor = 4;
			allHints.add(g.guessRes());
		} else {
			amtCor = getCorPos(g.guessResAI());
			allHints.add(g.guessRes());
		}
		
		return amtCor;
	}

	//Case 0 correct
	//Bit shift answer
	private int zeroCor(int j) {

		int i = 1;
		int amtCor = 0;
		ArrayList<Integer> oldGuess = allGuesses.get(j - 1);
		System.out.print("Last Guess was ");
		printArray(oldGuess, false);
		while (i != oldGuess.size()) {
			g.addToEndGuess(oldGuess.get(i));
			i++;
		}
		
		g.addToEndGuess(oldGuess.get(0));
		
		//Add guess to list
		allGuesses.add(g.getFullGuess());
		
		//Prints full guess
		System.out.print("Full guess is ");
		printArray(g.getFullGuess(), false);
		
		boolean correct = g.guessCheck();
		
		System.out.print("Full guess after clear is ");
		printArray(g.getFullGuess(), false);
		
		if (correct) {
			//done?
			amtCor = 4;
			allHints.add(g.guessRes());
		} else {
			amtCor = getCorPos(g.guessResAI());
			allHints.add(g.guessResAI());
			System.out.println("amtCor is " + allHints.size());
		}
		
		return amtCor;
	}

	//Gets the amount of stuff in the correct position
	private int getCorPos(ArrayList<Integer> allColours) {
		ArrayList<Integer> hint = g.guessRes();
		int i = 0;
		int corPos = 0;
		
		i = 0;
		
		while (i != hint.size()) {
			if (hint.get(i) == 2) {
				corPos++;
			}
			i++;
		}
		
		return corPos;
	}

	//Get the colours of the solution
	private ArrayList<Integer> getColours() {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		int i = 0;
		
		while(i != colourList.size() && (answer.size() != g.getSolutionSize())) {
			
			int j = 0;
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			
			//Guess 4 of one colour
			while (j != g.getSolutionSize()) {
				g.addToEndGuess(i);
				temp.add(i);
				j++;
			}
			
			//Add the guess to the guesses
			allGuesses.add(temp);
			
			//printArray(temp, false);
			//Do a check
			boolean correct = g.guessCheck();
			
			//If correct then break
			if (correct) {
				answer = null;
				break;
			}
			
			//Check if this colour is contained
			//Need to look at hints
			//2 means correct pos
			//1 means correct colour
			ArrayList<Integer> hint = g.guessRes();
			//System.out.println("Hint is ");
			//printArray(hint, true);
			allHints.add(hint);
			int x = 0;
			
			//If there was any correct colours we add to answer
			while (x != hint.size()) {
				//We got a correct colour
				if (hint.get(x) == 2) {
					answer.add(i);
				}
				x++;
			}
			
			//If we have 3 right colours and its the last colour to check
			if ((answer.size() == g.getSolutionSize() - 1) && (i == colourList.size() - 2)) {
				answer.add(i + 1);
				//System.out.println("Saved a guess!");
				i++;
			}
			i++;
		}
		
		return answer;
	}

	//Noob player
	private void playEasyGame() {
		int guessAmt = g.getMaxGuessAmt();
		
		//ArrayList<ArrayList<Integer>> allGuesses = new ArrayList<ArrayList<Integer>>();
		
		int j = 0;
		
		while (j != guessAmt) {
			boolean correct = makeAGuess(j);
			
			if (correct) {
				//Stop guessing?
				j = guessAmt;
			}
			System.out.println("j is " + j);	
			j++;
		}
	}
	
	private boolean makeAGuess(int j) {
		Random randomGenerator = new Random();
		ArrayList<Integer> guess = new ArrayList<Integer>();

		boolean temp;
		
		int i = 0;
		
	    while (i < g.getSolutionSize() + 1) {
	      int randomInt = randomGenerator.nextInt(6);
	      
	      //System.out.println("Guess is " + randomInt);
	      //Tries to choose a different colour each time
	      if (containsColour(randomInt, guess)) {
	    	  i--;
	      } else {
	    	  guess.add(randomInt);
	      }
	      
	      //When we got a full guess
	      if (guess.size() == 4) {
	    	//Check if we made a guess that we already did
		      if (isDuplicate(guess)) {
		    	  //Reset
		    	  i = 0;
		      } else {
		    	  //Add to list
		    	  allGuesses.add(guess);
		    	  
		    	  //Add the guess to the mmg
		    	  int p = 0;
		    	  
		    	  while (p != g.getSolutionSize()) {
		    		  g.addToEndGuess(guess.get(p));
		    		  p++;
		    	  }
		    	  
		    	  //Stop adding
		    	  i = 5;
		      }
	      }
	      i++;
	    }
	    
	    System.out.println("Guess was " + (j+1));
	    printArray(guess, false);
	    //ArrayList<Integer> guessMade = g.getFullGuess();
	    //System.out.println("Guess inputed was " + (j+1));
	    //printArray(guessMade);
	    temp = g.guessCheck();
	    
	    ArrayList<Integer> hints = g.guessRes();;
	    
	    allHints.add(hints);
	    
	    //System.out.println("Hints was " + (j+1));
	    //printArray(hints);
	    
	    System.out.println("Guess " + (j+1) + " was " + temp);
	    return temp;
	}
	
	//Check if there is a duplicate guess
	private boolean isDuplicate(ArrayList<Integer> guess) {
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
	private void printArray(ArrayList<Integer> guess, boolean b) {
		int i = 0;
			
		while (i != guess.size()) {
			int colour = guess.get(i);
			
			if (b) {
				System.out.print(colour + " ");
			} else {
				System.out.print(colourList.get(colour) + " ");
			}
			
			
			i++;
		}
		System.out.println();
	}

	//Returns the current hint
	public int curHint() {
		return curAIHint - 1;
	}

	//Returns the current move
	public int curMove() {
		return curAIMove - 1;
	}

	//Reset the ai game and play a new game
	public void resetGame() {
		g.resetGame();
		allGuesses = new ArrayList<ArrayList<Integer>>();
		allHints = new ArrayList<ArrayList<Integer>>();
		curAIMove = 0;
		curAIHint = 0;
		this.playGame();
	}
	
	//Returns a winning move if there was one
	public int winMove() {
		return allGuesses.size();
	}
	
}

import java.util.ArrayList;
import java.util.Random;


public class MasterMindGame {
	private ArrayList<Integer> finalSolution;
	//Guesses is a 2D array, size is [Amount of guesses] [Solution size * 2]. 
	//It stores the guessed colours as integers and
	//stores the correct/partially correct/wrong result as a 2, 1 and 0 respectively.
	private int [][] guesses;
	private int [][] AIGuesses;
	private int guessAmt;
	private int currGuess;
	private int solutionSize;
	private ArrayList<String> colourList;
	
	//Store the current guess
	private ArrayList<Integer> theGuess;
	
	//Store locations for GUI of guess
	private ArrayList<Integer> pos;
	
	//Constants
	public final int RED = 0;
	public final int GREEN = 1;
	public final int BLUE = 2;
	public final int YELLOW = 3;
	public final int WHITE = 4;
	public final int BLACK = 5;
	 
	//This code assumes colours cannot be repeated for the time being.
	public MasterMindGame(ArrayList<Integer> answer, int guessAmount){
		solutionSize = answer.size();
		guessAmt = guessAmount;
		finalSolution = new ArrayList<Integer>();
		finalSolution = answer;
		currGuess = 0;
		guesses = new int[guessAmount][solutionSize*2];
		//Used my AI to cheat
		AIGuesses = new int[guessAmount][solutionSize*2];
		colourList = new ArrayList<String>();
		addColours();
		theGuess = new ArrayList<Integer>();
		pos = new ArrayList<Integer>();
	}
	 
 
	//Adds the colours of a colour list
	private void addColours() {
		colourList.add("red");
		colourList.add("green");
		colourList.add("blue");
		colourList.add("yellow");
		colourList.add("white");
		colourList.add("black");
	}
	
	public boolean guessCheck(){
		
		//Check if solved by assuming it is solved and then disproving.
		boolean solved = true;
		
		//Check for invalid guesses.
		//Probably should restrict user to the amount required
		if (theGuess.size() != solutionSize) {
			solved = false;
		}
			
		if (currGuess == guessAmt) {
			solved = false;
		} else {
		
			//Assume these include hints
			//Rondo's code
			
			int iterator = 0;
			
			while (iterator != solutionSize){
				//Set the guessed values, assume 0 is not a choosable colour.
				//You mean you assume that you can't choose no colours
				guesses[currGuess][iterator] = theGuess.get(iterator);
				iterator++;
			}
			 
			iterator = 0;
			 
			while (iterator != solutionSize){
				//Check if the guess is the right colour AND position. Then check for the colour alone.
				if (theGuess.get(iterator)== finalSolution.get(iterator)){
					guesses[currGuess][iterator + solutionSize] = 2;
					AIGuesses[currGuess][iterator + solutionSize] = 2;
				} else if(finalSolution.contains(theGuess.get(iterator))){
					guesses[currGuess][iterator + solutionSize] = 1;
					AIGuesses[currGuess][iterator + solutionSize] = 1;
					solved = false;
				} else {
					AIGuesses[currGuess][iterator + solutionSize] = 0;
					solved = false;
				}
						
				iterator++;
			}
			
			currGuess++;
			clearGuess();
		}
		
		return solved;
	 }

	//Returns the amount of max guess
	public int getMaxGuessAmt() {
		return guessAmt;
	}
	
	//Returns the current amount of guesses
	public int getCurGuessAmt() {
		return currGuess;
	}
	
	//Returns a colour list
	public ArrayList<String> getColourList() {
		return colourList;
	}
	
	//Returns a colour
	public String getColourList(int i) {
		return colourList.get(i);
	}
		
	//Returns the guess solution size
	public int getSolutionSize() {
		return solutionSize;
	}

	//Adds to a guess
	public void addToGuess(Integer colourSel, int i) {
		theGuess.add(i, colourSel);
	}
	
	//Used by grid handler to remember positions
	public void addGuessPos(Integer colourSel, int i) {
		
		//Add guess
		theGuess.add(colourSel);
		
		//Add pos
		pos.add(i);
		
		if (pos.size() == finalSolution.size()) {
			rearrangeGuess();
		}
	}
	
	//Arrange the guess appropriately
	private void rearrangeGuess() {
		int i = 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		int lowest = 0;
		//Perform selection sort
		while (i != pos.size()) {
			int newLow = pos.get(i);
			
			if (newLow != lowest) {
				i++;
			} else {
				temp.add(theGuess.get(i));
				theGuess.remove(i);
				pos.remove(i);
				lowest++;
				i = 0;
			}
		}
		
		theGuess = temp;
		//printArray(theGuess);
	}


	private void printArray(ArrayList<Integer> theGuess2) {
		int i = 0;
		
		while (i != theGuess2.size()) {
			int colour = theGuess2.get(i);
			
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


	//Adds to end of a guess
	public void addToEndGuess(Integer colourSel) {
		theGuess.add(colourSel);
	}
		
	//Clear the guess
	public void clearGuess() {
		theGuess = new ArrayList<Integer>();
		pos = new ArrayList<Integer>();
	}
	
	//Retrieve Guess
	public ArrayList<Integer> getFullGuess() {
		return theGuess;
	}
		
	//Retrieve Solution
	public ArrayList<Integer> getSolution() {
		return finalSolution;
	}
		
	
	//Return an arraylist consisting of just the guess results.
	public ArrayList<Integer> guessRes(){
		 ArrayList<Integer> retGuess = new ArrayList<Integer>();
	  
		 int oldGuess = currGuess - 1;
		 
		 int iterator = 0;
		 //System.out.println("Solution size is " + solutionSize);
		 while(iterator != solutionSize){
				 retGuess.add(guesses[oldGuess][iterator + solutionSize]);
				 //System.out.println("Guess hint is " + guesses[oldGuess][iterator + solutionSize]);
				 
				 iterator++; 
		 }
		 
		 
		return retGuess;			  
	 }
	
	//Return an arraylist for AI consisting of just the guess results.
	public ArrayList<Integer> guessResAI(){
		 ArrayList<Integer> retGuess = new ArrayList<Integer>();
		  
		 int oldGuess = currGuess - 1;
		 
		 int iterator = 0;
		 while(iterator != solutionSize){
			 retGuess.add(AIGuesses[oldGuess][iterator + solutionSize]);	
			 iterator++; 
		 }
		 
		return retGuess;			  
	 }
	
	//Converts a hint to string
	public String convertHintToString() {
		ArrayList<Integer> retGuess = new ArrayList<Integer>();
		StringBuilder s = new StringBuilder();
		
		int iterator = 0;
		
		int oldGuess = currGuess - 1;
		//We will define B as correct colour correct position
		//W as correct colour wrong position
		while(iterator != solutionSize){
			retGuess.add(guesses[oldGuess][iterator + solutionSize]);	
			
			if (guesses[oldGuess][iterator + solutionSize] == 2) {
				s.append("B");
				//System.out.println("adding B");
			} else if (guesses[oldGuess][iterator + solutionSize] == 1) {
				s.append("W");
				//System.out.println("adding W");
			} else {
				s.append("X");
				//System.out.println("adding X");
			}
			
			iterator++; 
		}
		 
		//System.out.println("solution size is " + solutionSize);
		//System.out.println(s);
		
		String value = s.toString();
		
		System.out.println(value);
		
		return value;	
	}
	
	//Converts a hint to string
	public String convertAHintToString(ArrayList<Integer> theHint) {
		StringBuilder s = new StringBuilder();
			
		int i = 0;

		//We will define B as correct colour correct position
		//               W as correct colour wrong position
		
		while(i != theHint.size()){
			int val = theHint.get(i);	
				
			if (val == 2) {
				s.append("B");
				//System.out.println("adding B");
			} else if (val == 1) {
				s.append("W");
				//System.out.println("adding W");
			} else {
				s.append("X");
				//System.out.println("adding X");
			}
				
			i++; 
		}
			 
		//System.out.println("solution size is " + solutionSize);
		//System.out.println(s);
			
		String value = s.toString();
			
		System.out.println(value);
			
		return value;	
	}
		
	//Resets the game
	public void resetGame() {
		generatePuzzle();
		currGuess = 0;
		guesses = new int[guessAmt][solutionSize*2];
		//Used my AI to cheat
		AIGuesses = new int[guessAmt][solutionSize*2];
		colourList = new ArrayList<String>();
		//addColours();
		theGuess = new ArrayList<Integer>();
		pos = new ArrayList<Integer>();
	}
	
	//Randomly generate a new puzzle
	public void generatePuzzle() {
		ArrayList<Integer> sampleTest = new ArrayList<Integer>();
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
	    }
	    
	    finalSolution = sampleTest;
	}
	
	//Check if a colour is contained in the arraylist
	private boolean containsColour(int randomInt, ArrayList<Integer> guess) {
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
	
	//Does a duplicate check of guesses
	public boolean guessCheckDup(){
	//Check for invalid guesses.
	 
		if (theGuess.size() != solutionSize) {
			return false;
		}
		
		if(currGuess == guessAmt) {
			return false;
		}
		
		int iterator = 0;
		ArrayList<Integer> tempAns = new ArrayList<Integer>();
		ArrayList<Integer> tempGuess = new ArrayList<Integer>();
	
		//This time, solved is a loopbreak, so it gets a rename.
		boolean loopBreak = false;
	 
		while(iterator != solutionSize){
			//Set the guessed values, assume 0 is not a choosable colour.
			guesses[currGuess][iterator] = theGuess.get(iterator);
			tempAns.add(finalSolution.get(iterator));
			tempGuess.add(theGuess.get(iterator));
			iterator++;
		}
		
		iterator = solutionSize - 1;
	 
		//This requires some reverse traversal so it will actually work as intended. Else tempAns.remove(iterator) will fail on a correct guess.
		while(iterator != -1){
			//Check if the guess is the right colour AND position. Then modify the guess so we do not mess things up.
			//This time, we store the guess and answer in temporary array lists and remove the element when we come across it, so it will give false results from duplicates.
			if(theGuess.get(iterator)== finalSolution.get(iterator)){
				guesses[currGuess][iterator + solutionSize] = 2;
				tempAns.remove(iterator);
				tempGuess.remove(iterator);
//				retGuess.add(2);
			}
			iterator--;
		}
		
		//This if statement is only true for one case, when the guess was correct.
		if(tempAns.size() == 0) return true;
	 
		iterator = tempGuess.size() - 1;
		int iterator2 = tempAns.size() -1;
		
		while(iterator != -1){
	 
			//Iterate through the answer, using this instead of .contains to mitigate integer and .remove issues.
			while(iterator2 != -1 && loopBreak == false){
		
				if(tempAns.get(iterator2) == tempGuess.get(iterator)){
					guesses[currGuess][iterator + solutionSize] = 1;
					tempGuess.remove(iterator);
					tempAns.remove(iterator2);
					loopBreak = true;
				}
				iterator2 --;
			}	
		 
			//Check through the remaining guesses.
			iterator2 = tempAns.size()-1;
			iterator--;
			loopBreak = false;
		}
		
		currGuess++;
		clearGuess();
		
		return false;
	}
}

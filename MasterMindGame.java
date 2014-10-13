import java.util.ArrayList;


public class MasterMindGame {
	ArrayList<Integer> finalSolution;
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
	
	//Constants
	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int BLUE = 2;
	public final static int YELLOW = 3;
	public final static int WHITE = 4;
	public final static int BLACK = 5;
	 
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
			
			while (iterator != solutionSize && solved == true){
				//Set the guessed values, assume 0 is not a choosable colour.
				//You mean you assume that you can't choose no colours
				guesses[currGuess][iterator] = theGuess.get(iterator);
				iterator++;
			}
			 
			iterator = 0;
			 
			while (iterator != solutionSize && solved == true){
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
		theGuess.add(colourSel, i);
	}
	
	//Adds to end of a guess
	public void addToEndGuess(Integer colourSel) {
		theGuess.add(colourSel);
	}
		
	//Clear the guess
	public void clearGuess() {
		theGuess = new ArrayList<Integer>();
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
		 while(iterator != solutionSize){
				 retGuess.add(guesses[oldGuess][iterator + solutionSize]);	
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
	
	//
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
}

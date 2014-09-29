import java.util.ArrayList;


public class MasterMindGame {
	ArrayList<Integer> finalSolution;
	//Guesses is a 2D array, size is [Amount of guesses] [Solution size * 2]. 
	//It stores the guessed colours as integers and
	//stores the correct/partially correct/wrong result as a 2, 1 and 0 respectively.
	public int [][] guesses;
	public int guessAmt;
	public int currGuess;
	public int solutionSize;
	//Constants
	public final static int RED = 1;
	public final static int GREEN = 2;
	public final static int BLUE = 3;
	public final static int YELLOW = 4;
	public final static int WHITE = 5;
	public final static int BLACK = 6;
	 
	//This code assumes colours cannot be repeated for the time being.
	public MasterMindGame(ArrayList<Integer> answer, int guessAmount){
		solutionSize = answer.size();
		guessAmt = guessAmount;
		finalSolution = new ArrayList<Integer>();
		finalSolution = answer;
		currGuess = 0;
		guesses = new int[guessAmount][solutionSize*2];
	}
	 
 
	public boolean guessCheck(ArrayList<Integer> guess){
		
		//Check if solved by assuming it is solved and then disproving.
		boolean solved = true;
		
		//Check for invalid guesses.
		//Probably should restrict user to the amount required
		if (guess.size() != solutionSize) {
			solved = false;
		}
			
		if (currGuess == guessAmt) {
			solved = false;
		}
		
		//Assume these include hints
		//Rondo's code
		
		int iterator = 0;
		
		while (iterator != solutionSize){
			//Set the guessed values, assume 0 is not a choosable colour.
			//You mean you assume that you can't choose no colours
			guesses[currGuess][iterator] = guess.get(iterator);
			iterator++;
		}
		 
		iterator = 0;
		 
		while (iterator != solutionSize){
			//Check if the guess is the right colour AND position. Then check for the colour alone.
			if (guess.get(iterator)== finalSolution.get(iterator)){
				guesses[currGuess][iterator + solutionSize] = 2;
			} else if(finalSolution.contains(guess.get(iterator))){
				guesses[currGuess][iterator + solutionSize] = 1;
				solved = false;
			} else {
				solved = false;
				 
			}
					
			iterator++;
		}
		
		currGuess++;
		
		return solved;
	 }

	public int getMaxGuessAmt() {
		return guessAmt;
	}
	
	public int getCurGuessAmt() {
		return currGuess;
	}
}

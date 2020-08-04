package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  private int guessedIndex;
  private boolean endGame;
  private boolean[] candidateNumbers;
  private int[] priorGuesses;

  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    reset();
  }
  /**
   *  Resets data members and game state so we can play again.
   */
  
  public void reset() {
    guessedIndex = 0;
    guess = 1000;
    endGame = false;
    priorGuesses = new int[9000];
    candidateNumbers = new boolean[10000];
    
    //eliminates all numbers below 10000
    for (int i = 0; i < 1000; i++) {
      candidateNumbers[i] = true;
    } 
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    for (int guessed : priorGuesses) {
      if (n == guessed) {
        return true;
      } 
    }  
    return false;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    return guessedIndex; //getter accessor-method
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    int totalMatched = 0;
    
    //check if the first digit (thousands place) is a match
    if ((a / 1000) % 10 == (b / 1000) % 10) {
      totalMatched++;
    }

    //check if the second digit (hundreds place) is a match
    if ((a / 100) % 10 == (b / 100) % 10) {
      totalMatched++;
    }

    //check if the third digit (tens place) is a match
    if ((a / 10) % 10 == (b / 10) % 10) {
      totalMatched++;
    } 
    
    //check if the last digit (ones place) is a match
    if (a % 10 == b % 10) {
      totalMatched++;
    }
    return totalMatched; //returns the number matches in current guess
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    if (endGame == true) {
      return true;
    }
    if (guessedIndex > 9000) {
      return true;
    }
    return false;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * 
   * <p>Assigns current guess to the priorGuesses array at the 
   * updated index of total guesses made so far
   */
  public int getGuess() {
    guessedIndex++; 
    priorGuesses[guessedIndex] = guess;
    
    return guess;                        
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    boolean isCandidate = false;
    int range = 9999;
    
    if (nmatches == 4) {
      endGame = true; //when all digits are matched correctly - game ends (resets)
    } else {
      for (int i = 999; i <= range; i++) { //iterates range from 1000 - 9999 (9000)
        if (candidateNumbers[i] == false && numMatches(guess,i) != nmatches) {
          candidateNumbers[i] = true;
        } 
      }

      for (boolean eliminate : candidateNumbers) { 
        if (eliminate == false) { //checks candidates haven't been eliminated
          isCandidate = true;
        }
      }
      
        if (isCandidate == false) { //eliminate those that do not have the same number
          return false; // of matches with the player’s current guess
        }
      
      /**
       * Breaks out of the loop once reaches
       * a candidate that's doesn't share the
       * matches of the digits in the original guess
       * (resets) updateGuess
       */
      for (int i = 999; i <= range; i++) { 
        if (candidateNumbers[i] == false) { 
          guess = i; 
          i = 10001;
        }
      }
    }
    return true;
  }
  
  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    int[] copiedGuesses = new int[guessedIndex]; //length of array is determined by
                                                 //the guesses made so far
    if (guessedIndex > 0) {
      int count = 0;
      for (int matchedGuess : priorGuesses) { 
        if (matchedGuess > 999) { //check if guess falls within range of 4 digit numbers
          copiedGuesses[count] = matchedGuess; //copy only if it is a valid guess
          count++;
        }
      }
      return copiedGuesses;
    } else {
      return null;
    }
  }
}

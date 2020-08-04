package guessme;
/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {

  // TODO: declare data members as necessary
  private int totalGuesses;
  private int guess; 
  private boolean endGame;
  private LLIntegerNode priorGuesses;
  private LLIntegerNode candidateNumbers;

  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

   /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, and do NOT add
   * new files (as they will be ignored by the autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
    reset();
  }

  /** Resets data members and game state so we can play again.
   * 
   */
   public void reset() {
     totalGuesses = 0;
     guess = 1000;
     endGame = false;
     priorGuesses = new LLIntegerNode(1000, null);
     candidateNumbers = new LLIntegerNode(1000, null);
     
     LLIntegerNode curNode = candidateNumbers;
     while(curNode.getLink() != null) {
       curNode = curNode.getLink();
     }
     
     for(int i = 1001; i <= 9999; i++) {
       LLIntegerNode node = new LLIntegerNode(i, null);
       curNode.setLink(node);
       curNode = node;
     }
   }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
   public boolean isPriorGuess(int n) {
     LLIntegerNode curNode = priorGuesses;
     while(curNode != null) {
       if(n == curNode.getInfo()) {
         return true;
       } 
        curNode = curNode.getLink();
     }
     return false;
   }

  /** Returns the number of guesses so far.
   * 
   */
   public int numGuesses() {
      return totalGuesses;
   }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
   public static int numMatches(int a, int b) {
     int matches = 0;
     
     if((a/1000) % 10 == (b/1000) % 10) {
       matches++;
     }
     
     if((a/100) % 10 == (b/100) % 10) {
       matches++;
     }
     
     if((a/10) % 10 == (b/10) % 10) {
       matches++;
     }
     
     if(a % 10 == b % 10) {
       matches++;
     }
     return matches;
   }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if no candidate is left.
   */
   public boolean isOver() {
     if(totalGuesses > 9000) {
       return true;
     }
     if(endGame == true) {
       return true;
     }
     return false;
   }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */
   public int getGuess() {
     if(totalGuesses != 0) {
       LLIntegerNode curNode = priorGuesses;
       while(curNode.getLink() != null) {
         curNode = curNode.getLink();
       }
       LLIntegerNode node = new LLIntegerNode(guess, null);
       curNode.setLink(node);
     }
     totalGuesses++;
     return guess;
   }
  
  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
   public boolean updateGuess(int nmatches) {
     if (nmatches == 4) {
         endGame = true;
     } else {
       LLIntegerNode head = new LLIntegerNode(0, null);
       LLIntegerNode tail = head;

       LLIntegerNode curNode = candidateNumbers;
       while (curNode != null) {
         if (numMatches(guess, curNode.getInfo()) == nmatches) {
           LLIntegerNode newTemp = new LLIntegerNode(curNode.getInfo(), null);
           tail.setLink(newTemp);
           tail = tail.getLink();
         }
         curNode = curNode.getLink();
       }
       if (head.getLink() == null) {
           return false;
       }
       candidateNumbers = head.getLink();
       guess = candidateNumbers.getInfo();
     }
     return true;
   }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
   public LLIntegerNode priorGuesses() {
     if(totalGuesses == 0) {
       return null;
     }
     return priorGuesses;
   }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
   public String priorGuessesString() {
     String guessString = "";
     if(totalGuesses == 0) {
       return guessString;
     }
     
     LLIntegerNode curNode = priorGuesses;
     guessString = curNode.getInfo() + "";
     curNode = curNode.getLink();
     while(curNode != null) {
       guessString += ", " + curNode.getInfo();
       curNode = curNode.getLink();
     }
     return guessString;
   }
}

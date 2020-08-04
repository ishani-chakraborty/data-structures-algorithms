package fizzbuzz;
/*
 * 
 * @author Ishani Chakraborty
 * @date 01.23.2020
 * 
 * Group word game that incorporates rules about division and
 * counting games as well
 */

public class FizzBuzz {
  private final int fizzNumber;
  private final int buzzNumber;

  /**
   * Construct an object that can compute fizzbuzz values for a game of 
   * Fizz and Buzz.
   * 
   * @param fizzNumber an integer between 1 and 9
   * @param buzzNumber an integer between 1 and 9
   */
  public FizzBuzz(int fizzNumber, int buzzNumber) {
    this.fizzNumber = fizzNumber;
    this.buzzNumber = buzzNumber;
  }

  public boolean checkContainsDigit(int digit, int number) { 
    while( number > 0 ) { //initial condition: must contain at least single digit positive integer
      if ( number % 10 == digit) { //checks if that digit exists in the number to check if it can 
        //be divisible by fizz number, buzz number, both
        return true; //returns true if the number contains the digit
      }
      number = number / 10; //divide by 10 to traverse through the 1's, 10's, and 100's 
      //place in the number (integer)
    }
    return false; //returns false if digit doesn't exist in the number
  }

  /**
   * Returns the fizzbuzz value for n. The rules are:
   * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
   * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
   * - however, if both the above conditions are true, you must return "fizzbuzz"
   * - if none of the above conditions is true, return the number itself.
   *
   * <p>For example, getValue(1) returns "1".
   * 
   * @param n a positive integer
   * @return the fizzbuzz value, as a String
   */
  public String getValue(int n) {
    if((n % fizzNumber == 0 || checkContainsDigit(fizzNumber, n)) && (n % buzzNumber == 0 || checkContainsDigit(buzzNumber, n))) {
      return "fizzbuzz";
    }
    if(n % fizzNumber == 0 || checkContainsDigit(fizzNumber, n)) {
      return "fizz";
    }
    if (n % buzzNumber == 0 || checkContainsDigit(buzzNumber, n)) {
      return "buzz";
    }
    return Integer.toString(n); // return the number itself as a String
  }
  /**
   * Returns an array of the fizzbuzz values from start to end, inclusive.
   * 
   * <p>For example, if the fizz number is 3 and buzz number is 4,
   * getValues(2,6) should return an array of Strings:
   * 
   * <p>{"2", "fizz", "buzz", "5", "fizz"}
   * 
   * @param start
   *            the number to start from; start > 0
   * @param end
   *            the number to end at; end >= start
   * @return the array of fizzbuzz values
   */
  public String[] getValues(int start, int end) {
    int size = (end - start) + 1;
    int counter = 0;
    String [] fizzbuzzValues = new String[size];

    while(start <= end){ 
      fizzbuzzValues[counter] = getValue(start);
      start++;
      counter++;
    }
    return fizzbuzzValues;
  }
}

package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
  
  private int num;
  private LLIntegerNode link;
    
  public LLIntegerNode(int num, LLIntegerNode link) {
    this.num = num;
    this.link = link;
  }
  
  public void setInfo(int num) {
    this.num = num;
  }
  
  public void setLink (LLIntegerNode link) {
    this.link = link;
  }
 
  public int getInfo() {
    return num;
  }

  public LLIntegerNode getLink() {
     return link;
  }
}


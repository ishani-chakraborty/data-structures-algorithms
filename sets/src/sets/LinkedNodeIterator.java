package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeIterator<E> implements Iterator<E> {
  public LinkedNode<E> curr;
  
  public LinkedNodeIterator(LinkedNode<E> head) {
    this.curr = head;
  }

  @Override
  public boolean hasNext() {
    return (curr != null);
  }

  @Override
  public E next() {
    if (curr != null) {
      E data = curr.getData();
      curr = curr.getNext();
      return data;
    }
    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}

package structures;

import java.util.*;

public class RecursiveList<T> implements ListInterface<T>{
  public int size = 0;
  public int index = 0;
  public LLNode<T>head;
  public LLNode<T>tail;
  public LLNode<T>prev;
  
  class LLNode<T>{
    public T data;
    public LLNode<T> next;
    
    public LLNode(T data) { 
      this.data = data;
    }
    
    public LLNode(T data, LLNode<T> next) {
      this.data = data; 
      this.next = next;
    }
    
    public void setData(T data) {
      this.data = data;
    }
    
    public void setLink (LLNode<T> next) {
      this.next = next;
    }
   
    public T getData() {
      return data;
    }

    public LLNode<T> getLink() {
       return next;
    }
  }
  
  
  class Iterator<T>{
    public LLNode<T> head;
    public Iterator<T> iterator;
    public Iterator() {
      iterator = new Iterator<T>();
      head = new LLNode<T>(head.data, null);
    }
    public boolean hasNext() {
      return false;
    }
    
   // public T next() {
     // if(hasNext() == false) {
     //   throw new NoSuchElementException();
     // }
      
   // }
    
    //public void remove() {
    //  throw new UnsupportedOperationException();
   // }
  }
  
  public RecursiveList(){
    head = new LLNode<T>(head.data, null);
    tail = new LLNode<T>(tail.data, null);
    prev = new LLNode<T>(prev.data, head);
    index = 0;
    size = 0;
  }
  
  @Override
  public int size() {
    if(head == null) {
      throw new NullPointerException();
    }
    return size;
  }

  @Override
  public ListInterface<T> insertFirst(T elem) {
    if(isEmpty())
      throw new IndexOutOfBoundsException();
    return insertAt(0, elem);
  }

  @Override
  public ListInterface<T> insertLast(T elem) {
    return insertAt(size - 1, elem);
  }

  @Override
  public ListInterface<T> insertAt(int index, T elem) {
    if(index > size) {
      throw new IllegalStateException();
    }
    
    if(index == 0) {
      
    }
      prev = findNode(index - 1, 0, head);
      LLNode<T> node = new LLNode<T>(elem, prev.getLink());
      prev.setLink(node);
      size++;
      return this;
  }

  @Override
  public T removeFirst() {
    if(isEmpty()) {
      throw new IllegalStateException();
    }
    return removeAt(0);
  }

  @Override
  public T removeLast() {
    if(index < 0 || index >= size) {
      throw new IllegalStateException();
    }
    return removeAt(size - 1);
  }

  @Override
  public T removeAt(int i) {
    if(i == 0) {
      removeFirst();
    } else {
      prev = findNode(i - 1, 0, head);
      prev.setLink(prev.getLink().getLink());
      size--;
    }
    return prev.data;
  }

  @Override
  public T getFirst() {
    return get(0);
  }

  @Override
  public T getLast() {
    return get(size - 1);
  }

  @Override
  public T get(int i) {
    return findNode(i, 0, head).getData();
  }

  @Override
  public boolean remove(T elem) {
     int i = indexOf(elem);
     if(head == null) {
       throw new NullPointerException();
     }
     if(i == -1)
       return false;
     else
        removeAt(i);
     return true;
  }

  @Override
  public int indexOf(T elem) {
    return indexOfHelper(elem, index, head);
  }
  
  public int indexOfHelper(T elem, int index, LLNode<T> curNode) {
    if(curNode == null)
      return -1;
    if(curNode.data == elem)
      return index;
    return indexOfHelper(elem, index + 1, curNode.next);
  }

  @Override
  public boolean isEmpty() {
    return (head == null);
  }
  
  public LLNode<T> findNode(int index, int count, LLNode<T> curr) {
    if(index == count) {
      return curr;
    } else {
      return findNode(index, count + 1, curr.next);
    }
  }

  @Override
  public java.util.Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return null;
  }
}

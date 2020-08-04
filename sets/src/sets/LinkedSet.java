package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;
  
  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    int size = 0;
    Iterator<E> i = iterator();
    while (i.hasNext()) {
      i.next();
      size++;
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return (head == null);
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    Iterator<E> i = iterator();
    while (i.hasNext()) {
      if (i.next().equals(o))
        return true;
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    Iterator<E> i = iterator();
    while (i.hasNext()) {
      if (!(that.contains(i.next()))) 
        return false;
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    return (that.isSubset(this));
  }

  @Override
  public Set<E> adjoin(E e) {
    if (contains(e)) 
      return this;
    else {
      LinkedNode<E> headNode = new LinkedNode<E>(e,head);
      LinkedSet<E> combined = new LinkedSet<E>(headNode);
      return (combined);
    }
  }

  @Override
  public Set<E> union(Set<E> that) {
    Iterator<E> i = that.iterator();
    Set<E> bothSets = new LinkedSet<E>(head);
    while (i.hasNext()) 
      bothSets = bothSets.adjoin(i.next());
    return bothSets;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    LinkedNode<E> headNode = null;
    Set<E> intersectedSets = new LinkedSet<E>(headNode);
    Iterator<E> i = iterator();
    while (i.hasNext()) {
      E data = i.next();
      if (that.contains(data)) 
        intersectedSets = intersectedSets.adjoin(data);
    }
    return intersectedSets;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    LinkedNode<E> headNode = null;
    Set<E> diffSet = new LinkedSet<E>(headNode);
    Iterator<E> i = this.iterator();
    while (i.hasNext()) {
      E data = i.next();
      if (!(that.contains(data))) 
        diffSet = diffSet.adjoin(data);
    }
    return diffSet;
  }

  @Override
  public Set<E> remove(E e) {
    LinkedNode<E> newHead = null;
    Set<E> reducedSet = new LinkedSet<E>(newHead);
    Iterator<E> i = iterator();
    if (contains(e)) {
      while (i.hasNext()) {
        E data = i.next();
        if (!(data.equals(e))) 
          reducedSet = reducedSet.adjoin(data);
      }
      return reducedSet;
    }
    return this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (!(o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>) o;
    return (isSubset(that) && that.isSubset(this));
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}

package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO
    return get(t) != null;
  }

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  public BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO
    if (t == null) {
      throw new NullPointerException();
    }
    return get(t, root);
  }
  
  private T get(T elem, BSTNode<T> node) {
    if (node == null) 
      return null;
    int result = elem.compareTo(node.getData());
    if (result == 0) 
      return node.getData();
    else if (result < 0) 
      return get(elem, node.getLeft());
    else 
      return get(elem, node.getRight());
  }

  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    if (root == null) 
      return null;
    return getMinimum(root);
  }


  private T getMinimum(BSTNode<T> node) {
    if (node.getLeft() == null) {
      return node.getData();
    } else {
      return getMinimum(node.getLeft());
    }
  }

  @Override
  public T getMaximum() {
    if (root == null) 
      return null;
    return getHighestValue(root);
  }


  @Override
  public int height() {
    return height(root);
  }


  private int height(BSTNode<T> node) {
    if (node == null)
      return -1;
    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }

  public Iterator<T> preorderIterator() {
    Queue<T> q = new LinkedList<T>();
    preorderTraverse(q, root);
    return q.iterator();
  }


  private void preorderTraverse(Queue<T> q, BSTNode<T> node) {
    if (node != null) {
      q.add(node.getData());
      preorderTraverse(q, node.getLeft());
      preorderTraverse(q, node.getRight());
    }
  }

  /**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    postorderTraverse(queue, root);
    return queue.iterator();
  }


  private void postorderTraverse(Queue<T> q, BSTNode<T> node) {
    if (node != null) {
      postorderTraverse(q, node.getLeft());
      postorderTraverse(q, node.getRight());
      q.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    return equals(root, other.getRoot());
  }


  private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
    if (node1 == null && node2 == null) 
      return true;
    else if (node1 == null || node2 == null) 
      return false;
    else {
        if (!node1.getData().equals(node2.getData())) 
          return false;
     return equals(node1.getLeft(), node2.getLeft()) && equals(node1.getRight(), node2.getRight());
    }
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    Iterator<T> i1 = this.inorderIterator();
    Iterator<T> i2 = other.inorderIterator();
    while (i1.hasNext() && i2.hasNext()) 
        if (!i1.next().equals(i2.next()))
            return false;
    return (!i1.hasNext() && !i2.hasNext());
  }

  @Override
  public boolean isBalanced() {
    return Math.pow(2, height()) <= size() && size() < Math.pow(2, height() + 1);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
    Iterator<T> iterator = inorderIterator();
    T[] arr = (T[]) new Comparable[size()];
    int index = 0;
    while (iterator.hasNext()) {
        arr[index] = iterator.next();
        index++;
    }
    root = sortedArray2BST(arr, 0, arr.length-1);
  }


  private BSTNode<T> sortedArray2BST(T[] arr, int lower, int upper) {
    if (lower > upper) 
      return null;
    int middle = (lower + upper)/2;
    BSTNode<T> newNode = new BSTNode<T>(arr[middle], sortedArray2BST(arr, lower, middle-1), sortedArray2BST(arr, middle+1, upper));
    return newNode;
  }

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> "
            + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> "
            + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }

  /**
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;

  @Override
  public void add(T t) {
    if(t == null) {
      throw new NullPointerException();
    }
    upperBound++;
    BSTNode<T> node = new BSTNode<T>(t, null, null);
    root = addToSubtree(root, node);
    if(height() > Math.log(upperBound) / Math.log((double)3/2)) {
      BSTNode<T> childNode = node;
      BSTNode<T> parentNode = childNode.parent;
      while ((double)subtreeSize(childNode)/ subtreeSize(parentNode) <= (double)2/3) {
        parentNode = parentNode.parent;
        childNode = childNode.parent;
      }
      ScapegoatTree<T> subtree = new ScapegoatTree<T>();
      subtree.root = parentNode;
      BSTNode<T> firstParent = parentNode.parent;
      subtree.balance();
      if (firstParent.getLeft() == parentNode) firstParent.setLeft(subtree.root);
      else firstParent.setRight(subtree.root);
    }
  }

  @Override
  public boolean remove(T element) {
  
    if (element == null) {
      throw new NullPointerException();
    }
    boolean hasNode = contains(element);
    if (hasNode) {
      root = removeFromSubtree(root, element);
  }
  if (upperBound > size() * 2) {
      balance();
      upperBound = size();
  }
  return hasNode;
  }
}

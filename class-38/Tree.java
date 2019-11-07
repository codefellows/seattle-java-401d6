import java.util.*;

public class Tree {
  int value;
  Tree left;
  Tree right;

  public Tree() {}
  public Tree(int value) {
    this.value = value;
  }

  public Tree(int value, Tree left, Tree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static void main(String[] args) {
    Tree t = new Tree(1, new Tree(2, new Tree(1), new Tree(1)), new Tree(3, new Tree(1), new Tree(1)));
    System.out.println(isBalanced(t));
    Tree unbalanced = new Tree(1, new Tree(1, new Tree(1), null), null);
    System.out.println(isBalanced(unbalanced));
  }

  public static boolean isBalanced(Tree input) {
    if (input == null) {
      return true;
    }
    else {
      return isBalanced(input.left) &&
        isBalanced(input.right) &&
        Math.abs(height(input.left) - height(input.right)) < 2;
    }
  }

  public static int height(Tree input) {
    if (input == null) return 0;
    return 1 + Math.max(height(input.left), height(input.right));
  }
}

import java.util.*;

public class Tree {
  public static void main(String[] args) {
    //      10
    //     /  \
    //    5   15
    //    /\   /\
  //     2 7  12 17
    TreeNode root1 = new TreeNode(10,
      new TreeNode(5, new TreeNode(2), new TreeNode(7)),
      new TreeNode(15, new TreeNode(12), new TreeNode(17))
    );
    TreeNode root2 = new TreeNode(5, new TreeNode(0), new TreeNode(2));

    // intersection should be [5, 2]
    System.out.println(intersect(root1, root2));
  }

  public static void addValuesToMap(TreeNode root, HashMap<Integer, Boolean> map) {
    if (root != null) {
      map.put(root.value, true);
      addValuesToMap(root.left, map);
      addValuesToMap(root.right, map);
    }
  }

  public static void addValueToSetIfInMap(TreeNode root, HashMap<Integer, Boolean> map, Set<Integer> answer) {
    if (root != null) {
      if(map.containsKey(root.value)) {
          answer.add(root.value);
      }
      addValueToSetIfInMap(root.left, map, answer);
      addValueToSetIfInMap(root.right, map, answer);
    }
  }

  public static Set<Integer> intersect(TreeNode root1, TreeNode root2) {
      HashSet<Integer> answer =  new HashSet<Integer>();

      HashMap<Integer, Boolean> valuesFromRoot1 = new HashMap<>();
      addValuesToMap(root1, valuesFromRoot1);
      addValueToSetIfInMap(root2, valuesFromRoot1, answer);
      return answer;
  }
}

class TreeNode {
  public int value;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int value, TreeNode left, TreeNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public TreeNode(int value) {
    this.value = value;
  }
}

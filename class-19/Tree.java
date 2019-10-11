import java.util.*;

public class Tree<T> {
    Node<T> root;
    public static void main(String[] args) {
        //    A
        //   / \
        //  B   C
        // / \   \
        //D   E   F 
        //     \
        //      G
        Node<String> root = new Node<>(
            "A",
            new Node<>("B", new Node<>("D"), new Node<>("E", null, new Node<>("G"))),
            new Node<>("C", null, new Node<>("F"))
        );
        Tree<String> actuallyATree = new Tree<>();
        actuallyATree.root = root;
        System.out.println(breadthFirstSearch(actuallyATree));
    }

    public int findMax(Node<Integer> root) {
        if(root == null) {
            throw new IllegalArgumentException("node shouldn't be null!");
        }
        int answer = root.value;
        if(root.left != null) {
            answer = Math.max(answer, findMax(root.left));
        }
        if (root.right != null) {
            answer = Math.max(answer, findMax(root.right));
        }
        return answer;
    }

    //     4      => 1, or       5      => 2
    //    /                     / \
    //   5                     2   1

    // if we get to where root is null, there's not a leaf there, return 0
    // if we get to a leaf, count it, return 1
    // otherwise, figure out how many leaves on each side and add them up

    public int countLeaves(Node<T> root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return countLeaves(root.left) + countLeaves(root.right);
        }
    }
    public int sum(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        return sum(root.left) + sum(root.right) + root.value;
    }
    public int findMax(Node<Integer> root) {
        if(root == null) {
            throw new IllegalArgumentException("node shouldn't be null!");
        }
        Queue<Node<Integer>> nodesToProcess = new LinkedList<>();
        int max = root.value;
        nodesToProcess.add(root);
        while(!nodesToProcess.isEmpty()) {
            Node<Integer> currentNode = nodesToProcess.remove();
            if (currentNode.value > max) {
                max = currentNode.value;
            }
            if(currentNode.left != null) {
                nodesToProcess.add(currentNode.left);
            }
            if(currentNode.right != null) {
                nodesToProcess.add(currentNode.right);
            }
        }
        return max;
    }

    public List<T> breadthFirstSearch() {
        return breadthFirstSearch(this.root);
    }
    public static <T> List<T> breadthFirstSearch(Node<T> root) {
        Queue<Node<T>> nodesToProcess = new LinkedList<>();
        List<T> answer = new LinkedList<>();
        // start with the root node as the node to process
        nodesToProcess.add(root);
        // as long as there are still nodes to process, process them
        while (!nodesToProcess.isEmpty()) {
            Node<T> currentNode = nodesToProcess.remove();
            // add current node's value to answer
            answer.add(currentNode.value);
            // plan to process the left and the right nodes
            if (currentNode.left != null) {
                nodesToProcess.add(currentNode.left);
            }
            if (currentNode.right != null) {
                nodesToProcess.add(currentNode.right);
            }
        }
        return answer;
    }
}

class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(T value) {
        this(value, null, null);
    }

    public Node () {}
}

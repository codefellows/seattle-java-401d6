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

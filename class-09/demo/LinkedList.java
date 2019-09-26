import java.util.StringJoiner;

public class LinkedList {
    Node head;

    public static void main(String[] args) {
        // countdown(5);
        LinkedList list = new LinkedList();
        list.insert(3);
        list.insert(2);
        list.insert(1);
        System.out.println(list.size() + " (should be 3)");

        LinkedList list2 = new LinkedList();
        list2.insert(14);
        list2.insert(13);
        list2.insert(12);
        list2.insert(11);
        System.out.println(list2.size() + " (should be 4)");

        LinkedList list3 = LinkedList.merge(list, list2);
        System.out.println(list3);
    }

    public int size() {
        return size(this.head);
    }

    // private: I can only use this method inside of this file/class
    // if I imported LinkedList into another file, I couldn't do
    // LinkedList myList = new LinkedList();
    // myList.size(myList.head); // exception, method is private
    private int size(Node n) {
        // base case: if there are no nodes left to look at, the size is 0
        if (n == null) {
            return 0;
        } else {
            // recursive case: find the size of the rest of the list and add 1
            int sizeOfNodeN = 1;
            int sizeOfRestOfListAfterNodeN = size(n.next);
            return sizeOfNodeN + sizeOfRestOfListAfterNodeN;
        }
    }

    public void insert(int value) {
        this.head = new Node(value, this.head);
    }

    public String toString() {
        StringJoiner answer = new StringJoiner("->");
        Node current = head;
        while(current != null) {
            answer.add("" + current.value);
            current = current.next;
        }
        return answer.toString();
    }

    public static LinkedList merge(LinkedList list1, LinkedList list2) {
        // if (list1.head == null) {
        //     return list2;
        // }
        // Node current1 = list1.head;
        // Node current2 = list2.head;
        // while (current1 != null && current2 != null) {
        //     Node temp = current1.next;
        //     Node temp2 = current2.next;
        //     if (current1.next != null) {
        //         current2.next = current1.next;
        //     }
        //     current1.next = current2;
        //     current1 = temp;
        //     current2 = temp2;
        // }
        // return list1;
        LinkedList answer = new LinkedList();
        answer.head = mergeBetter(list1.head, list2.head);
        return answer;
    }

    private static Node merge(Node head1, Node head2) {
        // base case: I'm already done
        // 2 base cases for our 2 params: if either of them is null, we're done
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        // neither was null
        // recursive case
        // recursively call merge on the two nexts
        head2.next = merge(head1.next, head2.next);
        head1.next = head2;
        return head1;
    }

    private static Node mergeBetter(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        } else {
            head1.next = mergeBetter(head2, head1.next);
            return head1;
        }
    }


    public static void reallyBadRecursion() {
        // for this method to be recursive, it has to call itself
        reallyBadRecursion();
    }

    public static void countdown(int n) {
        if (n > 0) {
            // recursive case: there's still work to do!

            // take care of the current value
            System.out.println(n);
            // recursively call the method to solve a slightly smaller version of the same problem
            countdown(n-1);
        } else {
            // base case: nothing left to do, we already know the answer
            // we DO NOT call the method recursively
        }
    }
}

import java.util.StringJoiner;

public class LinkedList {
    Node head;

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(3);
        list.insert(2);
        list.insert(1);

        LinkedList list2 = new LinkedList();
        list2.insert(14);
        list2.insert(13);
        list2.insert(12);
        list2.insert(11);

        LinkedList list3 = LinkedList.merge(list, list2);
        System.out.println(list3);
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
        if (list1.head == null) {
            return list2;
        }
        Node current1 = list1.head;
        Node current2 = list2.head;
        while (current1 != null && current2 != null) {
            Node temp = current1.next;
            Node temp2 = current2.next;
            if (current1.next != null) {
                current2.next = current1.next;
            }
            current1.next = current2;
            current1 = temp;
            current2 = temp2;
        }
        return list1;
    }
}

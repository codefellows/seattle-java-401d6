import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

public class Hashtable<Key, Value> {

    // Array of buckets with LinkedLists at each index
    protected LinkedList<HashNode<Key, Value>>[] hashtable;

    // Current capacity of array
    public int hashtableNumberOfBuckets = 10;

    // Constructor for hashtable array of defined length with linkedlists
    public Hashtable() {
        this.hashtable = new LinkedList[hashtableNumberOfBuckets];
    }

    // **************** Hash Method ***************

    // This implements the hash function to calculate the index for the key
    protected int hash(Key key) {

        String keyAsString = key.toString();
        int keySumOfASCIIValues = 0;

        for (int i = 0; i < keyAsString.length(); i++) {
            keySumOfASCIIValues += keyAsString.charAt(i);
        }

        int hashtableLength = hashtable.length;
        int index = (keySumOfASCIIValues * 599) % hashtableLength;

        return index;
    }

    // **************** Add Method ***************

    // Adds a key value pair to hashtable
    public boolean add(Key key, Value value) {

        if (contains(key)) {
            return false;
        }

        HashNode dataToStore = new HashNode(key, value);

        int index = hash(key);

        if (this.hashtable[index] == null) {
            this.hashtable[index] = new LinkedList<>();
        }

        this.hashtable[index].add(dataToStore);

        return true;
    }

//        // If the load factor goes beyond the set threshold, double the hash table size
//        if ((1.0 * bucketArraySize)/bucketArrayNumberOfBuckets >= 0.7) {
//            LinkedList<HashNode> temp = bucketArray;
//            bucketArray = new LinkedList<>();
//            bucketArrayNumberOfBuckets = 2 * bucketArrayNumberOfBuckets;
//            bucketArraySize = 0;
//
//            for (int i = 0; i < bucketArrayNumberOfBuckets; i++) {
//                bucketArray.add(null);
//            }
//
//            for (HashNode headNode : temp) {
//                while (headNode != null) {
//                    add(headNode.key, headNode.value);
//                    headNode = headNode.next;
//                }
//            }
//        }
//    }

    // **************** Get Method ***************

    // Returns value for a key
    public Value get(Key key) {

        int index = hash(key);
        if (hashtable[index] == null) {
            return null;
        }

        if (hashtable[index] == null) {
            return null;
        }

        LinkedList<HashNode<Key, Value>> indexsLinkedList = hashtable[index];

        for (HashNode storedData : indexsLinkedList){

            if (key.equals(storedData.getKey())){
                return (Value)storedData.getValue();
            }
        }

        return null;
    }

    // **************** Contains Method ***************

    // Assess whether the specified key is stored in the table.

    public boolean contains(Key key){
        int index = hash(key);

        if (hashtable[index] == null) {
            return false;
        }

        LinkedList<HashNode<Key, Value>> indexsLinkedList = hashtable[index];

        for (HashNode storedThing : indexsLinkedList){

            if (key.equals(storedThing.getKey())){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Hashtable{ LinkedList = " + Arrays.toString(hashtable) + "}";
    }

    public static ArrayList<String[]> leftJoin(Hashtable<String, String> table1, Hashtable<String, String> table2) {
      // go through everyting in table1
      // add it to answer, possibly also with data from table2
      ArrayList<String[]> ans = new ArrayList<>();
      for(LinkedList<HashNode<String, String>> bucket : table1.hashtable) {
        if (bucket != null) {
          for(HashNode<String, String> node : bucket) {
            ans.add(new String[]{
              node.getKey(), 
              node.getValue(), 
              table2.contains(node.getKey()) ? table2.get(node.getKey()) : null
              // ternary is WTF (what, true, false)
            });
              
          }
        }
        
      }
      return ans;
    }

    public static void main(String[] args) {
      Hashtable<String,String> table = new Hashtable<>();
      table.add("hello", "world");
      table.add("leaf", "green");
      table.add("water", "bottled");

      Hashtable<String, String> table2 = new Hashtable<>();
      table2.add("hello", "everyone");
      table2.add("water", "spring");
      table2.add("something", "silly");

      ArrayList<String[]> ans = leftJoin(table, table2);
      for (String[] arr : ans) {
        System.out.println(Arrays.toString(arr));
      }
    }
}

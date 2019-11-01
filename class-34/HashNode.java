public class HashNode<Key, Value> {

    private Key key;
    private Value value;

    //Constructor
    public HashNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public HashNode() {};

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HashNode{ key = " + key + ", value = " + value + "}";
    }

}

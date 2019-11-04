import java.util.*;

public class Stringy {
  public static void main(String[] args) {
    String s = "seattle-java-401d6";
    HashMap<Character, Integer> counts = new HashMap<>();
    char mostFreq = s.charAt(0);
    // silly: splitting that into an array of single characters
    // less silly: just iterating through each character
    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);
      int times = 1;
      if(counts.containsKey(current)) {
        times += counts.get(current);
      }
      counts.put(current, times);

      if(times > counts.get(mostFreq)) {
        mostFreq = current;
      }
    }
    System.out.println(mostFreq);
  }
}

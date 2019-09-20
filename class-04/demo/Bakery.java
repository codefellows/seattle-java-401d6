public class Bakery {
  public static void main( String[] args){
    // short s = 20;
    Danish cheesy = new Danish("cheese", "round", (short) 20);
    System.out.println(cheesy);
    System.out.println(cheesy.toString() + cheesy.toString() + cheesy.toString());

    System.out.println(Danish.yumminess);

    System.out.println(Danish.getDanishBaker());
  }

  // String.format
  // it is public
  // it is a method accessed on String
  // this means it is a class method, it was created with the word static
}
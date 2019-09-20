
public class Danish {
  
  // Class variables and methods can be accessed before a single instance of the class has been made
  

  // Static means a property belongs to the class "Class"
  static String yumminess = "delicious";

  static String[] bakers = {"James", "Ginger", "Paula", "Michelle"};

  static String getDanishBaker(){
    int x = (int) (Math.random() * bakers.length);
    return bakers[x];
  }


  // Pick out the things unique about the danish

  String flavor;
  String shape;
  // Integer would be decent
  Short cookTime;

  // public static Type NameOfMethod(params)
  // Constructor will just have two pieces: public nameofClass/nameOfInstance
  public Danish (String flavor, String shape, Short cookTime) {
    this.flavor = flavor;
    this.shape = shape;
    this.cookTime = cookTime;
  }


  // Instance variables and methods can only be accessed from a instantiated instance
  public String toString(){
    // This is a round, cheesy Danish that has been cooked for 600 time units
    return String.format("This is a %s, %s Danish that has been cooked for %d", this.shape, this.flavor, this.cookTime);
  }
}
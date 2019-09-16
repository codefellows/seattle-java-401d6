// must import even things built into Java other than the absolute basics
import java.text.SimpleDateFormat;
import java.util.Calendar;

// File name matches EXACTLY with class name
// public class FileNameExactly
public class Basics {

    // running a file means running the main method
    // the main method ALWAYS looks EXACTLY like this
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
        // getting month help from https://stackoverflow.com/questions/14832151/how-to-get-month-name-from-calendar
        Calendar cal = Calendar.getInstance();
        String currentMonth = new SimpleDateFormat("MMMM").format(cal.getTime());
        System.out.println(isItSalmonSeason(currentMonth));

        String myName = "Michelle";

        // option 1 for creating an array: give it a size, fill in the data
        // String[] names = new String[3];
        // names[0] = "Michelle";
        // names[1] = "Nicholas";
        // names[2] = "Jeff";

        // option 2: use curly braces to specify the data inside of the array
        String[] names = new String[]{"Michelle", "Nicholas", "Jeff"};

        // the loops are REALLY the same as JS, just with int instead of var
        for(int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        System.out.println(i);
    }
    
    // writing a method
    // for now, they always start with "public static"
    // then, return type (or void)
    // then, name of the method
    public static boolean isItSalmonSeason(String month) {
        // salmon season runs July through September
        // == works for primitives (float, byte, double, char, long, short, boolean, int)
        // .equals works well for objects (everything else)
        if(month.equals("July") || month.equals("August") || month.equals("September")) {
            return true;
        } else {
            return false;
        }
    }

}

package demo;

// it doesn't say "extends Object", but it might as well
// everything extends from Object, even if we don't want that.
public class Cat {
    boolean hasClaws;
    double weightInKilos;
    int friendliness;


    public Cat() {}

    public Cat(boolean hasClaws, double weightInKilos, int friendliness) {
        this.hasClaws = hasClaws;
        this.weightInKilos = weightInKilos;
        this.friendliness = friendliness;
    }

    public void meow() {
        if(this.friendliness > 5) {
            System.out.println("meow");
        } else {
            System.out.println("hiss");
        }
    }

    // is overriding the basic toString method that's in the Object class
    // the annotation is OPTIONAL, does not actually make it override
    // the annotation lets you know if you THINK you're overriding something, but you're not
    @Override
    public String toString() {
        return String.format("A cat that is %d friendly", this.friendliness);
    }
}

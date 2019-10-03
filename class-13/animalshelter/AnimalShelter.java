import java.util.*;
import java.time.*;
public class AnimalShelter {
    private LinkedList<Dog> dogQueue = new LinkedList<>();
    private LinkedList<Cat> catQueue = new LinkedList<>();

    public void enqueue(Dog d) {
        d.setTimestamp(LocalDateTime.now());
        this.dogQueue.addLast(d);
    }

    public void enqueue(Cat c) {
        c.setTimestamp(LocalDateTime.now());
        this.catQueue.addLast(c);
    }

    public Animal dequeue() {
        if(this.catQueue.isEmpty()) {
            return this.dogQueue.removeFirst();
        } else if (this.dogQueue.isEmpty()) {
            return this.catQueue.removeFirst();
        } else if (this.dogQueue.get(0).getTimestamp().isBefore(this.catQueue.get(0).getTimestamp())) {
            return this.dogQueue.removeFirst();
        } else {
            return this.catQueue.removeFirst();
        }
    }
    // <T> : This method is generic. Whatever type you pass in, that's what it will return.
    public <T> T dequeue(Class<T> type) {
        if (type == Dog.class) {
            return (T)this.dogQueue.removeFirst();
        } else if (type.equals(Cat.class)) {
            return (T)this.catQueue.removeFirst();
        } else {
            throw new IllegalArgumentException("This shelter can only provide dogs and cats!");
        }
    }


    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();
        Dog d = new Dog("Rosie");
        shelter.enqueue(d);
        shelter.enqueue(new Cat("Teddie"));
        shelter.enqueue(new Cat("Jimbob"));
        shelter.enqueue(new Dog("Ginger"));

        System.out.println(shelter.dequeue(Dog.class) + " (should be Rosie)");
        System.out.println(shelter.dequeue(Cat.class) + " (should be Teddie)");
        System.out.println(shelter.dequeue() + " (should be Jimbob)");
    }
}

class Animal {
    private LocalDateTime timestamp;

    public Animal() {
        this.timestamp = LocalDateTime.now();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}

class Dog extends Animal {
    private String name;

    public Dog(String name) {
        super();
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

class Cat extends Animal {
    private String name;

    public Cat(String name) {
        super();
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

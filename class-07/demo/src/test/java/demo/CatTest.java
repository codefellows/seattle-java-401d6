package demo;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class CatTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCats() {
        Cat teddy = new Cat(true, 5, 11);
        teddy.meow();
        System.out.println(teddy.toString());

        // cats cannot be bobcats
        //((Bobcat) teddy).kill();
    }

    @Test
    public void testShelter() {
        LinkedList<ShelterableAnimal> animalsToTakeCareOf = new LinkedList<>();
        Cat teddy = new Cat(true, 5, 11);
        animalsToTakeCareOf.add(teddy);

        Mouse mickey = new Mouse();
        animalsToTakeCareOf.add(mickey);

        LinkedList<String> shoppingList = new LinkedList<>();
        for (ShelterableAnimal animal : animalsToTakeCareOf) {
            shoppingList.add(animal.getFoodType());
        }
        System.out.println(shoppingList);
    }
}
package demo;

public class Mouse implements ShelterableAnimal {

    @Override
    public String getEnclosureType() {
        return "small cage";
    }

    @Override
    public boolean canBeHousedWithOtherAnimalsOfType(Class type) {
        // fine as long as it's not a cat
        return !type.equals(Cat.class);
    }

    @Override
    public String getFoodType() {
        return "grains";
    }
}

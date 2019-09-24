package demo;

public interface ShelterableAnimal {
    public String getEnclosureType();
    public boolean canBeHousedWithOtherAnimalsOfType(Class type);
    public String getFoodType();
}

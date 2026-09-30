package com.petpulse.petpulse;

public class Cat extends Pet {
    private boolean isIndoor;

    public Cat(String id, String name, int age, double weight, boolean isIndoor) throws InvalidPetDataException {
        super(id, name, age, weight);
        this.isIndoor = isIndoor;
    }

    public boolean isIndoor() { return isIndoor; }
}
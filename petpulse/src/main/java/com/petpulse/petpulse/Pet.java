package com.petpulse.petpulse;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {
    private String id;
    private String name;
    private int age;
    private double weight;
    private List<HealthRecord> healthRecords = new ArrayList<>();

    public Pet(String id, String name, int age, double weight) throws InvalidPetDataException {
        if (age < 0 || weight <= 0) {
            throw new InvalidPetDataException("Age and weight must be positive values.");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public List<HealthRecord> getHealthRecords() { return healthRecords; }

    public void addHealthRecord(HealthRecord record) {
        this.healthRecords.add(record);
    }
}
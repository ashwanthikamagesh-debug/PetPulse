package com.petpulse.petpulse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dog {

    @Id
    private String id;
    private String name;
    private int age;
    private double weight;
    private String breed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Default Constructor required by JPA
    public Dog() {}

    // Parameterized Constructor
    public Dog(String id, String name, int age, double weight, String breed) throws InvalidPetDataException {
        if (age < 0 || weight <= 0) {
            throw new InvalidPetDataException("Age and weight must be positive values.");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
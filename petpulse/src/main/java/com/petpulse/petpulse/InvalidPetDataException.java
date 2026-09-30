package com.petpulse.petpulse;

public class InvalidPetDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidPetDataException(String message) {
        super(message);
    }
}
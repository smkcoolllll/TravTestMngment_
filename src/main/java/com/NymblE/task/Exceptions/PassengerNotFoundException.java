package com.NymblE.task.Exceptions;

public class PassengerNotFoundException extends Exception {

    public PassengerNotFoundException(String passengerId) {
        super("Passenger not found with id: " + passengerId);
    }
}
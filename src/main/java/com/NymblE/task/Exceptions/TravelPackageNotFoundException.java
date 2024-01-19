package com.NymblE.task.Exceptions;

public class TravelPackageNotFoundException extends Exception {
    public TravelPackageNotFoundException(String travelPackageId) {
        super("Travel package not found with id: " + travelPackageId);
    }
}

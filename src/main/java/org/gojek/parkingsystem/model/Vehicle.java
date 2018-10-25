package org.gojek.parkingsystem.model;


/**
 * This model holds the details of the vehicle being parked in the parking lot
 */
public class Vehicle {

    private String registrationNumber;
    private String color;

    public Vehicle(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public Vehicle() {

    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }


}

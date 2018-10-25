package org.gojek.parkingsystem.model;

import java.util.*;

/**
 * Model that represents the parking lot.
 * This class holds the information related to the car parking space allocation
 */
public class ParkingLot {

    private int capacity;
    private Vehicle[] parkingSpace;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingSpace = new Vehicle[capacity];
    }

    public List<Vehicle> getParkingSpace() {
        return Arrays.asList(parkingSpace);
    }
}

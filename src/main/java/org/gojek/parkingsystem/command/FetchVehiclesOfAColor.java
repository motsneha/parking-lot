package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchVehiclesOfAColor implements ParkingLotOperation {

    public List<String> execute(Map<String, Object> commandParams) {
        List<String> registrationNumbers = new ArrayList<>();
        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");
        String color = (String) commandParams.get("color");

        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();
        for (int i = 0; i < parkingSpace.size(); i++) {
            Vehicle vehicle = parkingSpace.get(i);
            if (vehicle.getColor().equals(color)) {
                registrationNumbers.add(vehicle.getRegistrationNumber());
            }
        }
        System.out.println(registrationNumbers.stream().collect(Collectors.joining(", ")));
        return registrationNumbers;
    }
}

package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchSlotForAColor implements ParkingLotOperation {

    public List<Integer> execute(Map<String, Object> commandParams) {
        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");
        String color = (String) commandParams.get("colorForSlots");

        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();
        List<Integer> vehicleSlots = new ArrayList<>();
        for (int i = 0; i < parkingSpace.size(); i++) {
            if (parkingSpace.get(i).getColor().equals(color)) {
                vehicleSlots.add(i + 1);
            }
        }
        System.out.println(vehicleSlots.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        return vehicleSlots;
    }
}

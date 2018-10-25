package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;

import java.util.Map;

public class CreateParkingLot implements ParkingLotOperation {

    @Override
    public Object execute(Map<String, Object> commandParams) {
        Integer slots = (Integer) commandParams.get("slots");
        ParkingLot parkingLot = new ParkingLot(slots);
        commandParams.put("parkingLot", parkingLot);
        System.out.println("Created a parking lot with " + slots + " slots");
        return parkingLot;
    }
}

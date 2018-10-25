package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;

import java.util.List;
import java.util.Map;

public class FetchSlotForAVehicle implements ParkingLotOperation {

    public Integer execute(Map<String, Object> commandParams) {
        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");
        String registrationNumber = (String) commandParams.get("regNumber");

        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();
        Integer slotNumber = -1;
        for (int i = 0; i < parkingSpace.size(); i++) {
            if (parkingSpace.get(i).getRegistrationNumber().equals(registrationNumber)) {
                slotNumber =  i + 1;
            }
        }
        System.out.println(slotNumber != -1 ? slotNumber : "Not Found");
        return slotNumber;
    }
}

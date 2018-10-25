package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Park implements ParkingLotOperation {

    public Object execute(Map<String, Object> commandParams) {
        Integer slotAllotted = null;
        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");

        Vehicle vehicle = new Vehicle((String)commandParams.get("registrationNumber")
                , (String)commandParams.get("vehicleColor"));

        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();

        for (int i = 0; i < parkingSpace.size(); i++) {
            if (parkingSpace.get(i) == null) {
                parkingSpace.set(i, vehicle);
                slotAllotted = i + 1;
                break;
            }
        }
        String message = Objects.isNull(slotAllotted) ? "Sorry, parking lot is full"
                : "Allocated slot number: " + slotAllotted;
        System.out.println(message);
        return slotAllotted;
    }
}

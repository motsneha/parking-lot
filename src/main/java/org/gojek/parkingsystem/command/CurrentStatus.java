package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CurrentStatus implements ParkingLotOperation {

    @Override
    public Object execute(Map<String, Object> commandParams) {
        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");
        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();
        System.out.println("Slot No.\tRegistration No.\tColour");
        for (int i = 0; i < parkingSpace.size(); i++) {
            Vehicle vehicle = parkingSpace.get(i);
            if(Objects.nonNull(vehicle)) {
                System.out.println(i + 1 + "\t\t\t" + vehicle.getRegistrationNumber() + "\t\t" + vehicle.getColor());
            }
        }
        return null;
    }
}

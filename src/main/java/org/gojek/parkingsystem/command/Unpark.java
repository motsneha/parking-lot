package org.gojek.parkingsystem.command;
import org.gojek.parkingsystem.model.ParkingLot;
import org.gojek.parkingsystem.model.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Unpark implements ParkingLotOperation {

    public Object execute(Map<String, Object> commandParams) {

        ParkingLot parkingLot = (ParkingLot) commandParams.get("parkingLot");
        Integer slotNumber = (Integer) commandParams.get("slotNumber");
        List<Vehicle> parkingSpace = parkingLot.getParkingSpace();
        if (Objects.nonNull(parkingSpace.get(slotNumber - 1))) {
            parkingSpace.set(slotNumber - 1, null);
            System.out.println("Slot number " + slotNumber + " is free");
            return slotNumber;
        }
        return -1;
    }
}

package org.gojek.parkingsystem.command;

import org.gojek.parkingsystem.model.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class CommandTest {

    public  Map<String, Object> params = new HashMap<>();


    @Test
    public void intwegrationTes() {
        params.put("slots", 6);
        ParkingLot parkingLot = (ParkingLot) new CreateParkingLot().execute(params);
        Park park = new Park();

        params.put("registrationNumber", "KA-01-HH-1234");
        params.put("vehicleColor", "White");
        Integer slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 1);

        params.put("registrationNumber", "KA-01-HH-9999");
        params.put("vehicleColor", "White");
        slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 2);

        params.put("registrationNumber", "KA-01-HH-3141");
        params.put("vehicleColor", "Black");
         slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 3);

        params.put("registrationNumber", "KA-01-BB-0001");
        params.put("vehicleColor", "Black");
         slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 4);

        params.put("registrationNumber", "KA-01-HH-7777");
        params.put("vehicleColor", "Red");
         slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 5);

        params.put("registrationNumber", "KA-01-HH-2701");
        params.put("vehicleColor", "Blue");
        slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 6);

        params.put("slotNumber", 4);
        Integer slotNumberFree =(Integer) new Unpark().execute(params);

        assertEquals(slotNumberFree, new Integer(4));

        new CurrentStatus().execute(params);

        params.put("registrationNumber", "KA-01-P-333");
        params.put("vehicleColor", "White");
        slot = (Integer) park.execute(params);
        assertEquals(slot.intValue(), 4);

        params.put("registrationNumber", "DL-12-AA-9999");
        params.put("vehicleColor", "White");
        slot = (Integer) park.execute(params);
        assertNull(slot);

        params.put("colorForSlots", "White");
        List<Integer> slots = new FetchSlotForAColor().execute(params);
        assertEquals(slots.size() , 3);

        params.put("regNumber", "KA-01-HH-2701");
        Integer slotNumberFor2701 = new FetchSlotForAVehicle().execute(params);
        assertEquals(slotNumberFor2701.intValue(), 6);
    }


}
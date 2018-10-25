package org.gojek.parkingsystem.command;
import java.util.Map;

@FunctionalInterface
public interface ParkingLotOperation {

    Object execute(Map<String, Object> commandParams);
}

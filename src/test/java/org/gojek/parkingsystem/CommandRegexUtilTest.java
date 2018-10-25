package org.gojek.parkingsystem;

import org.gojek.parkingsystem.util.CommandRegexUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CommandRegexUtilTest {

    private static String testParkCommand = "park KA-01-HH-1234 White";
    private static String testCreateCommand = "create_parking_lot 6";
    private static String testLeaveCommand = "leave 4";
    private static String testStatusCommand = "status";
    private static String testCommandForColor = "registration_numbers_for_cars_with_colour White";
    private static String testSlotNumbersofColorCommand = "slot_numbers_for_cars_with_colour White";
    private static String testSlotNumForRegNumCommand = "slot_number_for_registration_number KA-01-HH-3141";


    @Test
    public void testParseCommand() {
        List<String> commands = Arrays.asList(testCreateCommand,
                testParkCommand,
                testLeaveCommand,
                testStatusCommand,
                testCommandForColor,
                testSlotNumbersofColorCommand,
                testSlotNumForRegNumCommand);
        for (String command : commands) {
            System.out.println(CommandRegexUtil.parseCommand(command));
        }
    }
    @Test
    public void testParseCommandParams(){
        List<String> commands = Arrays.asList(testCreateCommand,
                testParkCommand,
                testLeaveCommand,
                testStatusCommand,
                testCommandForColor,
                testSlotNumbersofColorCommand,
                testSlotNumForRegNumCommand);
        for (String command : commands) {
            System.out.println(CommandRegexUtil.parseCommandParams(command));
        }
    }
}
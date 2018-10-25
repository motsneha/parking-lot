package org.gojek.parkingsystem.util;

import org.gojek.parkingsystem.command.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.gojek.parkingsystem.util.CommandConstants.*;

public class CommandRegexUtil {

    private static final String regexStatusCommand = "^(status)$";
    private static final String regexCreateParkingLot = "^(create_parking_lot)( )([\\d]+)$";
    private static final String regexParkCommnd = "(park)( )([A-Za-z]{2}[-][\\d]{2}[-][A-Za-z]{1,}[-][\\d]{1,})([ ])([A-Za-z]+)$";
    private static final String regexLeaveCommand = "^(leave)( )([1-9][0-9]*)$";
    private static final String regexRegColorsCommand = "^(registration_numbers_for_cars_with_colour)( )([A-Za-z]+)$";
    private static final String regexSlotNumbersColorCommand = "^(slot_numbers_for_cars_with_colour)( )([A-Za-z]+)$";
    private static final String regexSlotNumberOFRegNumberRegex = "^(slot_number_for_registration_number)( )([A-Za-z]{2}[-][\\d]{2}[-][A-Za-z]{1,}[-][\\d]{1,})$";


    public static Map<String, Object> commandParams = new HashMap<>();

    public static final List<Pattern> patterns;

    static {
        Pattern createRegex = Pattern.compile(regexCreateParkingLot);
        Pattern parkRegex = Pattern.compile(regexParkCommnd);
        Pattern leaveRegex = Pattern.compile(regexLeaveCommand);
        Pattern statusRegex = Pattern.compile(regexStatusCommand);
        Pattern regColorsRegex = Pattern.compile(regexRegColorsCommand);
        Pattern slotNumberColorRegex = Pattern.compile(regexSlotNumbersColorCommand);
        Pattern slotNumberOfReNumberRegex = Pattern.compile(regexSlotNumberOFRegNumberRegex);
        patterns = Arrays.asList(parkRegex, leaveRegex, slotNumberColorRegex,
                regColorsRegex, statusRegex, slotNumberOfReNumberRegex, createRegex);
    }

    public static String parseCommand(String command) {
        for (Pattern p : patterns) {
            Matcher matcher = p.matcher(command);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    /**
     * The method helps undertsand the command and params associated with it, later {@link ParkingLotOperation} class will use the params to execute the operation
     *
     * @param command
     * @return
     */
    public static Map<String, Object> commandParams(String command) {

        String commandType = parseCommand(command);
        List<String> params = parseCommandParams(command);
        switch (commandType) {
            case STATUS:
                break;
            case CREATE_PARKING_LOT:
                int slots = Integer.parseInt(params.get(0));
                commandParams.put("slots", slots);
                break;
            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                String color = params.get(0);
                commandParams.put("color", color);
                break;
            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                String regNumber = params.get(0);
                commandParams.put("regNumber", regNumber);
                break;
            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                String colorForSlots = params.get(0);
                commandParams.put("colorForSlots", colorForSlots);
                break;
            case LEAVE:
                int slot = Integer.parseInt(params.get(0));
                commandParams.put("slotNumber", slot);
                break;
            case PARK:
                String registrationNumber = params.get(0);
                String vehicleColor = params.get(1);
                commandParams.put("registrationNumber", registrationNumber);
                commandParams.put("vehicleColor", vehicleColor);
                break;
            default:
                System.out.println("Invalid action specified!");
                break;

        }

        return commandParams;
    }

    /**
     * Once we are aware of the command, we need to get the params that are passed to that commad, and collect them
     * the putput is empty if no params associated with the command
     *
     * @param command
     * @return
     */
    public static List<String> parseCommandParams(String command) {
        List<String> params = new ArrayList<>();
        for (Pattern p : patterns) {
            Matcher matcher = p.matcher(command);
            if (matcher.matches()) {
                /**
                 * starts from 2 as the first group will be the command.
                 */
                for (int i = 2; i <= matcher.groupCount(); i++) {
                    if (!matcher.group(i).equals(" ")) {
                        params.add(matcher.group(i));
                    }
                }
            }
        }
        return params;
    }

}

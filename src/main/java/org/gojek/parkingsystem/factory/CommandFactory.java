package org.gojek.parkingsystem.factory;

import org.gojek.parkingsystem.util.CommandRegexUtil;
import org.gojek.parkingsystem.command.*;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.gojek.parkingsystem.util.CommandConstants.*;


public class CommandFactory {

    private CommandFactory() {

    }


    private static Map<String, ParkingLotOperation> commands = new HashMap<>();

    private static CommandFactory commandFactory = new CommandFactory();

    public static CommandFactory getInstance() {
        return commandFactory;
    }


    static {
        commands.put(CREATE_PARKING_LOT, new CreateParkingLot());
        commands.put(PARK, new Park());
        commands.put(LEAVE, new Unpark());
        commands.put(STATUS, new CurrentStatus());
        commands.put(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR, new FetchVehiclesOfAColor());
        commands.put(SLOT_NUMBER_FOR_REGISTRATION_NUMBER, new FetchSlotForAVehicle());
        commands.put(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, new FetchSlotForAColor());
    }

    public void readInputFromConsole() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String inputString = bufferedReader.readLine();
                if (inputString.equals("exit")) {
                    break;
                } else if (inputString.isEmpty()) {
                    continue;
                } else {
                    processAndExecuteCommand(inputString.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read input from console");
        }

    }

    public void readInputFromFile(String file) {
        File inputFile = new File(file);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            bufferedReader.lines().forEach(
                    line -> processAndExecuteCommand(line)
            );

        } catch (IOException e1) {
            System.out.println("Unable to read input from file");
        }
    }

    public void processAndExecuteCommand(String args) {
        if (args.isEmpty()) {
            System.out.println("Invalid number of args specified!");
        } else {
            String command = CommandRegexUtil.parseCommand(args);
            if (command != null) {
                Map<String, Object> commandParams = CommandRegexUtil.commandParams(args);
                ParkingLotOperation parkingLotOperation = commands.get(command);
                if (parkingLotOperation != null) {
                    parkingLotOperation.execute(commandParams);
                }else{
                    System.out.println("Unsupported command operation!");
                }
            } else {
                System.out.println("Invalid command");
            }
        }


    }

}

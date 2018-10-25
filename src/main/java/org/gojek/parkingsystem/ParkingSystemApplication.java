package org.gojek.parkingsystem;


import org.gojek.parkingsystem.factory.CommandFactory;

public class ParkingSystemApplication {

    public static void main(String[] args) {
        int argumentCount = args.length;
        CommandFactory commandFactory = CommandFactory.getInstance();
        if (argumentCount == 0) {
            commandFactory.readInputFromConsole();
        } else if (argumentCount == 1) {
            commandFactory.readInputFromFile(args[0]);
        }else{
            System.out.println("Unable to process command!");
        }
    }
}

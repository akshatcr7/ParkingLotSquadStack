import parking.ParkingLot;
import utils.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

public class Main {

    public static ParkingLot parkingLot;
    public static Commands commands;

    public static void main(String[] args) throws Exception{
        parkingLot = new ParkingLot();
        commands = new Commands();
        FileReader fileReader = new FileReader(args[0]);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String inputCommand;

        while((inputCommand = bufferedReader.readLine()) != null) {
            executeCommand(inputCommand);
        }
        fileReader.close();
    }

    private static void executeCommand(String inputCommand) {
        String[] commandString = inputCommand.split(" ");
        Method method;
        try{
            switch(commandString.length) {
                case 2 :
                    method = commands.commandsMap.get(commandString[0]);
                    if (method != null) {
                        method.invoke(parkingLot, commandString[1]);
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;
                case 4 :
                    method = commands.commandsMap.get(commandString[0]);
                    if (method != null) {
                        String registrationNumber = commandString[1];
                        int age = Integer.parseInt(commandString[3]);
                        method.invoke(parkingLot, registrationNumber, age);
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }catch (Exception e) {
            System.out.println("Something went wrong : " + e.getMessage());
        }
    }
}

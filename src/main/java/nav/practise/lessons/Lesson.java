package nav.practise.lessons;

import nav.practise.entity.GasQuality;
import nav.practise.entity.vehicle.Vehicle;
import nav.practise.entity.vehicle.ground.Car;
import nav.practise.entity.Driver;
import nav.practise.entity.refuel.GasStation;
import nav.practise.entity.scheduling.Iterating;
import nav.practise.entity.vehicle.ground.Tractor;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Lesson {

    public static void main(String... args){
        Vehicle[] vehicles = new Vehicle[] {
                 new Car(),
                 new Tractor()
        };


        Scanner scanner = new Scanner(
                Lesson.class.getClassLoader().getResourceAsStream("commands.txt")
        );
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println("Input command: " + command);
            for (int i = 0; i < vehicles.length; i++) {
                processCommand(vehicles[i], command);
                afterCommand(vehicles[i]);
                System.out.println("Vehicle status: " + vehicles[i]);
            }
        }

        System.out.println("Goodbye!");
    }

    private static void processCommand(Vehicle vehicle, String command) {
        String[] splitCommand = command.split(" ");

        switch (splitCommand[0]) {
            case "refuel":
                refuelVehicle(vehicle, splitCommand);
                break;
            case "set_driver":
                Driver driver = new Driver("Вася");
                vehicle.setDriver(driver);
                break;
            case "unset_driver":
                vehicle.unsetDriver();
                break;
            case "power":
                vehicle.powerSwitch();
                break;
            case "acc":
                accVehicle(vehicle, splitCommand);
                break;
            case "dec":
                decVehicle(vehicle, splitCommand);
                break;
        }
    }

    private static void afterCommand(Iterating iterating) {
        iterating.perIteration();
    }

    private static void decVehicle(Vehicle vehicle, String[] splitCommand) {
        if (splitCommand.length == 1) {
            vehicle.dec();
        } else {
            vehicle.dec(Double.parseDouble(splitCommand[1]));
        }
    }

    private static void accVehicle(Vehicle Vehicle, String[] splitCommand) {
        if (splitCommand.length == 1) {
            Vehicle.acc();
        } else {
            Vehicle.acc(Double.parseDouble(splitCommand[1]));
        }
    }

    private static void refuelVehicle(Vehicle vehicle, String[] splitCommand) {
        if (splitCommand.length >= 3) {
            if (GasQuality.isValidFuel(splitCommand[1])) {
                GasStation.getInstance().refuelOut(
                    splitCommand[1],
                    Double.parseDouble(splitCommand[2]),
                    vehicle
                );
            }
            else {
                Pattern intPattern = Pattern.compile("^d+?$");
                if (intPattern.matcher(splitCommand[1]).matches()) {
                    GasStation.getInstance().refuelOut(
                            Integer.parseInt(splitCommand[1]),
                            Double.parseDouble(splitCommand[2]),
                            vehicle
                    );
                }
                else {
                    System.err.println("cannot refuel via " + splitCommand[1]);
                }
            }

        }
    }

}

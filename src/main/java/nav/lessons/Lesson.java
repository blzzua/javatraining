package nav.lessons;

import nav.entity.Car;
import nav.entity.Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lesson {


    public static void main(String... args) throws IOException {
        System.out.println("Hello!\n");

        Car car1 = new Car(3, 5, 100, 50, 1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int distance = 0;

        String command = br.readLine();
        while (!"exit".equals(command)) {
            processCommand(car1, command);
            distance += car1.getVelocity();
            System.out.println("Velocity: " + car1.getVelocity() +
                    ", gas left: " + car1.getVolumeGasTankCur() +
                    ", total distance: " + distance);
            command = br.readLine();
        }

        System.out.println("\nGood Bye!");
    }

    private static void processCommand(Car car, String command) {
        switch (command) {
            case "refuel":
                int fuel = car.refuel(10);
                System.out.println("Was refueled for " + fuel);
                break;
            case "set driver":
                Driver driver = new Driver("Вася");
                car.setDriver(driver);
                System.out.println("There is " + ( car.getDriver() != null  ? "a driver" : "no driver" ) + " inside" );
                break;
            case "unset driver":
                car.unsetDriver();
                System.out.println("There is " + ( car.getDriver() != null  ? "a driver" : "no driver" ) + " inside" );
                break;
            case "power":
                boolean powerOn = car.powerSwitch();
                System.out.println("Power is " + ( powerOn ? "on" : "off" ) );
                break;
            case "acc":
                car.acc();
                break;
            case "dec":
                car.dec();
                break;
        }
        car.perIteration();
    }
}

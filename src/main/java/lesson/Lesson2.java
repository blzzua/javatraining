package lesson;

import phone.SmartPhone;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Lesson2 {
    public static void main(String[] args)  {
        //part_homework(args);
        try {
            timeLoop(args);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    public static void part_homework(String[] args) {
        SmartPhone redminote4 = new SmartPhone("Xiaomi Redmi Note 4");
        redminote4.setBattery(4100F);
        redminote4.setCardSlot(true);

        redminote4.setDimension(151, 76);
        redminote4.setMainCamera(13, false);

        System.out.println(redminote4);

        redminote4.setFastCharge(true);
        System.out.println("start  level:" + redminote4.getChargeLevel());
        int timeGranularity = 600;
        for (int i = 0; i <= 2 * 3600; i += timeGranularity) {
            redminote4.ChargeDuringTime(2500, timeGranularity);
            System.out.println("charge time " + i + " sec, level:" + redminote4.getChargeLevel());
        }
    }

public static void timeLoop(String[] args) throws IOException {
        //seconds per minute
        long time;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        boolean exitFlag = false;
        while (!exitFlag) {
            command = br.readLine();
            time = System.currentTimeMillis() / 1000L;
            switch (command) {
                case "charge":
                    System.out.println("Was recharged for " + time);
                    break;
                case "exit":
                    exitFlag = true;
                    break;
                default:
                    System.out.println(time + ": unknown command " + command);
                    break;
            }
        }
    }
}




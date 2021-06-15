import phone.SmartPhone;

public class Lesson2 {
    public static void main(String[] args) {
        // part1(args);
        // System.out.println(part2());
        part_homework(args);
    }

    public static void part1(String[] args) {
        String str1 = "string1";
        System.out.println("string1 = " + str1);
        String str2 = new String("string2");
        System.out.println("string2 = " + str2);
        String str3 = "New\nline!";
        System.out.println("string3 = " + str3);

        String str4 = "New line";
        System.out.println("string4 = ");
        char[] CharArray = str4.toCharArray();
        CharArray[3] = '_';
        System.out.println(CharArray);
    }

    public static boolean part2(String... args) {
        /* operators */
        int a = 5;
        int b = 4;
        int c = a + b;
        int d = a - b;
        int e = a / b;
        System.out.println("operators^ c=" + c + " d=" + d + " e=" + e);
        int t;
        t = (a == 1) ? 20 : 30;
        System.out.println("ternary operators t=" + t);

        return false;
    }

    public static boolean part3(String... args) {
        /* ассоциация */
        /*
        Ассоциация – это когда один класс включает в себя другой класс в качестве одного из полей. Ассоциация описывается словом «имеет».
         Автомобиль имеет двигатель. Вполне естественно, что он не будет являться наследником двигателя (хотя такая архитектура тоже возможна в некоторых ситуациях).

        Выделяют два частных случая ассоциации: композицию и агрегацию.
        Композиция – это когда двигатель не существует отдельно от автомобиля. Он создается при создании автомобиля и
        полностью управляется автомобилем. В типичном примере, экземпляр двигателя будет создаваться в конструкторе автомобиля.

        Агрегация – это когда экземпляр двигателя создается где-то в другом месте кода, и передается в конструктор автомобиля в качестве параметра.
        return false;

         */
        return false;
    }

    public static boolean part4(String... args) {
        System.out.println("Hello World!");
        wallet.Account myAccount = new wallet.Account();
        myAccount.balance = 100d;
        System.out.println(myAccount.balance);
        return false;
    }

    public static boolean part5(String... args) {
        /*
        модификаторы доступа
        публичный
        по пакету (по-умолчанию. область видимости).
        private
        protected
        */
        return false;
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
        for (int i = 0 ; i <= 2*3600 ; i += timeGranularity) {
            redminote4.ChargeDuringTime(2500, timeGranularity);
            System.out.println("charge time "+i+" sec, level:" + redminote4.getChargeLevel());
        }
    }


}



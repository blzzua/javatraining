package nav.theory;

public class TheoryClass {

    public static void main(String... args) {
        TestStatic ts1 = new TestStatic();
        ts1.i = 14;
        System.out.println(ts1.i);
        TestStatic ts2 = new TestStatic();
        ts2.i = 41;
        System.out.println(ts1.i);
    }

}

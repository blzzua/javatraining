package nav.theory;
import nav.practise.entity.GasQuality;
import nav.practise.entity.refuel.GasStation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class Test {

    public static void main(String... args) {
        // System.out.println(sum(1,2,0,3,4));
        //testImpl(null, 1);
        // testImpl(0, 1);
        // testImpl(0, 1, 2);
        // testImpl(0, 1, 1, 2);


        // GasQuality selectedGasQuality = GasQuality.valueOf(gasQ);
        // System.out.println("Arrays.stream(GasQuality.values()).contains(gasQ) = " + (Arrays.asList(values).contains("GOOD")));


    }

    private static void testImpl(Integer... value){
        try {
            Consumer<Integer> consumer = System.out::println;
            consumer.accept(sum(consumer, value));
        } catch (Exception ex) {
            ((Runnable) ex::printStackTrace).run();
        }
    }

    private static int sum(Consumer<Integer> consumer, Integer[] value) {
        Integer res;
        try {
            res = sum(value);
            consumer.accept(res);
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return (Integer) Optional.ofNullable(null)
                    .orElseThrow(IllegalArgumentException::new);
        } finally {
            throw ((Function<String, RuntimeException>) RuntimeException::new).apply("Finally runtime exception");
        }
    }

    private static int sum(Integer... values) {
        try {
            return Arrays
                    .stream(values)
                    .filter(new HashSet<>()::add)
                    .mapToInt(Integer::intValue)
                    .sum();
        } catch (Exception ex) {
            ex.printStackTrace();
            return (Integer) null;
        }
    }
}
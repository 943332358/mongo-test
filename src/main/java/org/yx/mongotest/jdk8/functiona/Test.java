package org.yx.mongotest.jdk8.functiona;


import lombok.SneakyThrows;
import pl.touk.throwing.ThrowingFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.function.Function.identity;


/**
 * @author yangxin
 */
public class Test {


    @SneakyThrows
    public static void main(String[] args) {
        // Consumer
        Consumer<String> consumer = c -> System.out.println("Consumer " + c);
        consumer.accept("1");
        consumer.accept("2");
        consumer.andThen(a -> System.out.println("Consumer#andThen() " + a)).accept("3");

        // Supplier
        Supplier<String> supplier = () -> "Supplier";
        supplier.get();

        // Function
        Function<Integer, String> function = f -> "Function " + f;
        System.out.println(function.apply(1));
        System.out.println(function.apply(2));
        System.out.println(function.andThen(a -> "Function#andThen() " + a).apply(3));
        System.out.println(function.compose((Integer f) -> f * f).apply(4));
        System.out.println(identity().apply("Function#identity()"));

        // Predicate
        Predicate<Integer> predicate = p -> p == 1;
        System.out.println(predicate.test(1));
        System.out.println(predicate.test(2));

        // 异常处理
        Function<Integer, String> throwingFunction = ThrowingFunction.unchecked(f -> {
            Thread.sleep(1);
            return "ThrowingFunction " + f;
        });
        System.out.println(throwingFunction.apply(1));

        // 自定义Function
        org.yx.mongotest.common.function.Function<Integer, String> function1 = f -> {
            Thread.sleep(1);
            return "Custom function " + f;
        };
        System.out.println(function1.apply(1));


    }
}

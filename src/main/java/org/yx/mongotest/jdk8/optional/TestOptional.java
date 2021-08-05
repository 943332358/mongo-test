package org.yx.mongotest.jdk8.optional;

import lombok.var;
import org.yx.mongotest.authorization.entity.User;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author yangxin
 */
public class TestOptional {

    public static void main(String[] args) {
        var name = getOptional(true)
                .map(User::getPassword)
                // 该方法，在ofNullable不为空的情况下也会执行
                .orElse(get());
        //.orElseThrow(RuntimeException::new)
        //.orElseGet(TestOptional::get);
        System.out.println(name);

        // If a value is present, invoke the specified consumer with the value, otherwise do nothing.
        getOptional(false).ifPresent(System.out::println);

        // 错误用法,该用法与 if(value == null) 无异
        var optional = getOptional(true);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("value is null");
        }
    }

    public static Optional<User> getOptional(boolean tag) {
        if (tag) {
            return Optional.empty();
        }

        return Optional.ofNullable(new User().setName("张三").setAge(20).setBirthday(LocalDate.of(1991, 11, 12)).setPassword("123"));
    }

    public static String get() {
        System.out.println("get in.......");
        return "456";
    }

}

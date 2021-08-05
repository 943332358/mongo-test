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
        var name = Optional.ofNullable(new User().setName("张三").setAge(20).setBirthday(LocalDate.of(1991, 11, 12)).setPassword("123"))
                .map(User::getPassword)
                //.orElse("456");
                //.orElseThrow(RuntimeException::new)
                .orElseGet(() -> {
                    return "456";
                });
        System.out.println(name);
    }

}

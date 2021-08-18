package org.yx.mongotest.javassist;

import com.google.common.collect.Lists;
import lombok.var;
import org.yx.mongotest.authorization.entity.User;
import pl.touk.throwing.ThrowingConsumer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yangxin
 */
public class ForVsParallel {


    public static void main(String[] args) {
        final var zs = new User().setName("张三").setAge(20).setBirthday(LocalDate.of(1991, 11, 12)).setPassword("5");
        final var ls = new User().setName("李四").setAge(15).setBirthday(LocalDate.of(1999, 9, 30)).setPassword("4");
        final var ww = new User().setName("王五").setAge(20).setBirthday(LocalDate.of(1801, 3, 20)).setPassword("1");
        final var zl = new User().setName("赵六").setAge(20).setBirthday(LocalDate.of(1959, 10, 9)).setPassword("2");
        final var zll = new User().setName("赵六六").setAge(56).setBirthday(LocalDate.of(1969, 10, 19)).setPassword("3");
        List<User> users = Lists.newArrayList();
        users.add(zs);
        users.add(ls);
        users.add(ww);
        users.add(zl);
        users.add(zll);


        var start = LocalDateTime.now();
        users.forEach(ThrowingConsumer.unchecked(f -> {
            f.setAge(f.getAge() * 10 / 5);
            Thread.sleep(1000);
        }));
        var end = LocalDateTime.now();
        System.out.println(end.getSecond() - start.getSecond());

    }
}

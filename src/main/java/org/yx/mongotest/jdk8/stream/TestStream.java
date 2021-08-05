package org.yx.mongotest.jdk8.stream;

import com.google.common.collect.Lists;
import lombok.var;
import org.aspectj.lang.ProceedingJoinPoint;
import org.yx.mongotest.authorization.entity.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author yangxin
 */
public class TestStream {

    public static void main(String[] args) {
        final var zs = new User().setName("张三").setAge(20).setBirthday(LocalDate.of(1991, 11, 12)).setPassword("5");
        final var ls = new User().setName("李四").setAge(15).setBirthday(LocalDate.of(1999, 9, 30)).setPassword("4");
        final var ww = new User().setName("王五").setAge(20).setBirthday(LocalDate.of(1801, 3, 20)).setPassword("1");
        final var zl = new User().setName("赵六").setAge(20).setBirthday(LocalDate.of(1959, 10, 9)).setPassword("2");
        final var zll = new User().setName("赵六六").setAge(56).setBirthday(LocalDate.of(1969, 10, 19)).setPassword("3");
        Supplier<Stream<User>> users = () -> Stream.of(zs, zll, ls, ww, zl);

        // 过滤age不等于15的user(filter)
        users.get().filter(f -> f.getAge() == 15).forEach(System.out::println);

        // 集合转为age数组(map)
        users.get().map(User::getAge).forEach(System.out::println);

        // 修改 张三 age 值为 200(peek)
        users.get().filter(f -> "张三".equals(f.getName())).peek(p -> p.setAge(200)).forEach(System.out::println);
        // peek错误用法(不会执行)
        users.get().peek(System.out::println);

        System.out.println("—————————————————————————————————————————————————sorted—————————————————————————————————————————————————");
        // 排序(sorted)
        users.get().sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);

        System.out.println("—————————————————————————————————————————————————findFirst—————————————————————————————————————————————————");
        // findFirst
        users.get().parallel().findFirst().ifPresent(System.out::println);
        // findAny 默认情况下与 findFirst 等效。但是并行流不一定
        users.get().parallel().findAny().ifPresent(System.out::println);

        System.out.println("—————————————————————————————————————————————————anyMatch、allMatch—————————————————————————————————————————————————");
        // 判断是否存在 age > 60 的人员
        System.out.println("判断是否存在 age > 60 的人员：" + users.get().anyMatch(a -> a.getAge() > 60));
        /**
         * 判断是否所有人员 age > 60
         * {@link org.yx.mongotest.common.aop.PermissionAop#around(ProceedingJoinPoint)}
         * 判断接口需要的权限编码，用户是否全部拥有或只拥有其中一个
         */
        System.out.println("判断是否所有人员 age > 60：" + users.get().allMatch(a -> a.getAge() > 60));

        System.out.println("—————————————————————————————————————————————————flatMap—————————————————————————————————————————————————");
        // stream扁平化（类似于二维数组降级到一维数组）
        Stream.of(Lists.newArrayList(1, 2, 3), Lists.newArrayList(4, 5, 6)).flatMap(Collection::stream).forEach(System.out::println);

        System.out.println("—————————————————————————————————————————————————reduce—————————————————————————————————————————————————");
        // 计算user的年龄总和
        users.get().map(User::getAge).reduce(Integer::sum).ifPresent(System.out::println);

        System.out.println("—————————————————————————————————————————————————groupingBy—————————————————————————————————————————————————");
        // 通过password分组
        users.get().sorted(Comparator.comparing(User::getPassword))
                .collect(Collectors.groupingBy(User::getAge))
                .forEach((k, v) -> System.out.printf("%s %s%n", k, v));
        System.out.println("—————————————————————————————————————————————————groupingBy and sorted—————————————————————————————————————————————————");
        users.get().sorted(Comparator.comparing(User::getPassword))
                .collect(Collectors.groupingBy(User::getAge, LinkedHashMap::new, toList()))
                .forEach((k, v) -> System.out.printf("%s %s%n", k, v));
        System.out.println("—————————————————————————————————————————————————groupingBy and sorted—————————————————————————————————————————————————");
        users.get().sorted(Comparator.comparing(User::getPassword))
                .collect(Collectors.groupingBy(User::getAge, LinkedHashMap::new, toMap(User::getName, identity())))
                .forEach((k, v) -> System.out.printf("%s %s%n", k, v));

    }
}

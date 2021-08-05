package org.yx.mongotest.jdk8.interface8;

import lombok.var;

import static org.yx.mongotest.jdk8.interface8.Obj.run;

/**
 * @author yangxin
 */
public class TestInterface {
    public static void main(String[] args) {
        run();

        var cat = new Cat();
        cat.say();

        var dog = new Dog();
        dog.say();

        var person = new Person();
        person.say();
    }
}

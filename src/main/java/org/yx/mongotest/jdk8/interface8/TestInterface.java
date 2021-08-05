package org.yx.mongotest.jdk8.interface8;

import lombok.var;

/**
 * @author yangxin
 */
public class TestInterface {
    public static void main(String[] args) {
        Obj.run();

        var cat = new Cat();
        cat.say();

        var dog = new Dog();
        dog.say();

        var person = new Person();
        person.say();
    }
}

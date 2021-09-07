package org.yx.mongotest.leetcode;

import lombok.var;

import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        var stack = new Stack<>();
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");

        System.out.println(stack.pop());
        System.out.println(stack.peek());

        stack.forEach(System.out::println);

    }
}

package org.yx.mongotest.javassist;

import org.yx.mongotest.authorization.entity.User;

/**
 * @author yangxin
 */
public class JavassistTest {
    private String name;

    public JavassistTest(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("Hello");
    }

    public void say(String aa) {
        System.out.println(aa);
    }

    public void say(String aa, User user) {
        String sql = "insert into aa";
        System.out.println("sql：" + sql);
        System.out.println(aa + user);
    }
}

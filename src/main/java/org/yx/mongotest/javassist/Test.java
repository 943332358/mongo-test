package org.yx.mongotest.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.var;
import org.yx.mongotest.authorization.entity.User;

/**
 * @author yangxin
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();


        CtClass cc = pool.get("org.yx.mongotest.javassist.JavassistTest");
        cc.defrost();

        var userClass = pool.get("org.yx.mongotest.authorization.entity.User");
        var stringClass = pool.get("java.lang.String");
        var m = cc.getDeclaredMethod("say", new CtClass[]{stringClass, userClass});


        m.insertBefore("System.out.println(\"modify class ok\");");
        m.insertAfter("System.out.println(\"modify class end\");");
        m.insertAt(26, "int a = 10;" +
                "System.out.println(a);" +
                "System.out.println(name);");

        m.insertAt(25, "sql = \"update aa\";");

        cc.writeFile();
        cc.toClass();

        JavassistTest h2 = new JavassistTest("yangxin");
        h2.say("aa", new User());

    }
}

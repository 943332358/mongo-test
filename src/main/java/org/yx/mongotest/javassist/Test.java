package org.yx.mongotest.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author yangxin
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        String cc1 = "cc";
        System.out.println(cc1);


        CtClass cc = pool.get("org.yx.mongotest.javassist.JavassistTest");
        cc.defrost();


        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("System.out.println(\"modify class ok\");");
        m.insertAfter("System.out.println(\"modify class end\");");
        m.insertAt(9, "int a = 10;" +
                "System.out.println(a);");

        cc.writeFile();
        cc.toClass();

        JavassistTest h2 = new JavassistTest();
        h2.say();
    }
}

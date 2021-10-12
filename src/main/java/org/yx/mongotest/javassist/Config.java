package org.yx.mongotest.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author A
 */
@Configuration
public class Config {

    @SneakyThrows
    @Bean
    public JavassistTest javassistTest() {
        ClassPool pool = ClassPool.getDefault();


        CtClass cc = pool.get("org.yx.mongotest.javassist.JavassistTest");
        cc.defrost();

        var userClass = pool.get("org.yx.mongotest.authorization.entity.User");
        var stringClass = pool.get("java.lang.String");
        var m = cc.getDeclaredMethod("say", new CtClass[]{stringClass, userClass});


        m.insertBefore("System.out.println(\"modify class ok\");");
        m.insertAfter("System.out.println(\"modify class end\");");
        m.insertAt(30, "int a = 10;" +
                "System.out.println(a);" +
                "System.out.println(name);");

        m.insertAt(29, "sql = \"update aa\";");

//        cc.toClass();

        return new JavassistTest("yangxin");
    }
}

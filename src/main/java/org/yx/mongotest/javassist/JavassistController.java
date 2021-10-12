package org.yx.mongotest.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.authorization.entity.User;

import javax.annotation.Resource;

/**
 * @author A
 */
@RestController
@RequestMapping("javassist")
public class JavassistController {
    @Resource
    private JavassistTest javassistTest;

    @RequestMapping("test")
    public String test() {
        javassistTest.say("666", new User());
        return "end";
    }

    @SneakyThrows
    @RequestMapping("test1")
    public String test1() {
        ClassPool pool = ClassPool.getDefault();

        pool.appendClassPath(new LoaderClassPath(JavassistTest.class.getClassLoader()));

        CtClass cc = pool.get("org.yx.mongotest.javassist.JavassistTest");
        cc.defrost();

        var userClass = pool.get("org.yx.mongotest.authorization.entity.User");
        var stringClass = pool.get("java.lang.String");
        var m = cc.getDeclaredMethod("say", new CtClass[]{stringClass, userClass});


        m.insertBefore("System.out.println(\"modify class ok\");");
        m.insertAfter("System.out.println(\"modify class end\");");
        m.insertAt(31, "int a = 10;" +
                "System.out.println(a);" +
                "System.out.println(name);");

        m.insertAt(30, "sql = \"update aa\";");

        //cc.writeFile();
        cc.toClass();

        javassistTest.say("aa", new User());
        return "end1";
    }
}

package org.yx.mongotest.javassist;

import com.google.common.collect.Lists;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import lombok.var;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author yangxin
 */
@Configuration
public class Test2 {
    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {

        var pool = ClassPool.getDefault();
        var ctClass = pool.get("org.springframework.context.support.GenericApplicationContext");


        var method = ctClass.getDeclaredMethod("setAllowBeanDefinitionOverriding", new CtClass[]{CtClass.booleanType});

        method.insertBefore("System.out.println(customClassLoader);\n" +
                "        if (customClassLoader){\n" +
                "            System.out.println(\"in\");\n" +
                "        }else {\n" +
                "            System.out.println(\"out\");\n" +
                "        }");


        ctClass.writeFile();
        ctClass.toClass();

        var context = new AnnotationConfigApplicationContext(Test2.class);
        context.setAllowBeanDefinitionOverriding(false);

    }
}

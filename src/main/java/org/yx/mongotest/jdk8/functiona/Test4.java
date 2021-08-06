package org.yx.mongotest.jdk8.functiona;

import lombok.var;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.yx.mongotest.jdk8.interface8.Cat;
import org.yx.mongotest.jdk8.interface8.Dog;
import org.yx.mongotest.jdk8.interface8.Obj;
import org.yx.mongotest.jdk8.interface8.Person;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author yangxin
 */
@ComponentScan("org.yx.mongotest.jdk8")
public class Test4 {

    static Function<Class<? extends Obj>, Obj> function = SpringContextUtils::getBean;

    public static Obj getObj(Class<? extends Obj> obj) {
        return Arrays.stream(ObjEnum1.values()).filter(f -> f == ObjEnum1.OBJ)
                .findFirst()
                .map(m -> m.getFunction().apply(obj))
                .orElseThrow(RuntimeException::new);
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Test4.class);

        var obj = getObj(Dog.class);
        obj.say();
    }

}

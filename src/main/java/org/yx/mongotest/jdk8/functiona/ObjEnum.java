package org.yx.mongotest.jdk8.functiona;

import org.yx.mongotest.jdk8.interface8.Cat;
import org.yx.mongotest.jdk8.interface8.Dog;
import org.yx.mongotest.jdk8.interface8.Obj;
import org.yx.mongotest.jdk8.interface8.Person;

/**
 * @author yangxin
 */
public enum ObjEnum {
    // 猫
    CAT(SpringContextUtils.getBean(Cat.class)),
    // 狗
    DOG(SpringContextUtils.getBean(Dog.class)),
    // 人
    PERSON(SpringContextUtils.getBean(Person.class)),

    OBJ(SpringContextUtils.getBean(Obj.class));


    ObjEnum(Obj obj) {
        this.obj = obj;
    }

    private Obj obj;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

}

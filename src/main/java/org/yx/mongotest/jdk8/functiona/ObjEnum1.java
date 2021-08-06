package org.yx.mongotest.jdk8.functiona;

import org.yx.mongotest.jdk8.interface8.Obj;

import java.util.function.Function;

/**
 * @author yangxin
 */
public enum ObjEnum1 {
    // 猫
    CAT(Test4.function),
    // 狗
    DOG(Test4.function),
    // 人
    PERSON(Test4.function),
    // 接口
    OBJ(Test4.function);

    private Function<Class<? extends Obj>, Obj> function;

    ObjEnum1(Function<Class<? extends Obj>, Obj> function) {
        this.function = function;
    }

    public Function<Class<? extends Obj>, Obj> getFunction() {
        return function;
    }

    public void setFunction(Function<Class<? extends Obj>, Obj> function) {
        this.function = function;
    }

}

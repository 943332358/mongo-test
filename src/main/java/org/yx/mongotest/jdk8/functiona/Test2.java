package org.yx.mongotest.jdk8.functiona;

import lombok.var;

import java.util.function.Consumer;

/**
 * @author yangxin
 */
public class Test2 {
    private static final Language LANGUAGE = Language.BYE;


    enum Language {
        // hi
        HI,
        // bye
        BYE,
        // eat
        EAT
    }

    public static String say(Language language) {
        switch (language) {
            case HI:
                return "你好";
            case BYE:
                return "再见";
            case EAT:
                return "吃饭了吗";
            default:
                return "..";
        }

    }


    public static void main(String[] args) {
        switch (LANGUAGE) {
            case EAT:
                var say = say(Language.EAT);
                say = "张三：" + say;
                System.out.println(say);
                break;
            case BYE:
                var bye = say(Language.BYE);
                bye = "张三:" + bye;
                System.out.println(bye);
                break;
            case HI:
                var hi = say(Language.HI);
                hi = "张三:" + hi;
                System.out.println(hi);
                break;
            default:
                System.out.println("ss");
        }


        final Consumer<Language> consumer = c -> {
            var say = say(c);
            say = "张三:" + say;
            System.out.println(say);
        };
        switch (LANGUAGE) {
            case EAT:
                consumer.accept(Language.EAT);
                break;
            case BYE:
                consumer.accept(Language.BYE);
                break;
            case HI:
                consumer.accept(Language.HI);
                break;
            default:
                System.out.println("ss");
        }


    }
}

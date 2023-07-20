package com.aaa.javabase.base;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author liuzhen.tian
 * @version 1.0 MyTimeUnit.java  2023/7/20 22:07
 */
@Log4j2
public enum MyActionEnum {
    SayHello {
        public long doAction(long input) {
            System.out.println("SayHello...");
            return 1;
        }
    },
    SayGoodBye {
        public long doAction(long input) {
            System.out.println("SayGoodBye...");
            return 2;
        }
    };

    public long doAction(long input) {
        throw new AbstractMethodError();
    }

    public static MyActionEnum getByEnumName(String enumName) {
        return Arrays.stream(MyActionEnum.values())
                .filter(e -> StringUtils.equals(enumName, e.name()))
                .findAny()
                .orElseThrow(() -> new RuntimeException(enumName + ": is null！"));
    }


    public static void main(String[] args) {
        MyActionEnum sayHello = MyActionEnum.getByEnumName("SayHello");
        sayHello.doAction(0);

        MyActionEnum sayGoodBye = MyActionEnum.getByEnumName("SayGoodBye");
        sayGoodBye.doAction(0);
    }
}

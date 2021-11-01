package com.aaa.javabase.pattern.behavior.chain.demo1;

import com.aaa.javabase.pattern.behavior.chain.demo1.type.IReceiptHandler;
import com.aaa.javabase.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 处理者容器
 * @author liuzhen.tian
 * @version 1.0 ReceiptHandlerContainer.java  2020/11/10 11:38
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer(){}

    /**
     * 使用反射获取接口的实现类，或者利用spring 来实现
     * 【src/main/java/com/aaa/javabase/pattern/behavior/strategy/InspectionSolverChooser.java】
     */
    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        //获取IReceiptHandler接口的实现类
        Set<Class<?>> classList = ReflectionUtil.getClassSetBySuper(IReceiptHandler.class);
        if (classList != null && classList.size() > 0) {
            for (Class<?> clazz : classList) {
                try {
                    receiptHandlerList.add((IReceiptHandler)clazz.newInstance());
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return receiptHandlerList;
    }

}

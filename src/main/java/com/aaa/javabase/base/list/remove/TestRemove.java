package com.aaa.javabase.base.list.remove;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestRemove.java  2023/9/2 15:34
 */
public class TestRemove {
    public static void main(String[] args) {
        /**
         * 错误案例（正序删）
         * list = [2, 2, 3, 2, 5]
         * 如果连续删除俩个相同元素，第一次当index=0时，  remove(0);
         * 此时会把所有的元素下标往前推进
         * list = [2, 3, 2, 5]
         * 第二次执行 当index=1，  remove(1);  此时的1是新数组的下标1，而不是老数组的下标1，会忽略新数组的第一个值
         *
         */
        List<Integer> errorList = Lists.newArrayList(2, 2, 3, 2, 5);
        for (int i = 0; i < errorList.size(); i++) {
            if (errorList.get(i) == 2) {
                errorList.remove(i);
            }
        }
        /**
         * 正确案例（倒序删）
         * list = [2, 2, 3, 5, 5]
         * 如果连续删除俩个相同元素，第一次当index=4时，  remove(4);
         * list =[2, 2, 3, 5]
         * 第二次执行 当index=3，  remove(3);  实际上删除的3，还是原数组对应下标的3
         * 因为删除的是最后面的，所以下标不会改变
         *
         */
        List<Integer> successList = Lists.newArrayList(2, 2, 3, 5, 5);
        for (int i = successList.size() - 1; i >= 0; i--) {
            if (successList.get(i) == 5) {
                successList.remove(i);
            }
        }
        System.out.println(successList);
    }
}

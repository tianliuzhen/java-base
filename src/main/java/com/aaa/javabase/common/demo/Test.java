package com.aaa.javabase.common.demo;

import java.util.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/16
 */
public class Test {

    public static void main(String[] args) {

        group2();
    }
    public static void group2(){
        //这里是模拟的数据
        List<DiBang> diBangs = addBiBing();
        // 最终封装的转换
        List<DiBangTotal> diBangTotals = new ArrayList<>();
        List<String> str = new ArrayList<>();
        for (int i = 0; i < diBangs.size(); i++) {
            DiBangTotal diBangTotal = new DiBangTotal();
            diBangTotal.setCarCode(diBangs.get(i).getCarCode());
            diBangTotal.setId(diBangs.get(i).getId());
            List<DiBang> diBangs2 = new ArrayList<>();
            diBangs2.add(diBangs.get(i));
            diBangTotal.setList(diBangs2);
            //初始化
            if(diBangTotals.size()>=0){
                // 判断是否添加过
                if(!str.contains(diBangs.get(i).getId())){
                    str.add(diBangs.get(i).getId());
                    diBangTotals.add(diBangTotal);
                }else {
                    for (int i1 = 0; i1 < diBangTotals.size(); i1++) {
                        if(diBangTotals.get(i1).getId().equals(diBangs.get(i).getId())){
                            // 不是第一次 添加，直接修改 list 集合
                            List<DiBang> diBangs3 =diBangTotals.get(i1).getList();
                            diBangs3.add(diBangs.get(i));
                            diBangTotals.get(i1).setList(diBangs3);
                        }
                    }
                }

            }

        }


        System.out.println(11);
    }






    public static  List<DiBang>  addBiBing() {
        List<DiBang> diBangs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DiBang diBang = new DiBang();
            if(i==2){
                diBang.setId("地磅100");
                diBang.setStatus("待审核");
                diBang.setCarCode("浙D "+i);
                diBang.setExceptionType("毛重小于皮重");
            }else if(i==4){
                diBang.setId("地磅100");
                diBang.setStatus("审核通过");
                diBang.setCarCode("浙D "+i);
                diBang.setExceptionType("毛重小于皮重");
            } else{
//                diBang.setId("地磅"+i);
                diBang.setId("地磅101");
                diBang.setCarCode("浙D "+i);
                diBang.setStatus("审核通过");
                diBang.setExceptionType("间隔时间短");
            }

            diBangs.add(diBang);
        }
        return diBangs;
    }
}

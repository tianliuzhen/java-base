package com.aaa.javabase.common.demo;

import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/16
 */
public class DiBangTotal {
    private String id;
    private String carCode;
    private List<DiBang> list;

    public List<DiBang> getList() {
        return list;
    }

    public void setList(List<DiBang> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }
}

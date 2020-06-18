package com.aaa.javabase.pattern.creater.prototype.deep;

import java.io.Serializable;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/14
 */
public class DeepCloneTarget implements Serializable,Cloneable {
    private static final long serialVersion = 1L;
    private String cloneName;
    private String cloneClass;

    public DeepCloneTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }
    // 因为该类的属性都是String，因此我们这里使用默认的clone完成即可。
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

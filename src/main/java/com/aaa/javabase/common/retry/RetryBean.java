package com.aaa.javabase.common.retry;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryBean.java  2023/8/6 20:36
 */
public class RetryBean {


    public interface RetryBeanService {
        void say();
    }

    public static class RetryBeanServiceImpl implements RetryBeanService {
        public void say() {
            int a = 1 / 0;
        }
    }
}

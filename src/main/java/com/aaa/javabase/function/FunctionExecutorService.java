package com.aaa.javabase.function;

/**
 * 函数执行公共接口
 *
 * @author liuzhen.tian
 * @version 1.0 FunctionExecutorService.java  2022/7/22 18:53
 */
public interface FunctionExecutorService<R, T> {
    /**
     * 具体的关键字执行入口
     *
     * @param request
     * @return T
     */
    T execute(R request);
}

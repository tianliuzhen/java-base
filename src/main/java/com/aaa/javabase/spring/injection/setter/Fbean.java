package com.aaa.javabase.spring.injection.setter;


import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author liuzhen.tian
 * @version 1.0 Fbean.java  2023/9/28 22:50
 */
@Service
public class Fbean {


    @Inject
    private Cbean cbean;
}

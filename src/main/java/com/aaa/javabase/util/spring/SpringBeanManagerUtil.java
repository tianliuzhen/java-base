package com.aaa.javabase.util.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * spring bean容器管理
 *
 * @author liuzhen.tian
 * @version 1.0 SpringBeanManagerUtil.java  2022/12/31 15:07
 */

@Component
public class SpringBeanManagerUtil {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    public static AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader;

    @PostConstruct
    public void init() {
        annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(defaultListableBeanFactory);
    }

    /**
     * 指定类加载到spring容器中(不推荐)
     * 这个方法不会走：AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
     * 导致无法识别出@Lazy、@Primary，@DependsOn，@Description，@Role 等注解
     *
     * @param clazz
     */
    public void registerSpringBean(Class<?> clazz) {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.registerBeanDefinition(getSimpleClassName(clazz.getName()), beanDefinition.getBeanDefinition());
    }

    /**
     * 指定类加载到spring容器中(推荐)
     *
     * @param beanClass
     * @param name
     */
    public static void registerBeanWithAnnotated(Class<?> beanClass, String name) {
        String beanName = StringUtils.isBlank(name) ? getSimpleClassName(beanClass.getName()) : name;
        annotatedBeanDefinitionReader.registerBean(beanClass, beanName);
    }

    /**
     * 指定删除加载到spring容器中的bean
     *
     * @param clazz
     */
    public void deleteSpringBean(Class<?> clazz) {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.removeBeanDefinition(getSimpleClassName(clazz.getName()));
    }


    /**
     * 获取简易类名，首字母小写
     *
     * @param className
     * @return
     */
    public static String getSimpleClassName(String className) {
        String clazzName = className.substring(className.lastIndexOf(".") + 1);
        return clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
    }
}

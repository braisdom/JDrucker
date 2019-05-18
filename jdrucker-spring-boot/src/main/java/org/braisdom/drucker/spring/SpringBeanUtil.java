package org.braisdom.drucker.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringBeanUtil {

    private static ApplicationContext applicationContext;

    static void setApplicationContext(ApplicationContext applicationContext) {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
}

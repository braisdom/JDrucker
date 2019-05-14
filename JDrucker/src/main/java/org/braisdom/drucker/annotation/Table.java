package org.braisdom.drucker.annotation;

import org.braisdom.drucker.database.RawEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String file();

    Class entityBeanClass() default RawEntity.class;

    String tableName() default "";

    boolean uniqued() default false;

}

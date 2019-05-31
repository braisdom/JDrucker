package org.braisdom.drucker.annotation;

import org.braisdom.drucker.database.SQLExecutionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SQL {

    String id();

    boolean primitive() default false;

    SQLExecutionType executionType();

}

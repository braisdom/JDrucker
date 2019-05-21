package org.braisdom.drucker.annotation;

import org.braisdom.drucker.database.GenericEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String file();

    String tableName() default "";

    Class<? extends GenericEntity> entityClass() default GenericEntity.class;

    boolean uniqued() default false;

}

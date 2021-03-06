package org.braisdom.drucker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableColumn {

    String value() default "";

    boolean updatable() default true;

    boolean onDuplicatedUpdated() default false;
}

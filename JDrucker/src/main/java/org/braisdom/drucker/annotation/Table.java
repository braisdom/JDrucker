package org.braisdom.drucker.annotation;

public @interface Table {

    String value();

    boolean unique() default false;

}

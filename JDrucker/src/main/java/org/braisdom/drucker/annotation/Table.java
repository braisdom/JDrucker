package org.braisdom.drucker.annotation;

public @interface Table {

    String value();

    boolean uniqued() default false;

}

package org.braisdom.drucker.annotation;

public @interface SQL {

    String file();

    String id();

    boolean logged() default true;
}

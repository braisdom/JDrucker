package org.braisdom.drucker.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(XSqlDiscovererRegistrar.class)
public @interface TableDiscoverer {

    String[] classpath();

}

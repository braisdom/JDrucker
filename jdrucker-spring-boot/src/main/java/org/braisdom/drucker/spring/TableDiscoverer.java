package org.braisdom.drucker.spring;

import org.braisdom.drucker.spring.XSqlDiscovererRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(XSqlDiscovererRegistrar.class)
public @interface TableDiscoverer {

    String[] classpath();

}

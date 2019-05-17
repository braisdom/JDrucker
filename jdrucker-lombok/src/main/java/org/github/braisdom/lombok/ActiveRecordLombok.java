package org.github.braisdom.lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE) // 1
@Target(ElementType.TYPE)
public @interface ActiveRecordLombok {

}

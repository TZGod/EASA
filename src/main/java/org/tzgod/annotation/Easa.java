package org.tzgod.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD ,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Easa {
}

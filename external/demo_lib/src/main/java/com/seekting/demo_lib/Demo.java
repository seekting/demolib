package com.seekting.demo_lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by seekting on 17-6-14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Demo {
    String title() default "";

    String desc() default "";
}

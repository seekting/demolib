package com.seekting.demo_lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SubDemo {
    String title() default "";

    String desc() default "";

    int priority() default -1;

}

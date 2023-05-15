package me.totalfreedom.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Subcommand
{
    String name() default "";

    String permission();

    Class<?>[] args() default {};
}

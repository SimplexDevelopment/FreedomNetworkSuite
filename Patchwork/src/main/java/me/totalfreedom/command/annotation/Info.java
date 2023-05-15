package me.totalfreedom.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Info
{
    String name();

    String description() default "This is the default command description.";

    String usage() default "/<command>";

    String[] aliases() default {};
}

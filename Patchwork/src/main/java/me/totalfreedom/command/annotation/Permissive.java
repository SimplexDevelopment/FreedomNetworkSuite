package me.totalfreedom.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Permissive
{
    String perm();

    boolean onlyPlayers() default false;

    String noPerms() default "You do not have permission to use this command.";
}

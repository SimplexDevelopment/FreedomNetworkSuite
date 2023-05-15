package me.totalfreedom.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to mark a method as the command's default method.
 * This is the method that will be run to execute the command when a user inputs /{command}
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Base
{
}

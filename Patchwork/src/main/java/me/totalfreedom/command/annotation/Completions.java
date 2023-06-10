package me.totalfreedom.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker interface which represents a holder for multiple {@link Completion} annotations.
 * <br>
 * <u>This interface is <span color=#ff0000><b>NOT</b></span> intended for implementation and should
 * <span color=#ff0000><b>NOT</b></span> be used.</u>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Completions
{
    /**
     * @return The {@link Completion} annotations.
     */
    Completion[] value();
}

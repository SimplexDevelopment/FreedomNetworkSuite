package fns.patchwork.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker interface which represents a holder for multiple {@link Completion} annotations.
 * <br>
 * <u>This interface is <b>NOT</b> intended for implementation and should
 * <b>NOT</b> be used.</u>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Completions
{
    /**
     * @return The {@link Completion} annotations.
     */
    Completion[] value();
}

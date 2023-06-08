package me.totalfreedom.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a tab completion for a command.
 * <p>
 * This will register at class level, and does not retain method information. As a result, you only need to register the
 * arguments a single time, and it will always be used in tab completions.
 */
@Target(ElementType.METHOD)
@Repeatable(Completions.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Completion
{
    /**
     * An array of possible arguments for this particular index, represented by {@link #index()}.
     *
     * @return An array of possible arguments for tab completion.
     */
    String[] args();

    /**
     * @return The index in which these arguments should be shown.
     */
    int index();
}

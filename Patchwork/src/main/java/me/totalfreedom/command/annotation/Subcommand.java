package me.totalfreedom.command.annotation;

import me.totalfreedom.command.CommandHandler;
import me.totalfreedom.provider.ContextProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be used to mark methods as subcommand methods.
 * Subcommand methods can have custom arguments (current supported arguments can be found in the {@link ContextProvider}),
 * and can also have a custom permission. These subcommands can also be annotated with {@link Completions} to provide
 * tab completions for the subcommand. The subcommand method must be public, and must be in a class that is registered
 * with the {@link CommandHandler}.
 * <br>
 * Tab completions with the {@link Completions} annotation are only supported for subcommands. When registering
 * completions, you only need to define the completion arguments a single time. If there are other methods which
 * function as optional additional arguments for the subcommand, the previously registered arguments will still be
 * present when the user does their tab completion.
 * <br>
 * For example, if you have a subcommand method with the arguments {@code (Player, String)}, and another method which
 * has the arguments {@code (Player, String, String)}, the tab completions for the second method will still have the
 * {@code Player} and {@code String} arguments registered from the first method. You will only need to provide a
 * {@link Completion} for the additional 3rd argument.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subcommand
{
    /**
     * @return The permission to use when executing this subcommand.
     */
    String permission();

    /**
     * @return The arguments, as classes, to use when registering this subcommand.
     */
    Class<?>[] args() default {};
}

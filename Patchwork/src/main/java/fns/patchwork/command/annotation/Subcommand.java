package fns.patchwork.command.annotation;

import fns.patchwork.command.CommandHandler;
import fns.patchwork.provider.ContextProvider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be used to mark methods as subcommand methods. Subcommand methods can have custom arguments
 * <i>(current supported arguments can be found in the {@link ContextProvider})</i>, and can also have a custom
 * permission. These subcommands can also be annotated with {@link Completions} to provide tab completions for the
 * subcommand. The subcommand method must be public, and must be in a class that is registered with the
 * {@link CommandHandler}.
 * <p>
 * Tab completions with the {@link Completions} annotation are only supported for subcommands. When registering
 * completions, you only need to define the completion arguments a single time. If there are other methods which
 * function as optional additional arguments for the subcommand, the previously registered arguments will still be
 * present when the user does their tab completion.
 * <p>
 * For example, if you have a subcommand method with the arguments {@code (Player, String)}, and another method which
 * has the arguments {@code (Player, String, String)}, the tab completions for the second method will still have the
 * {@code Player} and {@code String} arguments registered from the first method. You will only need to provide a
 * {@link Completion} for the additional 3rd argument.
 * <p>
 * Additionally, if the final argument is a String object, the BukkitDelegate will automatically append any additional
 * arguments to the end of the String. For example, if you have a subcommand method with the arguments
 * {@code (Player, String)}, and the user executes the command with the arguments {@code /command playerName arg2 arg3},
 * the {@code String} argument will be {@code "arg2 arg3"}. This allows for us to use a String at the end of our
 * subcommand arguments to allow for the user to input a reason.
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

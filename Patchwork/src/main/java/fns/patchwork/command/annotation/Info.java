package fns.patchwork.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This interface holds the information for each command. This annotation defines the command's name, description,
 * usage, and aliases. Commands <b><u>must</u></b> have this annotation present to be registered with the handler.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Info
{
    /**
     * Technically, this is the only required value you must supply yourself. However, it is HIGHLY recommended you
     * supply the other optional values as well, for better customization of your command.
     *
     * @return The command's name.
     */
    String name();

    /**
     * By default, this is set to <u>"This is the default command description."</u>
     *
     * @return The command's description.
     */
    String description() default "This is the default command description.";

    /**
     * By default, this is set to <u>"/&lt;command&gt;"</u>
     *
     * @return The command's usage.
     */
    String usage() default "/<command>";

    /**
     * By default, this returns an empty array.
     *
     * @return The command's aliases.
     */
    String[] aliases() default {};
}

package me.totalfreedom.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation holds the permission information for each command. This annotation defines the command's permission,
 * whether it is only for players, and the message to send if the sender does not have permission to use the command.
 * <p>
 * Classes <u><b>MUST</b></u> have this annotation present to be registered with the handler.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permissive
{
    /**
     * @return The command's permission.
     */
    String perm();

    /**
     * By default, this is set to <u>false</u>.
     *
     * @return True if the command is only for players, false otherwise.
     */
    boolean onlyPlayers() default false;

    /**
     * By default, this is set to <u>"You do not have permission to use this command."</u>
     *
     * @return The message to send if the sender does not have permission to use the command.
     */
    String noPerms() default "You do not have permission to use this command.";
}

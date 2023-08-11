package fns.datura.cmd;

import fns.datura.Datura;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "commandspy", description = "Spy on commands executed by players.", usage = "/commandspy")
@Permissive(perm = "datura.commandspy", onlyPlayers = true)
public class CommandSpyCommand extends Commander
{

    /**
     * Initializes this command object. The provided {@link JavaPlugin} should be the plugin which contains the
     * command.
     * <p>
     * This constructor will automatically register all subcommands and completions for this command. It will also
     * automatically infer all required information from the provided {@link Info} and {@link Permissive} annotations.
     *
     * @param plugin The plugin which contains this command.
     */
    public CommandSpyCommand(@NotNull final Datura plugin)
    {
        super(plugin);
    }

    @Base
    public void commandSpy(final Player sender)
    {
        final var commandSpy = ((Datura) getPlugin()).
            getCommandSpy();

        final var uuid = sender.
            getUniqueId();

        if (commandSpy.isSpying(uuid))
        {
            commandSpy.stop(uuid);
            sender.sendPlainMessage("CommandSpy disabled.");
        }
        else
        {
            commandSpy.spy(uuid);
            sender.sendPlainMessage("CommandSpy enabled.");
        }
    }
}

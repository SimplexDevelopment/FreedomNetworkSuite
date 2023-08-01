package me.totalfreedom.datura.cmd;

import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.Base;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.datura.Datura;
import org.bukkit.command.CommandSender;
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
    public CommandSpyCommand(@NotNull JavaPlugin plugin)
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

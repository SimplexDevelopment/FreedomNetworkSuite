package fns.patchwork.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles the registration of commands. The plugin which initializes this class should be the plugin that is
 * registering the commands.
 */
public class CommandHandler
{
    /**
     * The plugin that this command handler is registered to.
     * <br>
     * This should be the plugin instance which is trying to register the commands.
     */
    private final JavaPlugin plugin;

    /**
     * Creates a new command handler.
     *
     * @param plugin The plugin that this command handler is registered to.
     */
    public CommandHandler(final JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Registers a command. This method will automatically delegate the command information to the Bukkit API and
     * register with the {@link CommandMap}.
     *
     * @param command The command to register.
     * @param <T>     The type of the command.
     */
    public <T extends Commander> void registerCommand(final T command)
    {
        final BukkitDelegate delegate = new BukkitDelegate(command);

        Bukkit.getCommandMap()
              .register(plugin.getName(), delegate);
    }
}

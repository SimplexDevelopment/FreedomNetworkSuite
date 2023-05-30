package me.totalfreedom.command;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler
{
    private final JavaPlugin plugin;

    public CommandHandler(final JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    public <T extends Commander> void registerCommand(final T command)
    {
        final BukkitDelegate delegate = new BukkitDelegate(command);

        Bukkit.getCommandMap()
              .register(plugin.getName(), delegate);
    }
}

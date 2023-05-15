package me.totalfreedom.command;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler
{
    private final JavaPlugin plugin;

    public CommandHandler(JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    // TODO: Figure out how to use CommandExecutor and TabCompleter.
    //       We need to find a way to resolve PluginCommands so we can
    //       set the executor and tab completer as necessary.
    //       OR we need to find an alternative way to process tab completions.
    public <T extends CommandBase> void registerCommand(T command) {
        BukkitDelegator delegate = new BukkitDelegator(plugin, command);

        Bukkit.getCommandMap().register(plugin.getName(), delegate);
    }
}

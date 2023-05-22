package me.totalfreedom.command;

import me.totalfreedom.api.Context;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.provider.ContextProvider;
import me.totalfreedom.utils.FreedomLogger;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

public class BukkitDelegator extends Command implements PluginIdentifiableCommand
{
    private final JavaPlugin plugin;
    private final CommandBase command;
    private final boolean noConsole;

    BukkitDelegator(final JavaPlugin plugin, final CommandBase command)
    {
        super(command.getInfo().name());
        this.plugin = plugin;
        this.command = command;
        this.setDescription(command.getInfo().description());
        this.setUsage(command.getInfo().usage());
        this.setPermission(command.getPerms().perm());
        this.setAliases(Arrays.asList(command.getInfo().aliases()));
        this.permissionMessage(Component.text(command.getPerms().noPerms()));
        this.noConsole = command.getPerms().onlyPlayers();
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender,
                           @NotNull final String commandLabel,
                           @NotNull final String[] args)
    {
        if (commandLabel.isEmpty() || !commandLabel.equalsIgnoreCase(getName()))
            return false;

        if (sender instanceof ConsoleCommandSender && noConsole)
        {
            sender.sendMessage(Component.text("This command can only be run by players."));
            return true;
        }

        if (getPermission() != null && !sender.hasPermission(getPermission()))
        {
            Component permissionMessage = permissionMessage();
            if (permissionMessage == null)
                permissionMessage = Component.text("You do not have permission to use this command.");
            sender.sendMessage(permissionMessage);
            return true;
        }

        if (args.length > 0)
        {
            final ContextProvider provider = new ContextProvider();
            final Set<Subcommand> nodes = command.getSubcommands().keySet();
            for (final Subcommand node : nodes)
            {
                final Class<?>[] argTypes = node.args();
                if (argTypes.length != args.length)
                    continue;

                Object[] objects = new Object[0];

                for (int i = 0; i < argTypes.length; i++)
                {
                    final Class<?> argType = argTypes[i];
                    final String arg = args[i];
                    if (argType == String.class)
                        continue;

                    final Context<?> context = () -> provider.fromString(arg);
                    if (!argType.isInstance(context.get()))
                    {
                        throw new IllegalStateException();
                    }
                    objects = Arrays.copyOf(objects, objects.length + 1);
                    objects[objects.length - 1] = context.get();
                }
                try
                {
                    command.getSubcommands().get(node).invoke(command, objects);
                } catch (Exception ex)
                {
                    FreedomLogger.getLogger("Patchwork")
                            .error(ex);
                }
            }

            return false;
        }

        if (command.getBaseMethodPair() != null)
        {
            try
            {
                command.getBaseMethodPair().value().invoke(command, sender);
            } catch (Exception ex)
            {
                FreedomLogger.getLogger("Patchwork")
                        .error(ex);
            }
        }

        return true;
    }

    @Override
    public @NotNull Plugin getPlugin()
    {
        return this.plugin;
    }
}

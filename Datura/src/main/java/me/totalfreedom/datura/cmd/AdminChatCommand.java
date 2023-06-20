package me.totalfreedom.datura.cmd;

import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.Base;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class AdminChatCommand extends Commander
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
    protected AdminChatCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void onAdminChat(final CommandSender sender) {
        
    }
}

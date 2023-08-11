package fns.datura.cmd;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "")
@Permissive(perm = "")
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
    public AdminChatCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void onAdminChat(final CommandSender sender)
    {
        if (!(sender instanceof Player)) return;

        final Player player = (Player) sender;

        Shortcuts.provideModule(Patchwork.class)
                 .getAdminChatDisplay()
                 .toggleChat(player);

        final boolean toggled = Shortcuts.provideModule(Patchwork.class)
                                         .getAdminChatDisplay()
                                         .isToggled(player);

        String message = "Toggled adminchat ";

        message += toggled ? "on" : "off";

        player.sendPlainMessage(message + ".");
    }

    // String here will automatically have all additional args appended to it :)
    @Subcommand(permission = "patchwork.adminchat", args = {String.class})
    public void sendMessage(final CommandSender sender, final String message)
    {
        Shortcuts.provideModule(Patchwork.class)
                 .getAdminChatDisplay()
                 .adminChatMessage(sender, Component.text(message));
    }
}

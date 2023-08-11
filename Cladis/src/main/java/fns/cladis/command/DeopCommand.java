package fns.cladis.command;

import fns.cladis.Cladis;
import fns.cladis.NMLink;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import nl.chimpgamer.networkmanager.api.models.permissions.Group;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(
    name = "deop",
    description = "Deop a player.",
    usage = "/deop <player>"
)
@Permissive(perm = "cladis.deop")
@Completion(index = 0, args = {"%player%"})
public class DeopCommand extends Commander
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
    protected DeopCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "cladis.deop", args = {Player.class})
    public void deop(final CommandSender sender, final Player player)
    {
        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final Group opGroup = nmLink.deopGroup();

        if (opGroup == null)
        {
            sender.sendPlainMessage("Unable to deop player. Please contact an administrator.");
            return;
        }

        if (nmLink.isDeop(player.getUniqueId()))
        {
            sender.sendPlainMessage("Player is not op.");
            return;
        }

        nmLink.getPlayerGroups(player.getUniqueId()).remove(opGroup);

        sender.sendPlainMessage("Player deopped.");
        player.sendPlainMessage("You have been deopped!");
    }
}

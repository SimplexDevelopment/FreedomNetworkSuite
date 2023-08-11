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
    name = "op",
    description = "Op a player.",
    usage = "/op <player>"
)
@Permissive(perm = "cladis.op")
@Completion(index = 0, args = {"%player%"})
public class OpCommand extends Commander
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
    public OpCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "cladis.op", args = {Player.class})
    public void op(@NotNull final CommandSender sender, final Player player)
    {
        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final Group opGroup = nmLink.locateGroup("fake_op").orElse(null);

        if (opGroup == null)
        {
            sender.sendPlainMessage("Unable to op player. Please contact an administrator.");
            return;
        }

        if (nmLink.isOp(player.getUniqueId()))
        {
            sender.sendPlainMessage("Player is already op.");
            return;
        }


        nmLink.getPermissionsPlayer(player.getUniqueId()).getGroups().add(opGroup);

        sender.sendPlainMessage("You have opped " + player.getName() + ".");
        player.sendPlainMessage("You have been opped.");
    }
}

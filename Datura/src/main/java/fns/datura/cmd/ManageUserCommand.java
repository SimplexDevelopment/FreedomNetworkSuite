package fns.datura.cmd;

import fns.datura.perms.PermissionNodeBuilder;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import fns.patchwork.security.Node;
import fns.patchwork.security.NodeType;
import fns.patchwork.security.PermissionHolder;
import fns.patchwork.user.User;
import java.time.Duration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "manageuser", description = "Manage a user's permissions", usage = "/manageuser <username> <info | (add "
    + "| remove <permission>)>", aliases = {"mu", "userdata", "ud", "usermanager", "um"})
@Permissive(perm = "datura.manageuser")
@Completion(index = 0, args = {"%player%"})
@Completion(index = 1, args = {"info", "add", "remove"})
@Completion(index = 2, args = {"<permission>"})
public class ManageUserCommand extends Commander
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
    public ManageUserCommand(final @NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.manageuser", args = {Player.class, String.class, String.class, Long.class})
    public void manageUser(final CommandSender sender, final Player player, final String addOrRemove,
                           final String permission, final long duration)
    {
        final PermissionHolder user = Shortcuts.getUser(player);
        final Node node = new PermissionNodeBuilder().key(permission)
                                                     .type(NodeType.PERMISSION)
                                                     .expiry(Duration.ofMinutes(duration)
                                                                     .getSeconds() + System.currentTimeMillis())
                                                     .build();
        ifElse(addOrRemove, user, node);
    }

    @Subcommand(permission = "datura.manageuser", args = {Player.class, String.class, String.class})
    public void manageUser(final CommandSender sender, final Player player, final String addOrRemove,
                           final String permission)
    {
        final PermissionHolder user = Shortcuts.getUser(player);
        final Node node = new PermissionNodeBuilder().key(permission).type(NodeType.PERMISSION).build();
        ifElse(addOrRemove, user, node);
    }

    @Subcommand(permission = "datura.manageuser", args = {Player.class, String.class})
    public void userInfo(final CommandSender sender, final Player player, final String info)
    {
        final User user = Shortcuts.getUser(player);
        if (info.equalsIgnoreCase("info"))
        {
            final StringBuilder permissions = new StringBuilder();
            user.getEffectivePermissions().forEach(node -> permissions.append(node.getPermission()));
            final String text = """
                                User: %s
                                Group: %s
                                Permissions: %s""".formatted(user.getName(), user.getUserData().getGroup(),
                                                             permissions.toString());
            sender.sendPlainMessage(text);
        }
    }

    private void ifElse(final String addOrRemove, final PermissionHolder user, final Node node)
    {
        if (addOrRemove.equalsIgnoreCase("add"))
        {
            user.addPermission(node);
        }
        else if (addOrRemove.equalsIgnoreCase("remove"))
        {
            user.removePermission(node);
        }
    }
}

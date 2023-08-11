package fns.cladis.command;

import fns.cladis.Cladis;
import fns.cladis.NMLink;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import java.util.Map;
import nl.chimpgamer.networkmanager.api.models.permissions.Group;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(
    name = "modstaff",
    description = "Mod a player.",
    usage = """
            /modstaff
            /modstaff info <player>
            /modstaff <add | remove> <player> <group>
            /modstaff <promote | demote> <player>
            """,
    aliases = {"ms", "saconfig", "adminlist", "al"}
)
@Permissive(perm = "cladis.modstaff")
@Completion(index = 0, args = {"info", "add", "remove", "promote", "demote"})
@Completion(index = 1, args = {"%player%"})
@Completion(index = 2, args = {"<group>"})
public class ModStaffCommand extends Commander
{

    public static final String USER_NOT_FOUND = "That user does not exist!";

    /**
     * Initializes this command object. The provided {@link JavaPlugin} should be the plugin which contains the
     * command.
     * <p>
     * This constructor will automatically register all subcommands and completions for this command. It will also
     * automatically infer all required information from the provided {@link Info} and {@link Permissive} annotations.
     *
     * @param plugin The plugin which contains this command.
     */
    public ModStaffCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void base(final CommandSender sender)
    {
        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final StringBuilder stringBuilder = new StringBuilder();
        nmLink.getCacheManager()
              .getCachedPlayers()
              .getPlayers()
              .forEach((u, p) ->
                       {
                           if (nmLink.isAdmin(u) || nmLink.isSenior(u))
                           {
                               stringBuilder.append(nmLink.getPermissionsPlayer(u)
                                                          .getPrimaryGroup()
                                                          .getPrefixes()
                                                          .values()
                                                          .stream()
                                                          .findFirst()
                                                          .orElseGet(() -> "[Admin]"))
                                            .append(" ")
                                            .append(p.getName()).append(", ");
                           }
                       });

        sender.sendPlainMessage(stringBuilder.toString());
    }

    @Subcommand(permission = "cladis.modstaff.info", args = {String.class, Player.class})
    public void info(final CommandSender sender, final String info, final Player player)
    {
        if (!info.equalsIgnoreCase("info"))
            return;

        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final StringBuilder stringBuilder = new StringBuilder();
        final nl.chimpgamer.networkmanager.api.models.player.Player nmPlayer =
            nmLink.getPlayer(player.getUniqueId()).orElse(null);

        if (nmPlayer == null)
        {
            sender.sendPlainMessage(USER_NOT_FOUND);
            return;
        }

        stringBuilder.append("Player: ")
                     .append(player.getName())
                     .append("\n");
        stringBuilder.append("UUID: ")
                     .append(nmPlayer.getUuid())
                     .append("\n");
        stringBuilder.append("Rank: ")
                     .append(nmLink.getPermissionsPlayer(player.getUniqueId()).getPrimaryGroup().getName())
                     .append("\n");
        stringBuilder.append("IP: ")
                     .append(nmPlayer.getIp())
                     .append("\n");
        stringBuilder.append("Playtime: ")
                     .append(nmPlayer.getPlaytime())
                     .append("\n");
        stringBuilder.append("Last Online: ")
                     .append(nmPlayer.getLastlogin())
                     .append("\n");

        sender.sendPlainMessage(stringBuilder.toString());
    }

    @Subcommand(permission = "cladis.modstaff.modify", args = {String.class, Player.class, String.class})
    public void modify(final CommandSender sender, final String arg, final Player target, final String group)
    {
        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final nl.chimpgamer.networkmanager.api.models.player.Player nmPlayer =
            nmLink.getPlayer(target.getUniqueId()).orElse(null);

        if (nmPlayer == null)
        {
            sender.sendPlainMessage(USER_NOT_FOUND);
            return;
        }

        final Map.Entry<Integer, Group> g =
            nmLink.getLoadedGroups().entrySet().stream().filter(e -> e.getValue().getName().equalsIgnoreCase(group))
                  .findFirst().orElse(null);

        if (g == null)
        {
            sender.sendPlainMessage("That group does not exist!");
            return;
        }

        if (sender instanceof Player player && (nmLink.getPermissionsPlayer(player.getUniqueId())
                                                      .getPrimaryGroup()
                                                      .getId() <= g.getKey()))
        {
            sender.sendPlainMessage("You cannot modify a player to a higher rank than yourself!");
            return;
        }

        if (arg.equalsIgnoreCase("add"))
        {
            nmLink.getPermissionsPlayer(target.getUniqueId()).getGroups().add(g.getValue());
            sender.sendPlainMessage("Added " + target.getName() + " to " + group);
        }
        else if (arg.equalsIgnoreCase("remove"))
        {
            nmLink.getPermissionsPlayer(target.getUniqueId()).getGroups().remove(g.getValue());
            sender.sendPlainMessage("Removed " + target.getName() + " from " + group);
        }
        else
        {
            sender.sendPlainMessage("Invalid argument!");
        }
    }

    @Subcommand(permission = "cladis.modstaff.track", args = {String.class, Player.class})
    public void track(final CommandSender sender, final String arg, final Player target)
    {
        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final nl.chimpgamer.networkmanager.api.models.player.Player nmPlayer =
            nmLink.getPlayer(target.getUniqueId()).orElse(null);

        if (nmPlayer == null)
        {
            sender.sendPlainMessage(USER_NOT_FOUND);
            return;
        }

        final Group primary = nmLink.getPermissionsPlayer(target.getUniqueId()).getPrimaryGroup();
        final int groupLevel = primary.getId();

        if (arg.equalsIgnoreCase("promote"))
        {
            final Group promo = nmLink.getLoadedGroups().get(groupLevel + 1);
            if (promo == null)
            {
                sender.sendPlainMessage("That user is already at the highest rank!");
                return;
            }

            nmLink.getPermissionsPlayer(target.getUniqueId()).getGroups().remove(primary);
            nmLink.getPermissionsPlayer(target.getUniqueId())
                  .getGroups()
                  .add(promo);
            sender.sendPlainMessage("Promoted " + target.getName());
        }
        else if (arg.equalsIgnoreCase("demote"))
        {
            final Group demo = nmLink.getLoadedGroups().get(groupLevel - 1);
            if (demo == null)
            {
                sender.sendPlainMessage("That user is already at the lowest rank!");
                return;
            }

            nmLink.getPermissionsPlayer(target.getUniqueId()).getGroups().remove(primary);
            nmLink.getPermissionsPlayer(target.getUniqueId())
                  .getGroups()
                  .add(demo);
            sender.sendPlainMessage("Demoted " + target.getName());
        }
        else
        {
            sender.sendPlainMessage("Invalid argument!");
        }
    }
}

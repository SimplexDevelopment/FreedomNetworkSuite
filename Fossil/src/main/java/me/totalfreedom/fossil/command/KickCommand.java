package me.totalfreedom.fossil.command;

import me.totalfreedom.command.CommandBase;
import me.totalfreedom.command.annotation.Base;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.fossil.Fossil;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Info(name = "kick", description = "Kick a player", usage = "/<command> <player>")
@Permissive(perm = "fossil.kick")
public class KickCommand extends CommandBase
{
    public KickCommand(final Fossil plugin) {
        super(plugin);
    }

    @Base
    public void run(final CommandSender sender) {
        sender.sendMessage(Component.text("You must specify a player to kick."));
    }

    @Subcommand(permission = "fossil.kick", args = {Player.class, String.class})
    public void kickPlayer(final Player player, final String string) {
        player.kick(Component.text(string));
    }
}

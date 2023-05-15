package me.totalfreedom.fossil.command;

import me.totalfreedom.command.*;
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
    public KickCommand(Fossil plugin) {
        super(plugin);
    }

    @Base
    public void run(CommandSender sender) {
        sender.sendMessage(Component.text("You must specify a player to kick."));
    }

    @Subcommand(permission = "fossil.kick", args = {Player.class, String.class})
    public void kickPlayer(Player player, String string) {
        player.kick(Component.text(string));
    }

    // TODO: Write the code to make this work properly.
    @Subcommand(name = "info", permission = "fossil.kick.info", args = {Player.class})
    public void playerinfo(Player player) {

    }
}

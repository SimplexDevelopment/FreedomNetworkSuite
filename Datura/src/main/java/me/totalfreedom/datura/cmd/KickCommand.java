package me.totalfreedom.datura.cmd;

import me.totalfreedom.command.CommandBase;
import me.totalfreedom.command.annotation.Completion;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.datura.Datura;
import org.bukkit.entity.Player;

@Completion(args = {"%player%"}, index = 0)
@Info(name = "kick", description = "Kick a player from the server.", usage = "/kick <player>")
@Permissive(perm = "datura.kick")
public class KickCommand extends CommandBase
{
    protected KickCommand(final Datura plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.kick", args = {Player.class})
    public void kick(final Player player)
    {
        player.kickPlayer("You have been kicked from the server.");
    }
}

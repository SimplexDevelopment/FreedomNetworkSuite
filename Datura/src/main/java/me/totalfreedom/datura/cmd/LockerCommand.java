package me.totalfreedom.datura.cmd;

import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.Completion;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.datura.Datura;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Info(name = "locker", description = "Lock a player, preventing them from interacting with their game client.",
    usage = "/locker <player> <on|off>", aliases = {"lock", "lockup"})
@Permissive(perm = "datura.locker")
public final class LockerCommand extends Commander
{
    public LockerCommand(final @NotNull Datura plugin)
    {
        super(plugin);
    }

    @Completion(args = {"%player%"}, index = 0)
    @Completion(args = {"on", "off"}, index = 1)
    @Subcommand(permission = "datura.locker", args = {Player.class, String.class})
    public void lockPlayer(final CommandSender sender, final Player player, final String string)
    {
        if (string.equalsIgnoreCase("on"))
        {
            ((Datura) getPlugin()).getLocker()
                                  .lock(player);

            sender.sendPlainMessage("Locked " + player.getName() + ".");
        } else if (string.equalsIgnoreCase("off"))
        {
            ((Datura) getPlugin()).getLocker()
                                  .unlock(player);
            sender.sendPlainMessage("Unlocked " + player.getName() + ".");
        }
    }
}

package me.totalfreedom.datura.cmd;

import me.totalfreedom.base.Shortcuts;
import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.Completion;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.datura.Datura;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "halt", description = "Halt a single player, or every player.", usage = "/<command> <player | all> <on, off>")
@Permissive(perm = "datura.halt")
public class HaltCommand extends Commander
{
    private final Datura plugin = Shortcuts.provideModule(Datura.class);

    /**
     * Initializes this command object. The provided {@link JavaPlugin} should be the plugin which contains the
     * command.
     * <p>
     * This constructor will automatically register all subcommands and completions for this command. It will also
     * automatically infer all required information from the provided {@link Info} and {@link Permissive} annotations.
     *
     * @param plugin The plugin which contains this command.
     */
    protected HaltCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }


    @Completion(index = 0, args = {"%player%", "all"})
    @Completion(index = 1, args = {"on", "off"})
    @Subcommand(permission = "datura.halt", args = {Player.class, String.class})
    public void haltPlayer(final CommandSender sender, final Player target, final String toggle)
    {
        if (toggle.equalsIgnoreCase("on"))
        {
            plugin.getHalter()
                  .halt(target.getUniqueId());

            target.sendPlainMessage("You have been frozen!");
            sender.sendPlainMessage("You have halted " + target.getName() + ".");
        } else if (toggle.equalsIgnoreCase("off"))
        {
            plugin.getHalter()
                  .stop(target.getUniqueId());

            target.sendPlainMessage("You have been unfrozen!");
            sender.sendPlainMessage("You have unhalted " + target.getName() + ".");
        }
    }

    // No completion needed here since it's already registered.
    @Subcommand(permission = "datura.halt.all", args = {String.class, String.class})
    public void haltAll(final CommandSender sender, final String all, final String toggle)
    {
        if (all.equalsIgnoreCase("all"))
        {
            if (toggle.equalsIgnoreCase("on"))
            {
                Bukkit.getServer()
                      .getOnlinePlayers()
                      .forEach(player -> plugin.getHalter()
                                               .halt(player.getUniqueId()));

                final Component message = sender.name()
                                                .append(Component.text(": Freezing all players"))
                                                .color(NamedTextColor.AQUA);

                Bukkit.broadcast(message);
                sender.sendPlainMessage("All players have been halted.");
            } else if (toggle.equalsIgnoreCase("off"))
            {
                plugin.getHalter()
                      .clear();

                Bukkit.broadcast(Component.text("All players have been unfrozen!", NamedTextColor.AQUA));
                sender.sendPlainMessage("All players have been unhalted.");
            }
        }
    }
}

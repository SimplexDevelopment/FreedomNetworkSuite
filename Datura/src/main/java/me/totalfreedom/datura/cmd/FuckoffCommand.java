package me.totalfreedom.datura.cmd;

import me.totalfreedom.base.Shortcuts;
import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.*;
import me.totalfreedom.datura.Datura;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "fuckoff", description = "You'll never even see it coming - repeatedly push players away from you until command is untoggled.", usage = "/fuckoff <on|off> [radius]")
@Permissive(perm = "datura.fuckoff")
@Completion(args = {"on", "off"}, index = 0)
@Completion(args = {"[radius]"}, index = 1)
public class FuckoffCommand extends Commander
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
    public FuckoffCommand(@NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.fuckoff", args = {String.class})
    public void fuckOff(final CommandSender sender, final String toggle)
    {
        execute(sender, toggle, 3);
    }

    @Subcommand(permission = "datura.fuckoff", args = {String.class, Integer.class})
    public void fuckOff(final CommandSender sender, final String toggle, final Integer radius)
    {
        execute(sender, toggle, radius);
    }

    private void execute(final CommandSender sender, final String toggle, final int radius)
    {
        if (!(sender instanceof Player))
        {
            sender.sendPlainMessage("You have to be a player to perform this command.");
            return;
        }
        final var player = (Player) sender;

        if (toggle.equalsIgnoreCase("on"))
        {
            plugin.getFuckoff().
                    add(player, radius);

            sender.sendPlainMessage("FuckOff enabled.");
        } else if (toggle.equalsIgnoreCase("off"))
        {
            plugin.getFuckoff().
                    remove(player);

            sender.sendPlainMessage("FuckOff disabled.");
        }
    }
}

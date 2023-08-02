package fns.datura.cmd;

import fns.datura.Datura;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "fuckoff", description = "You'll never even see it coming - repeatedly push players away from you until command is untoggled.", usage = "/fuckoff <on|off> [radius]")
@Permissive(perm = "datura.fuckoff", onlyPlayers = true)
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
    public FuckoffCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.fuckoff", args = {String.class})
    public void fuckOff(final Player sender, final String toggle)
    {
        execute(sender, toggle, 15);
    }

    @Subcommand(permission = "datura.fuckoff", args = {String.class, Integer.class})
    public void fuckOff(final Player sender, final String toggle, final Integer radius)
    {
        execute(sender, toggle, radius);
    }

    private void execute(final Player sender, final String toggle, final int radius)
    {
        if (toggle.equalsIgnoreCase("on"))
        {
            plugin.getFuckoff().
                    add(sender, radius);

            sender.sendPlainMessage("FuckOff enabled.");
        } else if (toggle.equalsIgnoreCase("off"))
        {
            plugin.getFuckoff().
                    remove(sender);

            sender.sendPlainMessage("FuckOff disabled.");
        }
    }
}

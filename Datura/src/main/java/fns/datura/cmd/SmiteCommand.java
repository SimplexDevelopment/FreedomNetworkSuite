package fns.datura.cmd;

import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "smite", description = "Smite a player.", usage = "/smite <player>", aliases = {"sm"})
@Permissive(perm = "datura.smite")
@Completion(index = 0, args = {"%player%"})
public class SmiteCommand extends Commander
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
    public SmiteCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.smite", args = {Player.class})
    public void smite(final CommandSender sender, final Player player)
    {
        final double size = 5D;
        for (int i = 0; i < size * size * size; i++)
        {
            final double x = i % size;
            final double y = (i / size) % size;
            final double z = (i / size / size) % size;

            final Location location = player.getLocation()
                                            .clone()
                                            .add(x - size / 2, y - size / 2, z - size / 2);

            player.getWorld()
                  .strikeLightning(location);
        }
    }
}

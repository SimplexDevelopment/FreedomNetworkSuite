package fns.datura.cmd;

import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import fns.patchwork.utils.Tagged;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "clearentities", description = "Clears all entities in the world.", usage = "/<command> [world]",
    aliases = {"ew", "ce", "entitywipe", "entityclear", "ec"})
@Permissive(perm = "datura.clearentities")
@Completion(index = 0, args = {"%world%"})
public class ClearEntitiesCommand extends Commander
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
    public ClearEntitiesCommand(final @NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void clearEntities(final CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            sender.sendPlainMessage("You must specify a world to clear entities from.");
            return;
        }

        final Player player = (Player) sender;

        int i = 0;
        for (final Entity entity : player.getWorld().getEntities())
        {
            if (!Tagged.NON_WIPEABLE.isTagged(entity.getType()))
            {
                entity.remove();
                i++;
            }
        }
        sender.sendPlainMessage("Cleared " + i + " entities.");
    }

    @Subcommand(permission = "datura.clearentities", args = {World.class})
    public void clearEntities(final CommandSender sender, final World world)
    {
        int i = 0;
        for (final Entity entity : world.getEntities())
        {
            if (!Tagged.NON_WIPEABLE.isTagged(entity.getType()))
            {
                entity.remove();
                i++;
            }
        }
        sender.sendPlainMessage("Cleared " + i + " entities.");
    }
}

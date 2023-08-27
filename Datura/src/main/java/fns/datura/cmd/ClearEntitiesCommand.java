/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

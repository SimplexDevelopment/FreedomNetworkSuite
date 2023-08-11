/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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


import fns.datura.Datura;
import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "cleardrops", description = "Clears all item drops in the world" + ".", usage = "/<command>", aliases =
    {"cd", "clearitems", "ci", "wipeitems", "wi", "removedrops", "rd"})
@Permissive(perm = "datura.cleardrops")
@Completion(index = 0, args = {"%world%"})
public class ClearDropsCommand extends Commander
{
    /**
     * Initializes this command object. The provided {@link JavaPlugin}
     * should be the plugin which contains the
     * command.
     * <p>
     * This constructor will automatically register all subcommands and
     * completions for this command. It will also
     * automatically infer all required information from the provided
     * {@link Info} and {@link Permissive} annotations.
     *
     * @param plugin The plugin which contains this command.
     */
    public ClearDropsCommand(final @NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void clearDrops(final CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            sender.sendPlainMessage("You must define a world.");
            return;
        }

        final Player player = (Player) sender;
        Shortcuts.provideModule(Patchwork.class)
                 .getExecutor()
                 .delayedExecutor(Shortcuts.provideModule(Datura.class), 20 * 10L)
                 .execute(() ->
                          {
                              int i = 0;
                              for (final Entity entity : player.getWorld().getEntities())
                              {
                                  if (entity instanceof Item)
                                  {
                                      entity.remove();
                                      i++;
                                  }
                              }

                              player.sendPlainMessage("Successfully removed " + i + " items.");
                          });
    }

    @Subcommand(permission = "datura.cleardrops", args = {World.class})
    public void clearDrops(final CommandSender sender, final World world)
    {
        Shortcuts.provideModule(Patchwork.class)
                 .getExecutor()
                 .delayedExecutor(Shortcuts.provideModule(Datura.class), 20 * 10L)
                 .execute(() ->
                          {
                              int i = 0;
                              for (final Entity entity : world.getEntities())
                              {
                                  if (entity instanceof Item)
                                  {
                                      entity.remove();
                                      i++;
                                  }
                              }

                              sender.sendPlainMessage("Successfully removed " + i + " items.");
                          });
    }
}

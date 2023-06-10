/*
 * Copyright (c) 2023 TotalFreedom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.totalfreedom.fossil.cmd;

import me.totalfreedom.command.Commander;
import me.totalfreedom.command.annotation.Base;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.utils.kyori.FreedomMiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "cake", description = "Gives everyone cake and broadcasts a message.", usage = "/cake")
@Permissive(perm = "fossil.cake")
public class CakeCommand extends Commander
{
    protected CakeCommand(final @NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Base
    public void broadcastCake(final CommandSender sender)
    {
        Bukkit.broadcast(FreedomMiniMessage.deserialize(true,
                "<rainbow>But there's no sense crying over every mistake. You just keep on trying till you run out of " +
                        "cake.</rainbow>"));

        final ItemStack stack = new ItemStack(Material.CAKE, 1);
        final ItemMeta meta = stack.getItemMeta();
        meta.displayName(FreedomMiniMessage.deserialize(true, "<dark_gray>The <white>Lie"));
        stack.setItemMeta(meta);

        Bukkit.getOnlinePlayers()
              .forEach(player -> player.getInventory()
                                       .addItem(stack));
    }
}

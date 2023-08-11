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
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "cage", description = "Cage a player.",
        usage = "/cage <player> <on|off> [material]")
@Permissive(perm = "datura.cage")
@Completion(args = {"%player%"}, index = 0)
@Completion(args = {"on", "off"}, index = 1)
@Completion(args = {"[material]"}, index = 2)
public class CageCommand extends Commander
{
    public CageCommand(final @NotNull JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "datura.cage", args = {Player.class, String.class})
    public void cagePlayer(final CommandSender sender, final Player player, final String string)
    {
        if (string.equalsIgnoreCase("on"))
        {
            ((Datura) getPlugin()).getCager()
                                  .cagePlayer(player.getUniqueId());
            sender.sendPlainMessage("Caged " + player.getName() + ".");
        } else if (string.equalsIgnoreCase("off"))
        {
            ((Datura) getPlugin()).getCager()
                                  .uncagePlayer(player.getUniqueId());
            sender.sendPlainMessage("Liberated " + player.getName() + ".");
        }
    }

    @Subcommand(permission = "datura.cage.custom", args = {Player.class, String.class, Material.class})
    public void cagePlayer(final CommandSender sender, final Player player, final String string,
                           final Material material)
    {
        if (string.equalsIgnoreCase("on"))
        {
            Shortcuts.provideModule(Datura.class)
                     .getCager()
                     .cagePlayer(player.getUniqueId(), material);
            sender.sendPlainMessage("Caged " + player.getName() + ".");
        } else if (string.equalsIgnoreCase("off"))
        {
            Shortcuts.provideModule(Datura.class)
                     .getCager()
                     .uncagePlayer(player.getUniqueId());
            sender.sendPlainMessage("Liberated " + player.getName() + ".");
        }
    }
}

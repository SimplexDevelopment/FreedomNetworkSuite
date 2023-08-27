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

import fns.datura.Datura;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Info(name = "locker", description = "Lock a player, preventing them from interacting with their game client.",
        usage = "/locker <player> <on|off>", aliases = {"lock", "lockup"})
@Permissive(perm = "datura.locker")
@Completion(args = {"%player%"}, index = 0)
@Completion(args = {"on", "off"}, index = 1)
public final class LockerCommand extends Commander
{
    public LockerCommand(final @NotNull Datura plugin)
    {
        super(plugin);
    }

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

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

package fns.cladis.command;

import fns.cladis.Cladis;
import fns.cladis.NMLink;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import java.util.UUID;
import nl.chimpgamer.networkmanager.api.models.permissions.Group;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(
    name = "deop",
    description = "Deop a player.",
    usage = "/deop <player>"
)
@Permissive(perm = "cladis.deop")
@Completion(index = 0, args = {"%player%"})
public class DeopCommand extends Commander
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
    protected DeopCommand(@NotNull final JavaPlugin plugin)
    {
        super(plugin);
    }

    @Subcommand(permission = "cladis.deop", args = {Player.class})
    public void deop(final CommandSender sender, final Player player)
    {
        final UUID playerUUID = player.getUniqueId();

        final NMLink nmLink = Shortcuts.provideModule(Cladis.class).getNMLink();
        final Group opGroup = nmLink.deopGroup();

        if (opGroup == null)
        {
            sender.sendPlainMessage("Unable to deop player. Please contact an administrator.");
            return;
        }

        if (nmLink.isDeop(playerUUID))
        {
            sender.sendPlainMessage("Player is not op.");
            return;
        }

        nmLink.getPlayerGroups(playerUUID).remove(opGroup);

        sender.sendPlainMessage("Player deopped.");
        player.sendPlainMessage("You have been deopped!");
    }
}

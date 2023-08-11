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
import fns.patchwork.command.Commander;
import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Info(name = "commandspy", description = "Spy on commands executed by players.", usage = "/commandspy")
@Permissive(perm = "datura.commandspy", onlyPlayers = true)
public class CommandSpyCommand extends Commander
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
    public CommandSpyCommand(@NotNull final Datura plugin)
    {
        super(plugin);
    }

    @Base
    public void commandSpy(final Player sender)
    {
        final var commandSpy = ((Datura) getPlugin()).
            getCommandSpy();

        final var uuid = sender.
            getUniqueId();

        if (commandSpy.isSpying(uuid))
        {
            commandSpy.stop(uuid);
            sender.sendPlainMessage("CommandSpy disabled.");
        }
        else
        {
            commandSpy.spy(uuid);
            sender.sendPlainMessage("CommandSpy enabled.");
        }
    }
}

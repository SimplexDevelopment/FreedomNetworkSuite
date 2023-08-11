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

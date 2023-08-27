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

package fns.patchwork.kyori;

import fns.patchwork.base.Patchwork;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class contains different methods to provide {@link ChatType.Bound} instances for sending messages to players in
 * game. This is now a requirement for all message requests to players due to the new chat signature system.
 * <br>
 * Even though Scissors has this feature disabled, upstream (Paper) and Kyori Adventure have made the appropriate API
 * changes to accomodate chat signatures.
 * <br>
 * As a result, we need to conform to those specifications even if we do not use this feature.
 */
public final class ChatBinder
{
    private static final ChatType type = ChatType.CHAT;

    /**
     * A singleton {@link ChatType.Bound} for the Patchwork plugin.
     */
    public static final ChatType.Bound PATCHWORK = fromPlugin(Patchwork.class);

    private ChatBinder()
    {
    }

    /**
     * Represents a Chat Bound for a plugin.
     * <br>
     * This is a convenience method so you are not required to dependency inject your plugin instance any time that you
     * need a Bound.
     *
     * @param pluginClass The plugin class to get the plugin instance from.
     * @return A ChatType.Bound instance for the plugin.
     * @see #fromPlugin(JavaPlugin)
     */
    public static ChatType.Bound fromPlugin(final Class<? extends JavaPlugin> pluginClass)
    {
        final JavaPlugin plugin = JavaPlugin.getPlugin(pluginClass);
        return fromPlugin(plugin);
    }

    public static ChatType.Bound fromPlugin(final JavaPlugin plugin)
    {
        final String name = plugin.getName();
        final Component component = Component.text(name, NamedTextColor.GOLD);
        return type.bind(component);
    }

    /**
     * Represents a Chat Bound for a player. Chat bounds are required for sending messages to players.
     * <br>
     * The chat bound is a representation of a validated chat signature, which is tied directly to the user's account
     * name. In our case, this is the player's name.
     *
     * @param player The player to get the bound for.
     * @return A ChatType.Bound instance for the player.
     */
    public static ChatType.Bound fromPlayer(final Player player)
    {
        return type.bind(player.name());
    }

    /**
     * Represents a Chat Bound for the console.
     * <br>
     * The chat bound is a representation of a validated chat signature, which is tied directly to the user's account
     * name. In our case, this is the player's name.
     *
     * @param sender The console to get the bound for.
     * @return A ChatType.Bound instance for the console.
     */
    public static ChatType.Bound fromConsole(final ConsoleCommandSender sender)
    {
        return type.bind(sender.name());
    }
}

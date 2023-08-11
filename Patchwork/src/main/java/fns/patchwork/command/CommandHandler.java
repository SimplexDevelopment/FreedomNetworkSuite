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

package fns.patchwork.command;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

/**
 * Handles the registration of commands. The plugin which initializes this class should be the plugin that is
 * registering the commands.
 */
public class CommandHandler
{
    /**
     * The plugin that this command handler is registered to.
     * <br>
     * This should be the plugin instance which is trying to register the commands.
     */
    private final JavaPlugin plugin;

    /**
     * Creates a new command handler.
     *
     * @param plugin The plugin that this command handler is registered to.
     */
    public CommandHandler(final JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Registers a command. This method will automatically delegate the command information to the Bukkit API and
     * register with the {@link CommandMap}.
     *
     * @param command The command to register.
     * @param <T>     The type of the command.
     */
    public <T extends Commander> void registerCommand(final T command)
    {
        new BukkitDelegate(command).register(Bukkit.getServer().getCommandMap());
    }

    /**
     * Registers all commands in the specified package that contains the provided class. This method will automatically
     * delegate the command information to the Bukkit API and register with the {@link CommandMap}.
     *
     * @param commandClass The class to register commands from.
     * @param <T>          The type of the command.
     */
    public <T extends Commander> void registerCommands(final Class<T> commandClass)
    {
        final Reflections reflections = new Reflections(commandClass.getPackageName());
        reflections.getSubTypesOf(commandClass)
                   .stream()
                   .map(c ->
                        {
                            try
                            {
                                return c.getDeclaredConstructor(JavaPlugin.class).newInstance(this);
                            }
                            catch (ReflectiveOperationException ex)
                            {
                                plugin.getSLF4JLogger().error("Unable to register command: " + c.getName(), ex);
                                return null;
                            }
                        })
                   .filter(Objects::nonNull)
                   .forEach(this::registerCommand);
    }
}

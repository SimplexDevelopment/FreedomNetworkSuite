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

package fns.patchwork.command;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

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
     * The logger instance of the plugin this command handler is registered to.
     */
    private final Logger logger;

    /**
     * Creates a new command handler.
     *
     * @param plugin The plugin that this command handler is registered to.
     */
    public CommandHandler(final JavaPlugin plugin)
    {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
    }

    private @Nullable Commander instantiateCommandClass(final ClassInfo commandSubclassInfo) {
        final Class<?> genericClass;

        try {
            genericClass = commandSubclassInfo.loadClass();
        } catch (IllegalArgumentException e) {
            this.logger.error("Failed to load command subclass", e);
            return null;
        }

        final Class<? extends Commander> subClass;

        try {
            subClass = genericClass.asSubclass(Commander.class);
        } catch (ClassCastException e) {
            this.logger.error("Failed to cast command class to subtype of commander class", e);
            return null;
        }

        try {
            return subClass.getDeclaredConstructor(JavaPlugin.class).newInstance(this.plugin);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
            //
        }

        try {
            return subClass.getDeclaredConstructor(this.plugin.getClass()).newInstance(this.plugin);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            this.logger.error("Failed to instantiate instance of command class", e);
            return null;
        }
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
        final CommandMap commandMap = Bukkit.getCommandMap();

        try (final ScanResult scanResult = new ClassGraph()
                .ignoreParentClassLoaders()
                .overrideClassLoaders(commandClass.getClassLoader(), this.getClass().getClassLoader())
                .enableClassInfo()
                .acceptPackages(commandClass.getPackageName())
                .scan()) {

            final String lowercasePluginName = this.plugin.getName().toLowerCase();
            scanResult.getSubclasses(Commander.class).stream()
                    .map(this::instantiateCommandClass)
                    .filter(Objects::nonNull)
                    .forEach(c -> commandMap.register(lowercasePluginName, new BukkitDelegate(c)));
        }
    }
}

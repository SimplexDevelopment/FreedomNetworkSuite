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

package fns.patchwork.registry;

import fns.patchwork.provider.ModuleProvider;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A registry for modules.
 */
public class ModuleRegistry
{
    /**
     * The list of modules.
     */
    private final List<JavaPlugin> plugins;

    /**
     * Creates a new module registry.
     */
    public ModuleRegistry()
    {
        this.plugins = new ArrayList<>();
    }

    /**
     * Adds a module to the registry.
     *
     * @param plugin The module to add.
     */
    public void addModule(final JavaPlugin plugin)
    {
        if (this.plugins.contains(plugin))
        {
            return;
        }
        this.plugins.add(plugin);
    }

    /**
     * Removes a module from the registry.
     *
     * @param plugin The module to remove.
     */
    public void removeModule(final JavaPlugin plugin)
    {
        this.plugins.remove(plugin);
    }

    /**
     * Gets a module from the registry wrapped in a {@link ModuleProvider}.
     *
     * @param clazz The class of the module.
     * @param <T>   The type of the module.
     * @return The module.
     */
    public <T extends JavaPlugin> ModuleProvider<T> getProvider(final Class<T> clazz)
    {
        for (final JavaPlugin plugin : plugins)
        {
            if (clazz.isInstance(plugin))
            {
                return () -> clazz.cast(plugin);
            }
        }

        return () -> null;
    }
}

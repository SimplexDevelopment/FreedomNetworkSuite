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

import fns.patchwork.config.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry for all the configurations.
 */
public class ConfigRegistry
{
    /**
     * A map of all the configurations.
     */
    private final Map<String, Configuration> configurationList = new HashMap<>();

    /**
     * Registers a configuration.
     *
     * @param name          The name of the configuration.
     * @param configuration The configuration.
     */
    public void register(final String name, final Configuration configuration)
    {
        configurationList.put(name, configuration);
    }

    /**
     * Unregisters a configuration.
     *
     * @param name The name of the configuration.
     */
    public void unregister(final String name)
    {
        configurationList.remove(name);
    }

    /**
     * Gets a configuration.
     *
     * @param name The name of the configuration.
     * @return The configuration.
     */
    public Configuration getConfiguration(final String name)
    {
        return configurationList.get(name);
    }
}

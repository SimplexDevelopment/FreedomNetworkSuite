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

package fns.patchwork.base;

import fns.patchwork.data.ConfigRegistry;
import fns.patchwork.data.EventRegistry;
import fns.patchwork.data.GroupRegistry;
import fns.patchwork.data.ModuleRegistry;
import fns.patchwork.data.ServiceTaskRegistry;
import fns.patchwork.data.UserRegistry;

/**
 * This class is a holder for each registry in the data package.
 * <br>
 * Registries such as {@link ModuleRegistry} and {@link ServiceTaskRegistry} can be found as final objects in this
 * class.
 */
public class Registration
{
    /**
     * The {@link EventRegistry}
     */
    private static final EventRegistry eventRegistry = new EventRegistry();
    /**
     * The {@link UserRegistry}
     */
    private static final UserRegistry userRegistry = new UserRegistry();
    /**
     * The {@link ServiceTaskRegistry}
     */
    private static final ServiceTaskRegistry serviceTaskRegistry = new ServiceTaskRegistry();
    /**
     * The {@link ModuleRegistry}
     */
    private static final ModuleRegistry moduleRegistry = new ModuleRegistry();
    /**
     * The {@link GroupRegistry}
     */
    private static final GroupRegistry groupRegistry = new GroupRegistry();
    /**
     * The {@link ConfigRegistry}
     */
    private static final ConfigRegistry configRegistry = new ConfigRegistry();

    private Registration()
    {
        throw new AssertionError();
    }

    /**
     * @return The {@link ModuleRegistry}
     */
    public static ModuleRegistry getModuleRegistry()
    {
        return moduleRegistry;
    }

    /**
     * @return The {@link EventRegistry}
     */
    public static EventRegistry getEventRegistry()
    {
        return eventRegistry;
    }

    /**
     * @return The {@link UserRegistry}
     */
    public static UserRegistry getUserRegistry()
    {
        return userRegistry;
    }

    /**
     * @return The {@link ServiceTaskRegistry}
     */
    public static ServiceTaskRegistry getServiceTaskRegistry()
    {
        return serviceTaskRegistry;
    }

    /**
     * @return The {@link GroupRegistry}
     */
    public static GroupRegistry getGroupRegistry()
    {
        return groupRegistry;
    }

    /**
     * @return The {@link ConfigRegistry}
     */
    public static ConfigRegistry getConfigRegistry()
    {
        return configRegistry;
    }
}
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

package fns.patchwork.service;

/**
 * Represents a ticking service. Services may be asynchronous or synchronous, however there are some restrictions:
 * <ul>
 *     <li>Sync services may not have a complexity greater than 5.</li>
 *     <li>Async services may not interact with the Bukkit API in any form.</li>
 * </ul>
 */
public abstract class Service
{
    /**
     * The name of the service.
     */
    private final String name;

    /**
     * Creates a new service with the given name.
     *
     * @param name The name of the service.
     */
    protected Service(final String name)
    {
        this.name = name;
    }

    /**
     * This method is called every single tick, or at least, every tick interval described by the holding
     * {@link ServiceSubscription}. Since this runs every single tick, the body of this method should not have a
     * complexity higher than 5. This includes Cyclomatic, Cognitive, and NPath complexities. If the service is
     * asynchronous, there is a bit more flexibility with the complexity rating, extending to no more than 10. However,
     * it's generally good practice to keep the complexity of ticking services as low as possible to avoid extensive
     * resource consumption.
     *
     * @see ServiceSubscription
     * @see SubscriptionProvider
     */
    public abstract void tick();

    /**
     * @return The name of the service.
     */
    public String getName()
    {
        return name;
    }
}

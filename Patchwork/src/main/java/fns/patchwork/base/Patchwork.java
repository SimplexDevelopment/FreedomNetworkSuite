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

import fns.patchwork.display.adminchat.AdminChatDisplay;
import fns.patchwork.event.EventBus;
import fns.patchwork.provider.ExecutorProvider;
import fns.patchwork.provider.SubscriptionProvider;
import fns.patchwork.registry.ServiceTaskRegistry;
import fns.patchwork.service.Service;
import fns.patchwork.utils.logging.FNS4J;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;

/**
 * The base class for Patchwork.
 */
public class Patchwork extends JavaPlugin
{
    /**
     * The {@link EventBus} for this plugin.
     */
    private EventBus eventBus;
    /**
     * The {@link ExecutorProvider} for this plugin.
     */
    private ExecutorProvider executor;
    /**
     * The {@link AdminChatDisplay} for this plugin.
     */
    private AdminChatDisplay acdisplay;

    private static final ServiceRunner runner = new ServiceRunner();


    @Override
    public void onEnable()
    {
        eventBus = new EventBus(this);
        executor = new ExecutorProvider(this);
        acdisplay = new AdminChatDisplay(this);

        Registration.getServiceTaskRegistry()
                    .registerService(SubscriptionProvider.asyncService(this, eventBus));

        Registration.getServiceTaskRegistry()
                    .registerService(SubscriptionProvider.asyncService(this, runner));

        // Will execute post-world
        getExecutor().getSync()
                     .execute(this::postWorld);

        Registration.getModuleRegistry().addModule(this);

        FNS4J.PATCHWORK.info("Successfully enabled Patchwork. API is ready to go.");
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler()
              .runTaskLater(this, () -> Registration
                  .getServiceTaskRegistry()
                  .stopAllServices(), 1L);

        Registration.getServiceTaskRegistry()
                    .unregisterService(EventBus.class);

        FNS4J.PATCHWORK.info("Successfully disabled Patchwork. API is no longer available.");
    }

    private void postWorld()
    {
        Registration.getServiceTaskRegistry()
                    .startAllServices();
    }

    /**
     * Gets the {@link ExecutorProvider} for this plugin.
     *
     * @return the {@link ExecutorProvider}
     */
    public ExecutorProvider getExecutor()
    {
        return executor;
    }

    /**
     * Gets the {@link EventBus} for this plugin. The EventBus is used to register and listen to custom events provided
     * by Freedom Network Suite.
     *
     * @return the {@link EventBus}
     */
    @ApiStatus.Experimental
    public EventBus getEventBus()
    {
        return eventBus;
    }

    /**
     * Gets the {@link AdminChatDisplay} for this plugin. The AdminChatDisplay is used to display messages sent in
     * adminchat.
     *
     * @return the {@link AdminChatDisplay}
     */
    public AdminChatDisplay getAdminChatDisplay()
    {
        return acdisplay;
    }

    @ApiStatus.Internal
    private static final class ServiceRunner extends Service
    {
        public ServiceRunner()
        {
            super("srv-runner");
        }

        @Override
        public void tick()
        {
            final ServiceTaskRegistry r = Registration.getServiceTaskRegistry();
            r.getServices().forEach(s ->
                                    {
                                        if (!s.isActive())
                                        {
                                            r.unregisterService(s.getService().getClass());
                                        }
                                    });

            r.getTasks().forEach(t ->
                                 {
                                     if (!t.isActive())
                                     {
                                         r.unregisterTask(t.getTask().getClass());
                                     }
                                 });
        }
    }
}

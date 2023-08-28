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

package fns.patchwork.event;

import fns.patchwork.provider.Context;
import fns.patchwork.base.Patchwork;
import fns.patchwork.service.Service;
import java.util.HashSet;
import java.util.Set;

public class EventBus extends Service
{
    private final Patchwork plugin;
    private final Set<FEvent> eventSet = new HashSet<>();
    private final SubscriptionBox<?> runningSubscriptions = new SubscriptionBox<>();

    public EventBus(final Patchwork plugin)
    {
        super("event_bus");
        this.plugin = plugin;
    }

    public void addEvent(final FEvent event)
    {
        eventSet.add(event);
    }

    public <T extends FEvent> T getEvent(final Class<T> eventClass)
    {
        final FEvent e = eventSet.stream()
                                 .filter(event -> event.getEventClass()
                                                       .equals(eventClass))
                                 .findFirst()
                                 .orElse(null);

        return eventClass.cast(e);
    }

    public <T extends FEvent> EventSubscription<T> subscribe(final Class<T> eventClass, final Callback<T> callback)
    {
        final Context<T> eventContext = () -> eventSet.stream()
                                                      .filter(event -> event.getEventClass()
                                                                            .equals(eventClass))
                                                      .findFirst()
                                                      .map(eventClass::cast)
                                                      .orElse(null);

        if (eventContext.get() == null)
        {
            throw new IllegalArgumentException("Event class " + eventClass.getName() + " is not registered.");
        }

        return new EventSubscription<>(eventContext.get(), callback);
    }

    public void unsubscribe(final EventSubscription<?> subscription)
    {
        runningSubscriptions.removeSubscription(subscription);
    }

    public Patchwork getCommonsBase()
    {
        return plugin;
    }

    @Override
    public void tick()
    {
        runningSubscriptions.tick();
    }
}

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

package fns.patchwork.registry;

import fns.patchwork.event.FEvent;
import fns.patchwork.provider.EventProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * A registry for {@link FEvent}s.
 */
public class EventRegistry
{
    /**
     * The list of events.
     */
    private final List<FEvent> events;

    /**
     * Creates a new event registry.
     */
    public EventRegistry()
    {
        this.events = new ArrayList<>();
    }

    /**
     * Registers an event.
     *
     * @param event The event to register.
     */
    public void register(final FEvent event)
    {
        this.events.add(event);
    }

    /**
     * Unregisters an event.
     *
     * @param event The event to unregister.
     */
    public void unregister(final FEvent event)
    {
        this.events.remove(event);
    }

    /**
     * Gets an {@link EventProvider} for the specified event class which contains the actual {@link FEvent} instance.
     *
     * @param clazz The event class.
     * @param <T>   The event type.
     * @return The event provider.
     */
    public <T extends FEvent> EventProvider<T> getEvent(final Class<T> clazz)
    {
        for (final FEvent event : this.events)
        {
            if (clazz.isInstance(event))
            {
                return () -> clazz.cast(event);
            }
        }
        return null;
    }
}

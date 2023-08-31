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

import fns.patchwork.permissible.Group;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

/**
 * A registry for {@link Group}s.
 */
public class GroupRegistry
{
    /**
     * The list of groups.
     */
    private final List<Group> groups;

    /**
     * Creates a new group registry.
     */
    public GroupRegistry()
    {
        this.groups = new ArrayList<>();
    }

    /**
     * Registers a group.
     *
     * @param group The group to register.
     * @return {@code true} if the group was registered, {@code false} otherwise.
     */
    public boolean registerGroup(final Group group)
    {
        return groups.add(group);
    }

    /**
     * Unregisters a group.
     *
     * @param group The group to unregister.
     * @return {@code true} if the group was unregistered, {@code false} otherwise.
     */
    public boolean unregisterGroup(final Group group)
    {
        return groups.remove(group);
    }

    /**
     * Gets a group by name.
     *
     * @param name The name of the group.
     * @return The group, or {@code null} if no group was found.
     */
    public Group getGroup(final String name)
    {
        final PlainTextComponentSerializer s = PlainTextComponentSerializer.plainText();
        for (final Group group : groups)
        {
            final String n = s.serialize(group.getName());
            if (n.equalsIgnoreCase(name))
            {
                return group;
            }
        }
        return null;
    }

    /**
     * @return The list of groups.
     */
    public List<Group> getGroups()
    {
        return groups;
    }
}
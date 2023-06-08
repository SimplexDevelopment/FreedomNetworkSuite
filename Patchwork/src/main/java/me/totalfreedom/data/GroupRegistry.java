package me.totalfreedom.data;

import me.totalfreedom.security.Group;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.ArrayList;
import java.util.List;

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
     * @param group The group to register.
     * @return {@code true} if the group was registered, {@code false} otherwise.
     */
    public boolean registerGroup(final Group group)
    {
        return groups.add(group);
    }

    /**
     * Unregisters a group.
     * @param group The group to unregister.
     * @return {@code true} if the group was unregistered, {@code false} otherwise.
     */
    public boolean unregisterGroup(final Group group)
    {
        return groups.remove(group);
    }

    /**
     * Gets a group by name.
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

package me.totalfreedom.data;

import me.totalfreedom.security.perm.Group;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.ArrayList;
import java.util.List;

public class GroupRegistry
{
    private final List<Group> groups;

    public GroupRegistry()
    {
        this.groups = new ArrayList<>();
    }

    public boolean registerGroup(final Group group)
    {
        return groups.add(group);
    }

    public boolean unregisterGroup(final Group group)
    {
        return groups.remove(group);
    }

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

    public List<Group> getGroups()
    {
        return groups;
    }
}

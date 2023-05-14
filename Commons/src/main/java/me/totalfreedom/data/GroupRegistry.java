package me.totalfreedom.data;

import me.totalfreedom.security.Group;
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

    public boolean registerGroup(Group group) {
        return groups.add(group);
    }

    public boolean unregisterGroup(Group group) {
        return groups.remove(group);
    }

    public Group getGroup(String name) {
        PlainTextComponentSerializer s = PlainTextComponentSerializer.plainText();
        for (Group group : groups) {
            String n = s.serialize(group.getName());
            if (n.equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    public List<Group> getGroups() {
        return groups;
    }
}

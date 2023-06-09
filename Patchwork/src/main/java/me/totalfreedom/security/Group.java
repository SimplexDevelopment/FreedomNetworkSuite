package me.totalfreedom.security;

import net.kyori.adventure.text.Component;

/**
 * Represents a permissible group which holds a set of permissions that can then be applied to a User / Player.
 */
public interface Group extends PermissionHolder
{
    /**
     * @return The name of the group.
     */
    Component getName();

    /**
     * @return The prefix of the group.
     */
    Component getPrefix();

    /**
     * @return The abbreviation of the group.
     */
    Component getAbbreviation();

    /**
     * @return The weight of the group.
     */
    int getWeight();

    /**
     * If more than one group is marked as default, the first retrieved default group will be used.
     *
     * @return Whether this is the default group.
     */
    boolean isDefault();

    /**
     * @return Whether the group is hidden.
     */
    boolean isHidden();
}

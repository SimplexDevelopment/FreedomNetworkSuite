package me.totalfreedom.security;

import net.kyori.adventure.text.Component;

public interface Group extends PermissionHolder
{
    Component getName();

    Component getPrefix();

    Component getAbbreviation();

    int getWeight();

    boolean isDefault();

    boolean isHidden();
}

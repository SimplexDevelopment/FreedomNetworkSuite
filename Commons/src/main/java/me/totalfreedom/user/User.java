package me.totalfreedom.user;

import me.totalfreedom.security.PermissionHolder;
import net.kyori.adventure.text.Component;

public interface User extends PermissionHolder
{
    Component getDisplayName();

    boolean isOnline();
}

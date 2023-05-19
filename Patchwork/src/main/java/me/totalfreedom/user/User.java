package me.totalfreedom.user;

import me.totalfreedom.security.perm.PermissionHolder;
import net.kyori.adventure.text.Component;

public interface User extends PermissionHolder
{
    UserData getUserData();

    Component getDisplayName();

    boolean isOnline();
}

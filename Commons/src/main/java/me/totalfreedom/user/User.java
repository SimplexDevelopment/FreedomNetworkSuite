package me.totalfreedom.user;

import me.totalfreedom.permission.PermissionHolder;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public interface User extends PermissionHolder
{
    Component getDisplayName();

    boolean isOnline();
}

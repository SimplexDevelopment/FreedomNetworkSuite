package me.totalfreedom.admin;

import me.totalfreedom.permission.Group;
import me.totalfreedom.permission.PermissionHolder;
import net.kyori.adventure.text.Component;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;
import java.util.UUID;

public interface Administrator extends Group
{
    boolean isActive();

    void setActive(boolean active);

    void setWeight(int weight);

    Component getLoginMessage();

    void setLoginMessage(Component loginMessage);
}

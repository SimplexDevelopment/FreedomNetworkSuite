package me.totalfreedom.permission;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface Node
{
    String getKey();

    boolean getValue();

    Permission spigot();

    NodeType getType();

    boolean compare(Node node);

    long getExpiry();

    boolean isExpired();

    boolean isPermanent();

    boolean isTemporary();

    boolean isWildcard();

    boolean isNegated();

    NodeBuilder builder();
}

package me.totalfreedom.security;

import org.bukkit.permissions.Permission;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface Node
{
    String getKey();

    boolean getValue();

    Permission bukkit();

    NodeType getType();

    boolean compare(Node node);

    long getExpiry();

    boolean isExpired();

    boolean isPermanent();

    boolean isTemporary();

    boolean isWildcard();

    boolean isNegated();
}

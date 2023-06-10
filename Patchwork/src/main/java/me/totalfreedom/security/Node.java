package me.totalfreedom.security;

import org.bukkit.permissions.Permission;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface Node
{
    String key();

    boolean value();

    Permission bukkit();

    NodeType type();

    boolean compare(Node node);

    long expiry();

    boolean isExpired();

    boolean isTemporary();

    boolean wildcard();
}

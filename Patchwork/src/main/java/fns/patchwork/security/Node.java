package fns.patchwork.security;

import javax.annotation.concurrent.Immutable;
import org.bukkit.permissions.Permission;

@Immutable
public interface Node
{
    String key();

    Permission bukkit();

    NodeType type();

    boolean compare(Node node);

    long expiry();

    boolean isExpired();

    boolean isTemporary();

    boolean wildcard();
}

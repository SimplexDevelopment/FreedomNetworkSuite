package me.totalfreedom.security.perm;

import org.bukkit.permissions.Permissible;

import java.util.Set;
import java.util.UUID;

public interface PermissionHolder extends Permissible
{
    UUID getUniqueId();

    Set<Node> permissions();

    boolean addPermission(Node node);

    boolean removePermission(Node node);
}

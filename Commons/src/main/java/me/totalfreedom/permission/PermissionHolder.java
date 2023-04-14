package me.totalfreedom.permission;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;

import java.util.Set;
import java.util.UUID;

public interface PermissionHolder extends Permissible
{
    PermissionHolder fromPlayer(Player player);

    UUID getUniqueId();

    Set<Node> permissions();

    boolean addPermission(Node node);

    boolean removePermission(Node node);
}

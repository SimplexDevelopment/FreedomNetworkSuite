package fns.patchwork.security;

import java.util.Set;
import java.util.UUID;
import org.bukkit.permissions.Permissible;

public interface PermissionHolder extends Permissible
{
    UUID getUniqueId();

    Set<Node> permissions();

    boolean addPermission(Node node);

    boolean removePermission(Node node);
}

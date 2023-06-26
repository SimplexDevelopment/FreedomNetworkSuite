package me.totalfreedom.datura.perms;

import me.totalfreedom.security.Node;
import me.totalfreedom.security.NodeType;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

record PermissionNode(String key, long expiry, NodeType type, boolean wildcard) implements Node
{

    @Override
    public Permission bukkit()
    {
        return new Permission(key(), PermissionDefault.FALSE);
    }

    @Override
    public boolean compare(final Node node)
    {
        return node.key().equalsIgnoreCase(key()) && node.type().equals(type()) && !node.isExpired();
    }

    @Override
    public boolean isExpired()
    {
        if (!isTemporary())
        {
            return false;
        }

        return System.currentTimeMillis() > expiry();
    }

    @Override
    public boolean isTemporary()
    {
        return expiry() > -1;
    }
}

package me.totalfreedom.datura.perms;

import me.totalfreedom.security.perm.Node;
import me.totalfreedom.security.perm.NodeType;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

record PermissionNode(String key,
                      boolean value,
                      long expiry,
                      NodeType type,
                      boolean wildcard,
                      boolean negated) implements Node
{

    @Override
    public Permission bukkit()
    {
        return new Permission(key(),
                value() ? PermissionDefault.TRUE : PermissionDefault.FALSE);
    }

    @Override
    public boolean compare(final Node node)
    {
        return node.key().equalsIgnoreCase(key())
                && node.value() == value()
                && node.type() == type();
    }

    @Override
    public boolean isExpired()
    {
        if (isPermanent())
        {
            return false;
        }

        return System.currentTimeMillis() > expiry();
    }

    @Override
    public boolean isPermanent()
    {
        return expiry() == -1;
    }

    @Override
    public boolean isTemporary()
    {
        return !isPermanent();
    }
}

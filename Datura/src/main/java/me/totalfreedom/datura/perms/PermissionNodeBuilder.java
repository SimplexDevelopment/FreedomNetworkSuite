package me.totalfreedom.datura.perms;

import me.totalfreedom.security.Node;
import me.totalfreedom.security.NodeBuilder;
import me.totalfreedom.security.NodeType;

public class PermissionNodeBuilder implements NodeBuilder
{
    private String key = "freedom.default";
    private boolean value = true;
    private long expiry = -1;
    private NodeType type = NodeType.PERMISSION;
    private boolean wildcard = false;
    private boolean negated = false;

    @Override
    public NodeBuilder key(final String key)
    {
        this.key = key;
        return this;
    }

    @Override
    public NodeBuilder value(final boolean value)
    {
        this.value = value;
        return this;
    }

    @Override
    public NodeBuilder expiry(final long expiry)
    {
        this.expiry = expiry;
        return this;
    }

    @Override
    public NodeBuilder type(final NodeType type)
    {
        this.type = type;
        return this;
    }

    @Override
    public NodeBuilder wildcard(final boolean wildcard)
    {
        this.wildcard = wildcard;
        return this;
    }

    @Override
    public NodeBuilder negated(final boolean negated)
    {
        this.negated = negated;
        return this;
    }

    @Override
    public Node build()
    {
        return new PermissionNode(key, value, expiry, type, wildcard, negated);
    }
}

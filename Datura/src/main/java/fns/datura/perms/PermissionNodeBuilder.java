package fns.datura.perms;

import fns.patchwork.security.Node;
import fns.patchwork.security.NodeBuilder;
import fns.patchwork.security.NodeType;

public class PermissionNodeBuilder implements NodeBuilder
{
    private String key = "freedom.default";
    private long expiry = -1;
    private NodeType type = NodeType.PERMISSION;
    private boolean wildcard = false;

    @Override
    public NodeBuilder key(final String key)
    {
        this.key = key;
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
    public Node build()
    {
        return new PermissionNode(key, expiry, type, wildcard);
    }
}

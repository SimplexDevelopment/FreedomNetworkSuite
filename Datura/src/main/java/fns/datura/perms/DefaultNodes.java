package fns.datura.perms;

import fns.patchwork.security.Node;
import fns.patchwork.security.NodeType;

public class DefaultNodes
{
    public static final Node OP = new PermissionNodeBuilder()
            .key("freedom.master_key")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(true)
            .build();
    public static final Node NON_OP = new PermissionNodeBuilder()
            .key("freedom.default")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(false)
            .build();
    public static final Node ALL = new PermissionNodeBuilder()
            .key("*")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(true)
            .build();
    public static final Node NONE = new PermissionNodeBuilder()
            .key("freedom.none")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(false)
            .build();

    private DefaultNodes()
    {
        throw new AssertionError();
    }
}

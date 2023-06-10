package me.totalfreedom.datura.perms;

import me.totalfreedom.security.Node;
import me.totalfreedom.security.NodeType;

public class DefaultNodes
{
    public static final Node OP = new PermissionNodeBuilder()
            .key("freedom.master_key")
            .value(true)
            .type(NodeType.PERMISSION)
            .negated(false)
            .wildcard(true)
            .build();
    public static final Node NON_OP = new PermissionNodeBuilder()
            .key("freedom.default")
            .value(true)
            .type(NodeType.PERMISSION)
            .negated(false)
            .wildcard(false)
            .build();
    public static final Node ALL = new PermissionNodeBuilder()
            .key("*")
            .value(true)
            .type(NodeType.PERMISSION)
            .negated(false)
            .wildcard(true)
            .build();
    public static final Node NONE = new PermissionNodeBuilder()
            .key("freedom.none")
            .value(true)
            .type(NodeType.PERMISSION)
            .negated(false)
            .wildcard(false)
            .build();

    private DefaultNodes()
    {
        throw new AssertionError();
    }
}

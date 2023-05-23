package me.totalfreedom.security.perm;

public interface NodeBuilder
{
    NodeBuilder key(String key);

    NodeBuilder value(boolean value);

    NodeBuilder expiry(long expiry);

    NodeBuilder type(NodeType type);

    NodeBuilder wildcard(boolean wildcard);

    NodeBuilder negated(boolean negated);

    Node build();
}

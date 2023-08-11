package fns.patchwork.security;

public interface NodeBuilder
{
    NodeBuilder key(String key);

    NodeBuilder expiry(long expiry);

    NodeBuilder type(NodeType type);

    NodeBuilder wildcard(boolean wildcard);

    Node build();
}

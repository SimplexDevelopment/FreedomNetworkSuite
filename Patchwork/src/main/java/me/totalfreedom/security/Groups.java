package me.totalfreedom.security;

public enum Groups
{
    NON_OP("patchwork.group.non-op"),
    OP("patchwork.group.op"),
    SUPER_ADMIN("patchwork.group.super"),
    SENIOR_ADMIN("patchwork.group.senior"),
    DEVELOPER("patchwork.group.dev"),
    EXECUTIVE("patchwork.group.exec"),
    OWNER("patchwork.group.owner");

    private final String permission;

    Groups(final String permission)
    {
        this.permission = permission;
    }

    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public String toString()
    {
        return this.permission;
    }
}

package me.totalfreedom.utils.container;

import java.util.UUID;

public class Identity
{
    private final String key;

    private final UUID id;

    public Identity(final String key)
    {
        this.key = key;
        this.id = UUID.nameUUIDFromBytes(key.getBytes());
    }

    public String getKey()
    {
        return key;
    }

    public UUID getId()
    {
        return id;
    }
}

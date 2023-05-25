package me.totalfreedom.datura.banning;

import me.totalfreedom.security.ban.Ban;
import me.totalfreedom.security.ban.BanID;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.UUID;

public final class SimpleBan implements Ban
{
    private final BanID id;
    private final UUID offenderID;
    private final String reason;
    private final String issuer;
    private final Instant creationTime;
    private final Instant expiry;

    public SimpleBan(
            final UUID offenderID,
            final String reason,
            final String issuer,
            final Instant creationTime,
            final Instant expiry)
    {
        if (expiry == null)
        {
            this.id = BanUID.createPermID();
        } else
        {
            this.id = BanUID.createTempID();
        }

        this.offenderID = offenderID;
        this.reason = reason;
        this.issuer = issuer;
        this.creationTime = creationTime;
        this.expiry = expiry;
    }

    @Override
    public BanID getBanID()
    {
        return id;
    }

    @Override
    public UUID getOffenderID()
    {
        return offenderID;
    }

    @Override
    public String getReason()
    {
        return reason;
    }

    @Override
    public String getBanIssuer()
    {
        return issuer;
    }

    @Override
    public Instant getCreationTime()
    {
        return creationTime;
    }

    @Override
    public @Nullable Instant getExpiry()
    {
        return expiry;
    }

    @Override
    public boolean isExpired()
    {
        return Instant.now().compareTo(expiry) >= 0;
    }
}

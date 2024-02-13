package fns.patchwork.bans;

import java.time.Instant;
import java.util.UUID;

public interface BanEntry
{
    /**
     * @return The username of the banned player.
     */
    String getUsername();

    /**
     * @return The {@link UUID} of the banned player.
     */
    UUID getUuid();

    /**
     * @return Either the IPv6 address of the banned player, if applicable,
     * otherwise returns {@code "N/A"}.
     */
    String getIpv6();

    /**
     * @return The reason for the ban.
     */
    String getReason();

    /**
     * @return The {@link Instant} the ban was issued.
     */
    Instant getIssued();

    /**
     * @return The {@link Instant} the ban expires.
     */
    Instant getExpires();

    /**
     * @return The username of the individual who issued the ban. Can be {@code "CONSOLE"}.
     */
    String getIssuer();
}
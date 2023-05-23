package me.totalfreedom.security.ban;

import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a physical ban entry. This is used to store information about a ban,
 * such as the player who was banned, the reason for why they were banned, the individual who issued the ban,
 * when the ban expires, and when the ban was created.
 * <br>
 * Ban information is stored in the Database with the {@link BanID} as the PRIMARY KEY.
 */
public interface Ban
{
    /**
     * Gets the ID of this ban. This is an object which represents a string prefixed with either a T or a P,
     * and suffixed with a 6-10 digit numerical code. This is used to identify the ban in the database, and for
     * easier ban referencing.
     *
     * @return The ID of this ban.
     */
    BanID getBanID();

    /**
     * Gets the UUID of the player who was banned. This is formatted as a UUID
     * which allows us to retrieve the particular player instance, if applicable, and also
     * have a true identifier to check against user logins.
     *
     * @return The UUID of the player who was banned.
     */
    UUID getOffenderID();

    /**
     * Gets the reason that the player was banned for. Typically, the default reason is "You are banned!".
     * We've forced implementations to require a reason, as it's important to know why a player was banned.
     *
     * @return The reason that the player was banned for.
     */
    String getReason();

    /**
     * Gets the username of the individual who issued the ban. This is not a reliable way to store data, but
     * in our case, we should not need to interact with the ban issuer from the code. This is simply for
     * reference purposes.
     *
     * @return The username of the individual who issued the ban.
     */
    String getBanIssuer();

    /**
     * Gets the {@link Instant} which this ban was created.
     *
     * @return The ban's creation time.
     */
    Instant getCreationTime();

    /**
     * Gets the {@link Instant} which this ban is due to expire, if applicable.
     * This method is annotated as {@link Nullable}, as permanent bans do not have an expiry date.
     *
     * @return The ban's expiry time, or null if the ban is permanent.
     */
    @Nullable
    Instant getExpiry();

    /**
     * Checks if the ban has expired. This will return false if:
     * <ul>
     *     <li>The {@link Instant} returned by {@link #getExpiry()} is null.</li>
     *     <li>The {@link Instant} returned by {@link #getExpiry()} is after the current time.</li>
     * </ul>
     *
     * @return True if the ban has expired, false otherwise.
     */
    boolean isExpired();
}

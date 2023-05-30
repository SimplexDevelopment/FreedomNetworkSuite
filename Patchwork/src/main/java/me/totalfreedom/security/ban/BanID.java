package me.totalfreedom.security.ban;

/**
 * Represents an ID for a ban. These are formatted either as:
 * <p>
 * P-00129381
 * <br>
 * T-00128381
 * <br>
 * </p>
 * Where P marks a ban as permanent, and T marks a ban as temporary.
 */
public interface BanID
{
    /**
     * This method returns the full Ban ID.
     *
     * @return The actual ID.
     */
    String getID();

    /**
     * Checks the prefix of the Ban ID to see whether if it is permanent.
     *
     * @return true if the Ban ID is prefixed with a P, false otherwise.
     */
    boolean isPermanent();

    default boolean matches(BanID other)
    {
        if (other == null)
        {
            return false;
        }
        return (getIDPrefix() == other.getIDPrefix())
                   && (getNumericalTag() == other.getNumericalTag());
    }

    /**
     * This method returns the ban type denominator character for the Ban ID.
     * This would either be T or P, where T = temporary and P = permanent.
     *
     * @return The ban type denominator character for the Ban ID.
     */
    char getIDPrefix();

    /**
     * Gets the numerical tag of this ban ID.
     * This would be the numerical section of the full Ban ID.
     *
     * @return The numerical tag of this ban ID.
     */
    int getNumericalTag();
}

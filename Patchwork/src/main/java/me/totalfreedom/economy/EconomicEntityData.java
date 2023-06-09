package me.totalfreedom.economy;

/**
 * Metadata associated with a {@link EconomicEntity}
 */
public interface EconomicEntityData
{
    /***
     * @return the transaction freeze state
     */
    boolean areTransactionsFrozen();

    /***
     * @return the balance
     */
    long getBalance();

    /**
     * Adds the provided amount to the associated instance's balance
     *
     * @param amount the amount to add
     * @return the new balance
     */
    long addToBalance(final long amount);

    /**
     * Subtracts the provided amount from the associated instance's balance
     *
     * @param amount the amount to subtract
     * @return the new balance
     */
    long removeFromBalance(final long amount);

    /**
     * Sets the balance of the associated instance
     *
     * @param newBalance the new balance
     */
    void setBalance(final long newBalance);
}

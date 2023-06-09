package me.totalfreedom.economy;

/**
 * A transaction that can be changed.
 * <p>
 * IMPORTANT NOTE: Please ensure that all modifications of {@link MutableTransaction} happen BEFORE it is passed to a {@link Transactor} implementation
 */
public interface MutableTransaction extends Transaction
{
    /**
     * Adds a number to the balance transferred in this transaction
     *
     * @param amount the amount to add
     * @return the new amount
     */
    long addToBalance(final long amount);

    /**
     * Subtracts a number from the balance transferred in this transaction
     *
     * @param amount the amount to remove
     * @return the new amount
     */
    long removeFromBalance(final long amount);

    /**
     * Sets the balance transferred in this transaction
     *
     * @param newBalance the new balance of the transaction
     */
    void setBalance(final long newBalance);
}

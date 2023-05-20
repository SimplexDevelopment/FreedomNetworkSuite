package me.totalfreedom.economy;

/**
 * Please ensure that all modifications of {@link MutableTransaction} happen BEFORE it is passed to a {@link Transactor} implementation
 */
public interface MutableTransaction extends Transaction
{
    long addToBalance(final long amount);

    long removeFromBalance(final long amount);

    void setBalance(final long newBalance);
}

package me.totalfreedom.economy;

public interface EconomicEntityData
{
    boolean areTransactionsFrozen();

    long getBalance();

    long addToBalance(final long amount);

    long removeFromBalance(final long amount);

    void setBalance(final long newBalance);
}

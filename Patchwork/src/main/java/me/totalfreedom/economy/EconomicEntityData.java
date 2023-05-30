package me.totalfreedom.economy;

public interface EconomicEntityData
{
    boolean areTransactionsFrozen();

    long getBalance();

    void setBalance(final long newBalance);

    long addToBalance(final long amount);

    long removeFromBalance(final long amount);
}

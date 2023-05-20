package me.totalfreedom.economy;

public interface EconomicEntityData
{
    boolean areTransactionsFrozen();

    long getBalance();

    void addToBalance(final long amount);

    void removeFromBalance(final long amount);

    void setBalance(final long newBalance);
}

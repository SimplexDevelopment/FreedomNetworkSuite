package me.totalfreedom.economy;

public interface Transaction
{
    Transaction copy();

    EconomicEntity getSource();

    EconomicEntity getDestination();

    long getBalance();

    long addToBalance(final long amount);

    long removeFromBalance(final long amount);

    void setBalance(final long newBalance);
}

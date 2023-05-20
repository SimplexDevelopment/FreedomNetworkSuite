package me.totalfreedom.economy;

public interface Transaction
{
    Transaction copy();

    EconomicEntity getSource();

    EconomicEntity getDestination();

    long getBalance();

    long addBalance(final long amount);

    long removeBalance(final long amount);

    void setBalance(final long newBalance);
}

package me.totalfreedom.economy;

public interface Transaction
{
    Transaction copy();

    EconomicEntity getSource();

    EconomicEntity getDestination();

    long getTransferAmount();

    long addBalance(final long amount);

    long removeBalance(final long amount);
}

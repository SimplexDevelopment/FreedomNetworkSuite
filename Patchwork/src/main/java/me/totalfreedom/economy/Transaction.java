package me.totalfreedom.economy;

public interface Transaction
{
    EconomicEntity getSource();

    EconomicEntity getDestination();

    long getBalance();

}

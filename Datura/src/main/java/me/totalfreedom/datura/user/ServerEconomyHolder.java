package me.totalfreedom.datura.user;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.EconomicEntityData;

public class ServerEconomyHolder implements EconomicEntity, EconomicEntityData
{
    private long balance = Long.MAX_VALUE;

    @Override
    public EconomicEntityData getEconomicData()
    {
        return this;
    }

    @Override
    public String getName()
    {
        return "TotalFreedom-Bank";
    }

    @Override
    public boolean areTransactionsFrozen()
    {
        return false;
    }

    @Override
    public long getBalance()
    {
        return balance;
    }

    @Override
    public void setBalance(final long newBalance)
    {
        balance = newBalance;
    }

    @Override
    public long addToBalance(final long amount)
    {
        balance += amount;
        return balance;
    }

    @Override
    public long removeFromBalance(final long amount)
    {
        balance -= amount;
        return balance;
    }
}

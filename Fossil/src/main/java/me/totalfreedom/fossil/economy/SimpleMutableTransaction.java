package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.MutableTransaction;

public class SimpleMutableTransaction extends SimpleTransaction implements MutableTransaction
{
    public SimpleMutableTransaction(EconomicEntity source, EconomicEntity destination, long balance)
    {
        super(source, destination, balance);
    }

    @Override
    public long addToBalance(long amount)
    {
        return balance.addAndGet(amount);
    }

    @Override
    public long removeFromBalance(long amount)
    {
        return this.addToBalance(-amount);
    }

    @Override
    public void setBalance(long newBalance)
    {
        balance.set(newBalance);
    }
}

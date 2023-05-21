package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.Transaction;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleTransaction implements Transaction
{
    private final EconomicEntity source;
    private final EconomicEntity destination;
    protected final AtomicLong balance;

    public SimpleTransaction(EconomicEntity source, EconomicEntity destination, long balance)
    {
        this.source = source;
        this.destination = destination;
        this.balance = new AtomicLong(balance);
    }

    @Override
    public EconomicEntity getSource()
    {
        return source;
    }

    @Override
    public EconomicEntity getDestination()
    {
        return destination;
    }

    @Override
    public long getBalance()
    {
        return balance.get();
    }
}

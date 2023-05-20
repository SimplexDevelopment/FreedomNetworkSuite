package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.Transaction;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleTransaction implements Transaction
{
    private final EconomicEntity source;
    private final EconomicEntity destination;
    private final AtomicLong balance;

    public SimpleTransaction(EconomicEntity source, EconomicEntity destination, long balance)
    {
        this.source = source;
        this.destination = destination;
        this.balance = new AtomicLong(balance);
    }

    @Override
    public Transaction copy()
    {
        return new SimpleTransaction(source, destination, balance.get());
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

    @Override
    public long addBalance(long amount)
    {
        return balance.addAndGet(amount);
    }

    @Override
    public long removeBalance(long amount)
    {
        return this.addBalance(-amount);
    }

    @Override
    public void setBalance(long newBalance)
    {
        balance.set(newBalance);
    }
}

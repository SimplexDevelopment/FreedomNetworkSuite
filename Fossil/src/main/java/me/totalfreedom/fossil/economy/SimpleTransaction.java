package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.Transaction;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleTransaction implements Transaction
{
    private final EconomicEntity source;
    private final EconomicEntity destination;
    private final AtomicLong transferAmount;

    public SimpleTransaction(EconomicEntity source, EconomicEntity destination, long transferAmount)
    {
        this.source = source;
        this.destination = destination;
        this.transferAmount = new AtomicLong(transferAmount);
    }

    @Override
    public Transaction copy()
    {
        return new SimpleTransaction(source, destination, transferAmount.get());
    }

    @Override
    public EconomicEntity getSource()
    {
        return this.source;
    }

    @Override
    public EconomicEntity getDestination()
    {
        return this.destination;
    }

    @Override
    public long getTransferAmount()
    {
        return this.transferAmount.get();
    }

    @Override
    public long addBalance(long amount)
    {
        return this.transferAmount.addAndGet(amount);
    }

    @Override
    public long removeBalance(long amount)
    {
        return this.addBalance(-amount);
    }
}

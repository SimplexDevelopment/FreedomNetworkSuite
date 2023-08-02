package fns.fossil.economy;

import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.Transaction;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleTransaction implements Transaction
{
    protected final AtomicLong balance;
    private final EconomicEntity source;
    private final EconomicEntity destination;

    public SimpleTransaction(final EconomicEntity source, final EconomicEntity destination, final long balance)
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

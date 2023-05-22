package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.CompletedTransaction;
import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.Transaction;
import me.totalfreedom.economy.TransactionResult;

public class SimpleCompletedTransaction implements CompletedTransaction
{
    private final TransactionResult transactionResult;
    private final EconomicEntity source;
    private final EconomicEntity destination;
    private final long balance;

    public SimpleCompletedTransaction(final Transaction transaction, final TransactionResult transactionResult)
    {

        this.source = transaction.getSource();
        this.destination = transaction.getDestination();
        this.balance = transaction.getBalance();
        this.transactionResult = transactionResult;
    }

    @Override
    public TransactionResult getResult()
    {
        return transactionResult;
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
        return balance;
    }
}

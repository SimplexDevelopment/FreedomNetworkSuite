package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.CompletedTransaction;
import me.totalfreedom.economy.Transaction;
import me.totalfreedom.economy.TransactionResult;

public class SimpleCompletedTransaction implements CompletedTransaction
{
    private final Transaction transaction;
    private final TransactionResult transactionResult;

    public SimpleCompletedTransaction(Transaction transaction, TransactionResult transactionResult)
    {
        this.transaction = transaction.copy();
        this.transactionResult = transactionResult;
    }


    @Override
    public Transaction getTransaction()
    {
        return transaction.copy();
    }

    @Override
    public TransactionResult getResult()
    {
        return transactionResult;
    }
}

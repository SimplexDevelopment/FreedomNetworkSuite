package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.CompletedTransaction;
import me.totalfreedom.economy.MutableTransaction;
import me.totalfreedom.economy.TransactionLogger;
import me.totalfreedom.economy.Transactor;

public class SimpleLoggedTransactor implements Transactor
{
    private final Transactor transactor;
    private final TransactionLogger transactionLogger;

    public SimpleLoggedTransactor()
    {
        this(new SimpleTransactor(), new SimpleTransactionLogger());
    }

    public SimpleLoggedTransactor(final Transactor transactor, final TransactionLogger transactionLogger)
    {
        this.transactor = transactor;
        this.transactionLogger = transactionLogger;
    }

    @Override
    public CompletedTransaction handleTransaction(final MutableTransaction transaction)
    {
        final CompletedTransaction completedTransaction = transactor.handleTransaction(transaction);

        transactionLogger.logTransaction(completedTransaction);
        return completedTransaction;
    }

    public TransactionLogger getTransactionLogger()
    {
        return this.transactionLogger;
    }
}

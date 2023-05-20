package me.totalfreedom.economy;

public interface TransactionLogger
{
    void logTransaction(CompletedTransaction completedTransaction);
}

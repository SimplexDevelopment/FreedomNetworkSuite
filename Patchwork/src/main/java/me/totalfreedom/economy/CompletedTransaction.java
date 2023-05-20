package me.totalfreedom.economy;

public interface CompletedTransaction
{
    Transaction getTransaction();

    TransactionResult getResult();
}

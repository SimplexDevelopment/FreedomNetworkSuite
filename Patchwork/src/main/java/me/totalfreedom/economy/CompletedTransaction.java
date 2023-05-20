package me.totalfreedom.economy;

public interface CompletedTransaction extends Transaction
{

    TransactionResult getResult();
}

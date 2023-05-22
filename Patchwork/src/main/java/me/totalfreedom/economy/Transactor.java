package me.totalfreedom.economy;

public interface Transactor
{
    CompletedTransaction handleTransaction(MutableTransaction transaction);
}

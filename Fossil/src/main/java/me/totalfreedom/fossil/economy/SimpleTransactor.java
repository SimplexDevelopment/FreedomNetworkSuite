package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.*;

public class SimpleTransactor implements Transactor
{
    @Override
    public CompletedTransaction handleTransaction(MutableTransaction transaction)
    {
        EconomicEntity source = transaction.getSource();
        EconomicEntityData sourceData = source.getEconomicData();

        if (sourceData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        long transactionAmount = transaction.getBalance();

        if (transactionAmount >= 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.AMOUNT_TOO_SMALL);
        }

        long sourceBalance = sourceData.getBalance();
        long diff = sourceBalance - transactionAmount;

        if (diff > 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.INSUFFICIENT_FUNDS);
        }

        EconomicEntity destination = transaction.getDestination();
        EconomicEntityData destinationData = destination.getEconomicData();

        if (destinationData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        sourceData.removeFromBalance(transactionAmount);
        destinationData.addToBalance(transactionAmount);

        return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.SUCCESSFUL);
    }
}

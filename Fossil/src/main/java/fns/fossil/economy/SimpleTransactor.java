package fns.fossil.economy;

import fns.patchwork.economy.CompletedTransaction;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.EconomicEntityData;
import fns.patchwork.economy.MutableTransaction;
import fns.patchwork.economy.Transactor;

public class SimpleTransactor implements Transactor
{
    @Override
    public CompletedTransaction handleTransaction(final MutableTransaction transaction)
    {
        final EconomicEntity source = transaction.getSource();
        final EconomicEntityData sourceData = source.getEconomicData();

        if (sourceData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        final long transactionAmount = transaction.getBalance();

        if (transactionAmount <= 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.AMOUNT_TOO_SMALL);
        }

        final long sourceBalance = sourceData.getBalance();
        final long diff = sourceBalance - transactionAmount;

        if (diff > 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.INSUFFICIENT_FUNDS);
        }

        final EconomicEntity destination = transaction.getDestination();
        final EconomicEntityData destinationData = destination.getEconomicData();

        if (destinationData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        sourceData.removeFromBalance(transactionAmount);
        destinationData.addToBalance(transactionAmount);

        return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.SUCCESSFUL);
    }
}

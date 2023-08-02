package fns.patchwork.economy;

/**
 * A class that implements the completion of transactions.
 */
public interface Transactor
{
    /**
     * Processes a transaction
     *
     * @param transaction the transaction to process
     * @return the completed transaction
     */
    CompletedTransaction handleTransaction(MutableTransaction transaction);
}

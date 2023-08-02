package fns.patchwork.economy;

/**
 * A class that intercepts transactions after they are completed and logs them to a data point
 */
public interface TransactionLogger
{
    /**
     * Logs a completed transaction
     *
     * @param completedTransaction the completed transaction to log
     */
    void logTransaction(CompletedTransaction completedTransaction);
}

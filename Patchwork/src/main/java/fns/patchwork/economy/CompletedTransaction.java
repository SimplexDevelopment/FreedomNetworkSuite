package fns.patchwork.economy;

/**
 * Represents an immutable transaction that has been fully handled by a {@link Transactor} instance
 */
public interface CompletedTransaction extends Transaction
{
    /**
     * Gets the final result of the {@link Transaction}
     *
     * @return the result
     */
    TransactionResult getResult();
}

package fns.fossil.economy;

import fns.patchwork.audience.MutableAudienceForwarder;
import fns.patchwork.economy.CompletedTransaction;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.TransactionLogger;
import fns.patchwork.economy.TransactionResult;
import fns.patchwork.utils.logging.FreedomLogger;
import net.kyori.adventure.text.Component;

public class SimpleTransactionLogger implements TransactionLogger
{
    private final MutableAudienceForwarder audience = MutableAudienceForwarder.from(FreedomLogger.getLogger("Fossil"));

    @Override
    public void logTransaction(final CompletedTransaction completedTransaction)
    {
        final StringBuilder transactionLoggingStatementBuilder = new StringBuilder();
        final TransactionResult result = completedTransaction.getResult();
        final boolean resultSuccess = result.isSuccessful();
        final String resultMessage = result.getMessage();

        final EconomicEntity source = completedTransaction.getSource();
        final EconomicEntity destination = completedTransaction.getDestination();
        final long transactionAmount = completedTransaction.getBalance();

        transactionLoggingStatementBuilder.append(resultSuccess
                                                  ? "Successful"
                                                  : "Unsuccessful")
                                          .append(" (")
                                          .append(resultMessage)
                                          .append(") ")
                                          .append(" transaction between ")
                                          .append(source.getName())
                                          .append(" ")
                                          .append(destination.getName())
                                          .append(" where the volume of currency transferred was $")
                                          .append(transactionAmount)
                                          .append(".");

        final Component message = Component.text(transactionLoggingStatementBuilder.toString());

        audience.sendMessage(message);
    }

    public MutableAudienceForwarder getAudienceForwarder()
    {
        return audience;
    }
}

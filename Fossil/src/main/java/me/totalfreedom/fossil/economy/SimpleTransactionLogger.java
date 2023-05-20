package me.totalfreedom.fossil.economy;

import me.totalfreedom.audience.MutableAudienceForwarder;
import me.totalfreedom.economy.*;
import me.totalfreedom.utils.FreedomLogger;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class SimpleTransactionLogger implements TransactionLogger
{
    private MutableAudienceForwarder audience = MutableAudienceForwarder.from(FreedomLogger.getLogger("Fossil"));

    @Override
    public void logTransaction(CompletedTransaction completedTransaction)
    {
        StringBuilder transactionLoggingStatementBuilder = new StringBuilder();
        TransactionResult result = completedTransaction.getResult();
        boolean resultSuccess = result.isSuccessful();
        String resultMessage = result.getMessage();

        Transaction transaction = completedTransaction.getTransaction();
        EconomicEntity source = transaction.getSource();
        EconomicEntity destination = transaction.getDestination();
        long transactionAmount = transaction.getBalance();

        transactionLoggingStatementBuilder.append(resultSuccess ? "Successful" : "Unsuccessful")
                .append(" (")
                .append(resultMessage)
                .append(") ")
                .append(" transaction between ")
                .append(source.getName())
                .append(" ")
                .append(destination.getName())
                .append(" where the volume of currency transferred was ")
                .append("$")
                .append(transactionAmount)
                .append(".");

        Component message = Component.text(transactionLoggingStatementBuilder.toString());

        audience.sendMessage(message);
    }
}

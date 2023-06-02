package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.TransactionResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SimpleTransactionResult implements TransactionResult
{
    public static final TransactionResult SUCCESSFUL = new SimpleTransactionResult("Successful transaction.", true);
    public static final TransactionResult UNAUTHORIZED = new SimpleTransactionResult("Unauthorized transaction.",
        false);
    public static final TransactionResult AMOUNT_TOO_SMALL = new SimpleTransactionResult(
        "Transaction balance too small.", false);
    public static final TransactionResult INSUFFICIENT_FUNDS = new SimpleTransactionResult(
        "The source has an insufficient balance to carry out this transaction.", false);
    private final String message;
    private final Component component;
    private final boolean successful;

    public SimpleTransactionResult(final String message, final boolean successful)
    {
        this(message, Component.text(message, successful
                                              ? NamedTextColor.GREEN
                                              : NamedTextColor.RED), successful);
    }

    public SimpleTransactionResult(final String message, final Component component, final boolean successful)
    {
        this.message = message;
        this.component = component;
        this.successful = successful;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public boolean isSuccessful()
    {
        return successful;
    }

    @Override
    public Component getComponent()
    {
        return component;
    }
}

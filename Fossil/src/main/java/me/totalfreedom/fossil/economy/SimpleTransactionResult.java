package me.totalfreedom.fossil.economy;

import me.totalfreedom.economy.TransactionResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SimpleTransactionResult implements TransactionResult
{
    private final String message;
    private final Component component;
    private final boolean successful;

    public SimpleTransactionResult(String message, boolean successful)
    {
        this(message, Component.text(message, successful ? NamedTextColor.GREEN : NamedTextColor.RED), successful);
    }

    public SimpleTransactionResult(String message, Component component, boolean successful)
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

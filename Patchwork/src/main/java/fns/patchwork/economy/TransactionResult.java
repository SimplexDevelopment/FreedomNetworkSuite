package fns.patchwork.economy;

import net.kyori.adventure.text.Component;

/**
 * A class that represents the result of a transaction
 */
public interface TransactionResult
{
    String getMessage();

    boolean isSuccessful();

    Component getComponent();
}

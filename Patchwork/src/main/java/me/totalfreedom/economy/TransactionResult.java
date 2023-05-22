package me.totalfreedom.economy;

import net.kyori.adventure.text.Component;

public interface TransactionResult
{
    String getMessage();

    boolean isSuccessful();

    Component getComponent();
}

/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.fossil.economy;

import fns.patchwork.economy.TransactionResult;
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
    private final boolean isSuccessful;

    public SimpleTransactionResult(final String message, final boolean isSuccessful)
    {
        this(message, Component.text(message, isSuccessful
            ? NamedTextColor.GREEN
            : NamedTextColor.RED), isSuccessful);
    }

    public SimpleTransactionResult(final String message, final Component component, final boolean isSuccessful)
    {
        this.message = message;
        this.component = component;
        this.isSuccessful = isSuccessful;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public boolean isSuccessful()
    {
        return isSuccessful;
    }

    @Override
    public Component getComponent()
    {
        return component;
    }
}

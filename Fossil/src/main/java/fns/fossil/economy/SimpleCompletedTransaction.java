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

import fns.patchwork.economy.CompletedTransaction;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.Transaction;
import fns.patchwork.economy.TransactionResult;

public class SimpleCompletedTransaction implements CompletedTransaction
{
    private final TransactionResult transactionResult;
    private final EconomicEntity source;
    private final EconomicEntity destination;
    private final long balance;

    public SimpleCompletedTransaction(final Transaction transaction, final TransactionResult transactionResult)
    {

        this.source = transaction.getSource();
        this.destination = transaction.getDestination();
        this.balance = transaction.getBalance();
        this.transactionResult = transactionResult;
    }

    @Override
    public TransactionResult getResult()
    {
        return transactionResult;
    }

    @Override
    public EconomicEntity getSource()
    {
        return source;
    }

    @Override
    public EconomicEntity getDestination()
    {
        return destination;
    }

    @Override
    public long getBalance()
    {
        return balance;
    }
}

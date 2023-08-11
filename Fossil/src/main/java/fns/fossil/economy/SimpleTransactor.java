/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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
import fns.patchwork.economy.EconomicEntityData;
import fns.patchwork.economy.MutableTransaction;
import fns.patchwork.economy.Transactor;

public class SimpleTransactor implements Transactor
{
    @Override
    public CompletedTransaction handleTransaction(final MutableTransaction transaction)
    {
        final EconomicEntity source = transaction.getSource();
        final EconomicEntityData sourceData = source.getEconomicData();

        if (sourceData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        final long transactionAmount = transaction.getBalance();

        if (transactionAmount <= 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.AMOUNT_TOO_SMALL);
        }

        final long sourceBalance = sourceData.getBalance();
        final long diff = sourceBalance - transactionAmount;

        if (diff > 0)
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.INSUFFICIENT_FUNDS);
        }

        final EconomicEntity destination = transaction.getDestination();
        final EconomicEntityData destinationData = destination.getEconomicData();

        if (destinationData.areTransactionsFrozen())
        {
            return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.UNAUTHORIZED);
        }

        sourceData.removeFromBalance(transactionAmount);
        destinationData.addToBalance(transactionAmount);

        return new SimpleCompletedTransaction(transaction, SimpleTransactionResult.SUCCESSFUL);
    }
}

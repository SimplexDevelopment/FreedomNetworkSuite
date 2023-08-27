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

import fns.patchwork.audience.MutableAudienceForwarder;
import fns.patchwork.economy.CompletedTransaction;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.TransactionLogger;
import fns.patchwork.economy.TransactionResult;
import fns.patchwork.utils.logging.FNS4J;
import net.kyori.adventure.text.Component;

public class SimpleTransactionLogger implements TransactionLogger
{
    private final MutableAudienceForwarder audience = MutableAudienceForwarder.from(FNS4J.getLogger("Fossil"));

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

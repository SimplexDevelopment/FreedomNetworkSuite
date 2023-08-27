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

package fns.patchwork.economy;

/**
 * Metadata associated with a {@link EconomicEntity}
 */
public interface EconomicEntityData
{
    /***
     * @return the transaction freeze state
     */
    boolean areTransactionsFrozen();

    /***
     * @return the balance
     */
    long getBalance();

    /**
     * Sets the balance of the associated instance
     *
     * @param newBalance the new balance
     */
    void setBalance(final long newBalance);

    /**
     * Adds the provided amount to the associated instance's balance
     *
     * @param amount the amount to add
     * @return the new balance
     */
    long addToBalance(final long amount);

    /**
     * Subtracts the provided amount from the associated instance's balance
     *
     * @param amount the amount to subtract
     * @return the new balance
     */
    long removeFromBalance(final long amount);
}

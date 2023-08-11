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

package fns.datura.user;

import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.EconomicEntityData;

/**
 * Represents the server's economy holder.
 * <br>
 * <br>
 * This is effectively a Bank object which is meant to represent the server itself, which can store a balance and
 * perform transactions with other EconomicEntity objects.
 * <br>
 * <br>
 * The server is initially given a maximum balance of {@link Long#MAX_VALUE}, though this can be changed using the
 * constructor {@link #ServerEconomyHolder(String, long)}. The value that this bank object holds is persistent, which
 * means that the total economic resources available are of limited supply.
 * <br>
 * <br>
 * Please be aware, if the server's economy falls below 0, it will have drastic consequences.
 */
public class ServerEconomyHolder implements EconomicEntity, EconomicEntityData
{
    private final String name;
    private long balance;

    /**
     * Constructs a new ServerEconomyHolder with the specified name and a balance of {@link Long#MAX_VALUE}.
     *
     * @param name The name of this server economy holder.
     */
    public ServerEconomyHolder(final String name)
    {
        this.name = name;
        this.balance = Long.MAX_VALUE;
    }

    /**
     * Constructs a new ServerEconomyHolder with the specified name and balance.
     *
     * @param name    The name of this server economy holder.
     * @param balance The balance of this server economy holder.
     */
    public ServerEconomyHolder(final String name, final long balance)
    {
        this.name = name;
        this.balance = balance;
    }

    /**
     * This method will return this object, as it is both the EconomicEntity and the EconomicEntityData. This is due to
     * the fact that the server should only ever have one singular concrete representation of it's economic entity and
     * the respective data.
     *
     * @return this object.
     */
    @Override
    public EconomicEntityData getEconomicData()
    {
        return this;
    }

    /**
     * @return The name of this server economy holder.
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * This method will always return false, as the server should not ever be prevented from performing transactions.
     *
     * @return false
     */
    @Override
    public boolean areTransactionsFrozen()
    {
        return false;
    }

    /**
     * @return The server's current available balance.
     */
    @Override
    public long getBalance()
    {
        return balance;
    }

    /**
     * Sets the server's balance to the specified value.
     *
     * @param newBalance The new balance to set.
     */
    @Override
    public void setBalance(final long newBalance)
    {
        balance = newBalance;
    }

    /**
     * Adds the specified amount to the server's balance. This method mutates the balance and returns the new balance.
     *
     * @param amount The amount to add.
     * @return The new balance.
     */
    @Override
    public long addToBalance(final long amount)
    {
        balance += amount;
        return balance;
    }

    /**
     * Removes the specified amount from the server's balance. This method mutates the balance and returns the new
     * balance.
     *
     * @param amount The amount to remove.
     * @return The new balance.
     */
    @Override
    public long removeFromBalance(final long amount)
    {
        balance -= amount;
        return balance;
    }
}

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

package fns.patchwork.service;

import java.time.Duration;

public abstract class TimedTask extends Task
{
    private final Duration timeout;
    private long currentTimeSeconds = 0;

    protected TimedTask(final String name, final Duration interval, final Duration timeout)
    {
        super(name, 0, interval);
        this.timeout = timeout;
    }

    protected abstract void runTimer();

    @Override
    public void run()
    {
        if (this.currentTimeSeconds == 0)
            this.currentTimeSeconds = System.currentTimeMillis() / 1000L;

        if (System.currentTimeMillis() / 1000L - this.currentTimeSeconds >= this.timeout.getSeconds())
        {
            this.cancel();
            return;
        }

        this.runTimer();
    }
}

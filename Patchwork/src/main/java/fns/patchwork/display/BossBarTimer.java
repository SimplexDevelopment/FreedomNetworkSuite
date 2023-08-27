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

package fns.patchwork.display;

import fns.patchwork.service.Task;
import java.time.Duration;
import org.bukkit.Bukkit;

public class BossBarTimer extends Task
{
    private final BossBarDisplay bossBarDisplay;
    private final Duration duration;
    private double seconds = 0;

    public BossBarTimer(final BossBarDisplay bossBarDisplay, final Duration duration)
    {
        super("BossBarTimer", -1L, 20L);
        this.bossBarDisplay = bossBarDisplay;
        this.duration = duration;
        bossBarDisplay.minimumProgress();
        bossBarDisplay.showTo(Bukkit.getServer());
    }

    @Override
    public void run()
    {
        if (this.isCancelled()) return;

        if (seconds >= duration.getSeconds())
        {
            bossBarDisplay.hideFrom(Bukkit.getServer());
            this.cancel();
            return;
        }

        final float percentage = (float) (seconds / duration.getSeconds()) * 100L;
        bossBarDisplay.incrementProgress(percentage);
        seconds++;
    }
}

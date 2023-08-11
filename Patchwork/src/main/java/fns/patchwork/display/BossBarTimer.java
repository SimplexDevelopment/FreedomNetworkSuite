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

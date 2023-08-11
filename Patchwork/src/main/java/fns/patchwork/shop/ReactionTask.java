package fns.patchwork.shop;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.display.BossBarDisplay;
import fns.patchwork.display.BossBarTimer;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.service.Task;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReactionTask extends Task implements Listener
{
    private final Reaction reaction;
    private final BossBarDisplay bossBarDisplay;

    public ReactionTask(final String name, final Reaction reaction)
    {
        super(name, -1L, -1);
        this.reaction = reaction;
        final BossBar bossBar = BossBarDisplay.builder()
                                              .setName(reaction.getReactionMessage())
                                              .setColor(BossBar.Color.GREEN)
                                              .setProgress(0.0F)
                                              .build();

        this.bossBarDisplay = new BossBarDisplay(bossBar);
    }

    @Override
    public void run()
    {
        if (isCancelled())
        {
        }

        final BossBarTimer timer = new BossBarTimer(bossBarDisplay, reaction.getReactionDuration());
        timer.runTaskTimer(Shortcuts.provideModule(Patchwork.class), 0L, timer.getInterval());
    }

    @EventHandler
    public void onPlayerChat(final AsyncChatEvent event)
    {
        if (event.message()
                 .equals(reaction.getReactionMessage()))
        {
            final EconomicEntity entity = Registration.getUserRegistry()
                                                   .getUser(event.getPlayer());

            reaction.onReact(entity);
        }
    }
}

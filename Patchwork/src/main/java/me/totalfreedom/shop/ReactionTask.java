package me.totalfreedom.shop;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.display.BossBarDisplay;
import me.totalfreedom.display.BossBarTimer;
import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.service.Task;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReactionTask extends Task implements Listener {
    private final Reaction reaction;
    private final BossBarDisplay bossBarDisplay;

    public ReactionTask(final String name, final Reaction reaction) {
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
    public void run() {
        if (isCancelled()) {
        }

        final BossBarTimer timer = new BossBarTimer(bossBarDisplay, reaction.getReactionDuration());
        timer.runTaskTimer(CommonsBase.getInstance(), 0L, timer.getInterval());
    }

    @EventHandler
    public void onPlayerChat(final AsyncChatEvent event) {
        if (event.message()
                .equals(reaction.getReactionMessage())) {
            final EconomicEntity entity = CommonsBase.getInstance()
                    .getRegistrations()
                    .getUserRegistry()
                    .getUser(event.getPlayer());

            reaction.onReact(entity);
        }
    }
}

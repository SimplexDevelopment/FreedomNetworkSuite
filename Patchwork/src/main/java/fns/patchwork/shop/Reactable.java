package fns.patchwork.shop;

import fns.patchwork.economy.EconomicEntity;
import java.time.Duration;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

public interface Reactable
{
    Component getReactionMessage();

    Duration getReactionDuration();

    ReactionType getReactionType();

    long getReward();

    void display(final Audience audience);

    void onReact(final EconomicEntity entity);
}

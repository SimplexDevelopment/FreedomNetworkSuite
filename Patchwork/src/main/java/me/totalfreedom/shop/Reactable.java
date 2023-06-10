package me.totalfreedom.shop;

import me.totalfreedom.economy.EconomicEntity;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

import java.time.Duration;

public interface Reactable
{
    Component getReactionMessage();

    Duration getReactionDuration();

    ReactionType getReactionType();

    long getReward();

    void display(final Audience audience);

    void onReact(final EconomicEntity entity);
}

package me.totalfreedom.shop;

import me.totalfreedom.economy.EconomicEntity;

import java.time.Duration;

public interface Reactable
{
    String getReactionMessage();

    Duration getReactionDuration();

    ReactionType getReactionType();

    void onReact(final EconomicEntity entity);
}

package me.totalfreedom.fossil.reactions;

import me.totalfreedom.display.BossBarDisplay;
import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.shop.Reaction;
import me.totalfreedom.shop.ReactionType;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;

import java.util.SplittableRandom;
import java.util.function.Consumer;

/**
 * Represents a single chat reaction that can be performed by a player.
 */
public final class CopyCatReaction extends Reaction
{
    private final long reward;

    public CopyCatReaction(final long reward)
    {
        super(ReactionType.COPYCAT);
        this.reward = reward;
    }

    @Override
    public long getReward()
    {
        return reward;
    }

    @Override
    public void onReact(final Consumer<EconomicEntity> entity)
    {
        entity.accept(null);
    }

    @Override
    public void display(final Audience audience)
    {
        final BossBar bossBar = BossBarDisplay.builder().setName(getRandomCharacterString())
                                     .setProgress(0.0F)
                                     .build();
    }

    public String getRandomCharacterString() {
        final SplittableRandom random = new SplittableRandom();
        final StringBuilder sb = new StringBuilder(10);

        final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}

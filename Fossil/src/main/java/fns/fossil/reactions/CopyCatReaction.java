package fns.fossil.reactions;

import fns.patchwork.display.BossBarDisplay;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.shop.Reaction;
import fns.patchwork.shop.ReactionType;
import java.util.SplittableRandom;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;

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
    public void display(final Audience audience)
    {
        final BossBar bossBar = BossBarDisplay.builder()
                                              .setName(getRandomCharacterString())
                                              .setProgress(0.0F)
                                              .build();
    }

    @Override
    public void onReact(final EconomicEntity entity)
    {
        //
    }

    public String getRandomCharacterString()
    {
        final SplittableRandom random = new SplittableRandom();
        final StringBuilder sb = new StringBuilder(10);

        final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 10; i++)
        {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}

package fns.fossil.trail.types;

import org.bukkit.entity.Player;

public final class TrailProvider
{
    public BasicTrail basicTrail(final Player player)
    {
        return new BasicTrail(player);
    }

}

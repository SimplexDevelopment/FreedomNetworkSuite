package me.totalfreedom.fossil.trail.types;

import me.totalfreedom.particle.TrailType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class HeartTrail extends SimpleTrail
{
    public HeartTrail(final Player player)
    {
        super(player, TrailType.HEART);
    }

    @Override
    public void spawnParticle()
    {
        if (!getAssociatedPlayer().isOnline() || !isActive()) return;

        final Player player = (Player) getAssociatedPlayer();
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);
        location.getWorld()
                .spawnParticle(getTrailType().getType(), location, 1, 0.0, 0.5, 0.0);
    }
}

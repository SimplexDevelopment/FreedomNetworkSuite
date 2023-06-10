package me.totalfreedom.fossil.trail.types;

import me.totalfreedom.particle.TrailType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public final class FlameTrail extends SimpleTrail
{
    public FlameTrail(final Player player)
    {
        super(player, TrailType.FLAME);
    }

    @Override
    public void spawnParticle()
    {
        if (!getAssociatedPlayer().isOnline() || !isActive()) return;

        final Player player = (Player) getAssociatedPlayer();
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);
        final Vector direction = location.getDirection();
        location.getWorld()
                .spawnParticle(getTrailType().getType(), location, 0, direction.getX(), direction.getY(),
                        direction.getZ(), 0.1);
    }
}

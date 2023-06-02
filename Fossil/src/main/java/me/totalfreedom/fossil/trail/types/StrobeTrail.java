package me.totalfreedom.fossil.trail.types;

import me.totalfreedom.particle.TrailType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public final class StrobeTrail extends SimpleTrail
{
    private final Particle.DustTransition dustTransition;

    public StrobeTrail(final Player player, final Color from, final Color to)
    {
        super(player, TrailType.STROBE);
        this.dustTransition = new Particle.DustTransition(from, to, 3F);
    }

    @Override
    public void spawnParticle()
    {
        if (!getAssociatedPlayer().isOnline() || !isActive()) return;

        final Player player = (Player) getAssociatedPlayer();
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);
        location.getWorld().spawnParticle(getTrailType().getType(), location, 1, 0.0, 0.5, 0.0, dustTransition);
    }
}

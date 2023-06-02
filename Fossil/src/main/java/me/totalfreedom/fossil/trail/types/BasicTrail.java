package me.totalfreedom.fossil.trail.types;

import me.totalfreedom.particle.TrailType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public final class BasicTrail extends SimpleTrail
{
    protected BasicTrail(final Player player)
    {
        super(player, TrailType.DEFAULT);
        super.setColor(Color.RED);
    }

    @Override
    public void spawnParticle()
    {
        // Exit immediately if either condition is false.
        if (!isActive() || !getAssociatedPlayer().isOnline()) return;

        // Trail is active and the player is online.
        final Particle particle = getTrailType().getType();
        final Particle.DustOptions options = new Particle.DustOptions(getColor(), 3);
        final Player player = (Player) getAssociatedPlayer();
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);
        location.getWorld().spawnParticle(particle, location, 1, 0.0, 0.5, 0.0, options);
    }
}

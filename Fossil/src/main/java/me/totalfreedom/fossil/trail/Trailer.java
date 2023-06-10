package me.totalfreedom.fossil.trail;

import me.totalfreedom.particle.Trail;
import me.totalfreedom.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Trailer extends Service
{
    private final List<Trail> activeTrails = new ArrayList<>();

    // Cannot be async due to interaction with the world, and API interactions MUST be synchronized.
    public Trailer()
    {
        super("trailer_service");
    }

    public void addTrail(final Trail trail)
    {
        this.activeTrails.add(trail);
    }

    public void removeTrail(final Trail trail)
    {
        this.activeTrails.remove(trail);
    }

    @Override
    public void tick()
    {
        for (final Trail trail : activeTrails)
        {
            trail.spawnParticle();
        }
    }
}

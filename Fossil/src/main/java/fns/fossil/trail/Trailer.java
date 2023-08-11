package fns.fossil.trail;

import fns.patchwork.particle.Trail;
import fns.patchwork.service.Service;
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

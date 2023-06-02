package me.totalfreedom.fossil.trail;

import me.totalfreedom.particle.Trail;
import me.totalfreedom.particle.TrailType;
import me.totalfreedom.service.Service;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Trailer extends Service
{
    private final List<Trail> trailList = new ArrayList<>();

    public Trailer() {
        super("trailer_service");
    }

    public void addTrail(final Trail trail) {
        this.trailList.add(trail);
    }

    public void removeTrail(final Trail trail) {
        this.trailList.remove(trail);
    }

    @Override
    public void tick()
    {
        for (final Trail trail : trailList) {
            if (trail.getAssociatedPlayer().isOnline()) {
                final Player player = (Player) trail.getAssociatedPlayer();

            }
        }
    }
}

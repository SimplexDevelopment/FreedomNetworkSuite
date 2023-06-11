package me.totalfreedom.datura.punishment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Halter implements Listener
{
    private final Set<UUID> haltedPlayers;

    public Halter()
    {
        this.haltedPlayers = new HashSet<>();
    }

    public void halt(final UUID uuid)
    {
        this.haltedPlayers.add(uuid);
    }

    public void stop(final UUID uuid)
    {
        this.haltedPlayers.remove(uuid);
    }

    public void clear() {
        this.haltedPlayers.clear();
    }

    @EventHandler
    public void playerMove(final PlayerMoveEvent event)
    {
        if (haltedPlayers.contains(event.getPlayer()
                                        .getUniqueId()))
        {
            event.setCancelled(true);
        }
    }
}

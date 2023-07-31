package me.totalfreedom.datura.features;

import me.totalfreedom.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Fuckoff extends Service
{
    private final Map<UUID, Integer> players = new ConcurrentHashMap<>();

    public Fuckoff()
    {
        super("fuckoff-service");
    }

    public void add(final Player player, final int radius)
    {
        players.put(player.getUniqueId(), radius);
    }

    public void remove(final Player player)
    {
        players.remove(player.getUniqueId());
    }

    @Override
    public void tick()
    {
        for (Map.Entry<UUID, Integer> entry : players.entrySet())
        {
            final var player = Bukkit.getPlayer(entry.getKey());

            if (player == null)
            {
                players.remove(entry.getKey());
                continue;
            }

            pushOfPlayers(player, entry.getValue());
        }
    }

    private void pushOfPlayers(@NotNull final Player player, final int radius)
    {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(onlinePlayer -> onlinePlayer.getLocation().distanceSquared(player.getLocation()) < (radius * radius))
                .forEach(onlinePlayer -> {
                    onlinePlayer.setVelocity(
                            player.getLocation().toVector()
                                    .add(onlinePlayer.getLocation().toVector()).normalize().multiply(radius)
                    );
        });
    }
}

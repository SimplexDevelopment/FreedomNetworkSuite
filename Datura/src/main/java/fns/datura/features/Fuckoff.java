package fns.datura.features;

import fns.patchwork.service.Service;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        for (final Map.Entry<UUID, Integer> entry : players.entrySet())
        {
            final var player = Bukkit.getPlayer(entry.getKey());

            if (player == null)
            {
                players.remove(entry.getKey());
                continue;
            }

            pushPlayers(player, entry.getValue());
        }
    }

    private void pushPlayers(@NotNull final Player player, final int radius)
    {
        Bukkit.getOnlinePlayers()
              .stream()
              .filter(onlinePlayer -> onlinePlayer.getLocation()
                                                  .clone()
                                                  .distanceSquared(player.getLocation()) < Math.pow(radius, 2))
              .forEach(onlinePlayer ->
                           onlinePlayer.setVelocity(player.getLocation()
                                                          .toVector()
                                                          .add(onlinePlayer.getLocation().toVector())
                                                          .normalize()
                                                          .multiply(radius)));
    }
}

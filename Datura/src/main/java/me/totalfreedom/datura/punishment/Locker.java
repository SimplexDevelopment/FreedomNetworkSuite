package me.totalfreedom.datura.punishment;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.UUID;

public class Locker extends Service
{
    private final Set<UUID> lockedPlayers = new HashSet<>();

    public Locker()
    {
        super("locker-service");
    }

    public void lock(final Player player)
    {
        lockedPlayers.add(player.getUniqueId());
    }

    public void unlock(final Player player)
    {
        lockedPlayers.remove(player.getUniqueId());
    }

    @Override
    public void tick()
    {
        lockedPlayers.removeIf(uuid -> !CommonsBase.getInstance()
                                                   .getServer()
                                                   .getOfflinePlayer(uuid)
                                                   .isOnline());

        for (final UUID uuid : lockedPlayers)
        {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null) continue;

            lockingMethod(player);
        }
    }

    private void lockingMethod(@NotNull final Player player)
    {
        final double x = player.getLocation()
                               .getX();
        final double z = player.getLocation()
                               .getZ();

        if ((x / z % 0.001) < 1)
        {
            player.setVelocity(new Vector(x % 12, 0, z % 12));
        }

        player.setWalkSpeed(0.0f);
        player.setFlySpeed(0.0f);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setInvulnerable(true);
        player.setCollidable(false);
        player.setGliding(false);
        player.setGlowing(true);
        player.setSilent(true);
        player.setCanPickupItems(false);
        player.setInvisible(true);

        player.openInventory(Bukkit.createInventory(null, 54));
        player.closeInventory(InventoryCloseEvent.Reason.UNKNOWN);
        player.teleport(player.getLocation()
                              .clone());

        final SplittableRandom random = new SplittableRandom();
        player.getEyeLocation()
              .add(new Vector(
                      random.nextDouble(-1.0, 1.0),
                      random.nextDouble(-1.0, 1.0),
                      random.nextDouble(-1.0, 1.0)
              ));
    }
}

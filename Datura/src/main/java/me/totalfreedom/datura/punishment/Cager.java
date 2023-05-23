package me.totalfreedom.datura.punishment;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.service.Service;
import me.totalfreedom.utils.Shaper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.DoubleUnaryOperator;

public class Cager extends Service
{
    private final Set<UUID> cagedPlayers;
    private final Map<UUID, Location> cageLocations;

    public Cager()
    {
        super("cage_service");
        this.cagedPlayers = new HashSet<>();
        this.cageLocations = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new CageListener(), CommonsBase.getInstance());
    }

    /**
     * This method will cage the player using {@link #createCage(Location, Material)}.
     * <p>This will also add the returned location to the {@link #cageLocations} map.
     *
     * @param uuid The UUID of the player to cage.
     */
    public void cagePlayer(final UUID uuid)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player == null) return;

        cagedPlayers.add(uuid);
        cageLocations.put(uuid, createCage(player.getLocation(), Material.GLASS));
    }

    public void cagePlayer(final UUID uuid, final Material material)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player == null) return;

        cagedPlayers.add(uuid);
        cageLocations.put(uuid, createCage(player.getLocation(), material));
    }

    /**
     * This method will uncage the player by removing them from the {@link #cagedPlayers} set.
     *
     * @param uuid The UUID of the player to uncage.
     */
    public void uncagePlayer(final UUID uuid)
    {
        cagedPlayers.remove(uuid);
        final Location location = cageLocations.get(uuid);

        createCage(location, Material.AIR); // Remove the cage (set all blocks to air).

        cageLocations.remove(uuid);
    }

    /**
     * This method will check to make sure each caged player remains within their cage.
     * We use
     * <p>
     * <code>{@link Location#distanceSquared(Location)} * {@link Math#pow(double, double)}</code>
     * <p>
     * to check if the player is outside the cage.
     */
    @Override
    public void tick()
    {
        for (final UUID uuid : cagedPlayers)
        {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null) continue;

            final Location cageLocation = getCageLocation(player);

            final boolean inside;
            if (!player.getWorld().equals(cageLocation.getWorld()))
            {
                inside = false;
            } else
            {
                inside = player.getLocation().distanceSquared(cageLocation) > (Math.pow(2.5, 2.0));
            }

            if (!inside)
            {
                player.teleport(cageLocation);
            }
        }
    }

    /**
     * This method returns whether the player is caged.
     * <p>This method requires the player to be online to execute properly.</p>
     *
     * @param player The player to check.
     * @return Whether the player is caged.
     */
    public Location getCageLocation(final Player player)
    {
        return cageLocations.get(player.getUniqueId());
    }

    /**
     * This method generates a cube centered around the passed location,
     * made of the provided material. This method returns the passed location object.
     * We use the {@link Shaper} class to generate the cube, which allows us to define
     * custom shapes using {@link DoubleUnaryOperator}s.
     *
     * @param location The location to center the cube around.
     * @param material The material to use for the cube.
     * @return The center location of the cube (the passed location).
     * @see Shaper
     * @see DoubleUnaryOperator
     */
    public Location createCage(final Location location, final Material material)
    {
        final Shaper shaper = new Shaper(location.getWorld(), 0.0, 4.0);
        final List<Location> cubed = new LinkedList<>();
        cubed.addAll(shaper.generate(5, t -> t, t -> 4.0, t -> t));
        cubed.addAll(shaper.generate(5, t -> t, t -> 0.0, t -> t));
        cubed.addAll(shaper.generate(5, t -> 0.0, t -> t, t -> t));
        cubed.addAll(shaper.generate(5, t -> 4.0, t -> t, t -> t));
        cubed.addAll(shaper.generate(5, t -> t, t -> t, t -> 0.0));
        cubed.addAll(shaper.generate(5, t -> t, t -> t, t -> 4.0));

        for (final Location l : cubed)
        {
            location.getWorld().getBlockAt(l).setType(material);
        }

        return location.clone(); // Return the passed location as that is the center of the cube.
    }

    private final class CageListener implements Listener
    {
        @EventHandler
        public void blockBreakEvent(final BlockBreakEvent event)
        {
            if (cagedPlayers.contains(event.getPlayer().getUniqueId()))
            {
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void playerLeaveEvent(final PlayerQuitEvent event)
        {
            if (cagedPlayers.contains(event.getPlayer().getUniqueId()))
            {
                uncagePlayer(event.getPlayer().getUniqueId());
            }
        }
    }
}

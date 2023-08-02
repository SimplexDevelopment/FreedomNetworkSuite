package fns.corvo.listener;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractionListener implements Listener
{
    @EventHandler
    public void playerBreakBlock(final BlockBreakEvent event)
    {

    }

    @EventHandler
    public void blockBreakBlock(final BlockBreakBlockEvent event)
    {

    }

    @EventHandler
    public void playerOpenContainer(final InventoryOpenEvent event)
    {

    }

    @EventHandler
    public void playerCloseContainer(final InventoryCloseEvent event)
    {

    }

    @EventHandler
    public void playerInteraction(final PlayerInteractEvent event)
    {

    }

}

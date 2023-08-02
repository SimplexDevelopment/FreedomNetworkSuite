package fns.patchwork.logging;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ContainerInteraction implements Interaction<List<ItemStack>>
{
    private final UUID whoClicked;
    private final List<ItemStack> originalState;
    private final List<ItemStack> newState;
    private final Instant when;
    private final Location location;

    public ContainerInteraction(final Player player, final Container originalState, final Container newState)
    {
        this.whoClicked = player.getUniqueId();
        this.originalState = Collections.unmodifiableList(Arrays.asList(originalState.getInventory()
                                                                                     .getContents()));
        this.newState = Collections.unmodifiableList(Arrays.asList(newState.getInventory()
                                                                           .getContents()));
        this.location = originalState.getLocation();
        this.when = Instant.now();
    }

    @Override
    public @NotNull UUID getWhoClicked()
    {
        return whoClicked;
    }

    @Override
    public @NotNull List<ItemStack> getOriginalState()
    {
        return originalState;
    }

    @Override
    public @NotNull List<ItemStack> getNewState()
    {
        return newState;
    }

    @Override
    public @NotNull Instant getWhen()
    {
        return when;
    }

    @Override
    public @NotNull Location getLocation()
    {
        return location;
    }
}

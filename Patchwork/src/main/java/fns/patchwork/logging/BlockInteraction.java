package fns.patchwork.logging;

import java.time.Instant;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class BlockInteraction implements Interaction<BlockData>
{
    private final Location location;
    private final UUID whoClicked;
    private final Instant when;
    private final BlockData originalState;
    private final BlockData newState;

    public BlockInteraction(final Player player, final BlockData originalState, final BlockData newState)
    {
        this.location = player.getLocation();
        this.whoClicked = player.getUniqueId();
        this.when = Instant.now();
        this.originalState = originalState;
        this.newState = newState;
    }

    @Override
    public @NotNull UUID getWhoClicked()
    {
        return whoClicked;
    }

    @Override
    public @NotNull BlockData getOriginalState()
    {
        return originalState;
    }

    @Override
    public @NotNull BlockData getNewState()
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

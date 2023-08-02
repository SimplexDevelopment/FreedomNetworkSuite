package fns.fossil.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ClownfishItem extends ShopItem
{
    public ClownfishItem()
    {
        super(Material.TROPICAL_FISH);
    }

    @Override
    public void runAction(final @NotNull Player user, final @Nullable Entity target)
    {
        if (target == null) return;

        final Location location = user.getEyeLocation()
                                      .clone();
        final Vector vector = location.getDirection()
                                      .multiply(2);

        target.setVelocity(vector);
    }
}

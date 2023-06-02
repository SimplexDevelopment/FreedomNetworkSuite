package me.totalfreedom.fossil.items;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TrailItem extends ShopItem
{
    public TrailItem()
    {
        super(Material.LINGERING_POTION);
    }

    @Override
    public void runAction(final @NotNull Player user, final @Nullable Entity target)
    {

    }
}

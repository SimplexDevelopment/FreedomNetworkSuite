package fns.fossil.items;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ShopItem
{
    private final ItemStack item;
    private final ItemMeta meta;

    protected ShopItem(final Material material)
    {
        this.item = new ItemStack(material, 1);

        this.meta = this.item.getItemMeta();
    }

    public abstract void runAction(@NotNull final Player user, @Nullable final Entity target);

    public ItemStack getItem()
    {
        return this.item;
    }

    public ItemMeta getMeta()
    {
        return this.meta;
    }
}

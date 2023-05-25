package me.totalfreedom.display;

import me.totalfreedom.utils.FreedomAdventure;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

public class DisplayableView extends InventoryView
{
    private final Displayable top;
    private final Displayable bottom;
    private final Player player;
    private final InventoryType type;
    private String title;

    public DisplayableView(final Player player, final Displayable top, final Displayable bottom) {
        this.player = player;
        this.top = top;
        this.bottom = bottom;
        this.type = InventoryType.CHEST;
        this.title = FreedomAdventure.toPlainText(type.defaultTitle());
    }

    @Override
    public @NotNull Inventory getTopInventory()
    {
        return top;
    }

    @Override
    public @NotNull Inventory getBottomInventory()
    {
        return bottom;
    }

    @Override
    public @NotNull HumanEntity getPlayer()
    {
        return player;
    }

    @Override
    public @NotNull InventoryType getType()
    {
        return type;
    }

    @Override
    public @NotNull String getTitle()
    {
        return title;
    }

    @Override
    public @NotNull String getOriginalTitle()
    {
        return FreedomAdventure.toPlainText(type.defaultTitle());
    }

    @Override
    public void setTitle(final @NotNull String title)
    {
        this.title = title;
    }
}

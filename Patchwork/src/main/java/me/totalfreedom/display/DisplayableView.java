package me.totalfreedom.display;

import me.totalfreedom.utils.FreedomAdventure;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * A view of a {@link Displayable} inventory.
 * <p>
 * This class can be used to display two separate {@link Displayable} objects to the Player.
 */
public class DisplayableView extends InventoryView
{
    /**
     * The upper inventory involved in this transaction.
     */
    private final Displayable top;
    /**
     * The lower inventory involved in this transaction.
     */
    private final Displayable bottom;
    /**
     * The player viewing the inventories involved in this transaction.
     */
    private final Player player;
    /**
     * The type of inventory this transaction is for.
     */
    private final InventoryType type;
    /**
     * The title of the main inventory involved in this transaction. The main inventory should always be the top
     * inventory.
     */
    private String title;

    /**
     * Creates a new DisplayableView.
     *
     * @param player The player viewing the inventories involved in this transaction.
     * @param top    The upper inventory involved in this transaction.
     * @param bottom The lower inventory involved in this transaction.
     */
    public DisplayableView(final Player player, final Displayable top, final Displayable bottom)
    {
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
    public void setTitle(final @NotNull String title)
    {
        this.title = title;
    }

    @Override
    public @NotNull String getOriginalTitle()
    {
        return FreedomAdventure.toPlainText(type.defaultTitle());
    }
}

package me.totalfreedom.display;

import org.bukkit.entity.Player;

/**
 * Represents an action to be performed when a player clicks on an inventory slot in the respective {@link AbstractMenu}.
 */
@FunctionalInterface
public interface ClickAction
{
    /**
     * Called when a player clicks on an inventory slot in the respective {@link AbstractMenu}.
     * @param player The player who clicked.
     */
    void onClick(final Player player);
}

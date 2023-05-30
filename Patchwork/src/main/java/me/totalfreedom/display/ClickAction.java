package me.totalfreedom.display;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface ClickAction
{
    void onClick(final Player player);
}

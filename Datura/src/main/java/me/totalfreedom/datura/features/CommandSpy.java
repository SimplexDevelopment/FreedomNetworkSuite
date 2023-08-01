package me.totalfreedom.datura.features;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommandSpy implements Listener
{

    private final Set<UUID> commandWatchers;

    public CommandSpy()
    {
        this.commandWatchers = new HashSet<>();
    }

    public void spy(final UUID uuid)
    {
        this.commandWatchers.add(uuid);
    }

    public void stop(final UUID uuid)
    {
        this.commandWatchers.remove(uuid);
    }

    public void clear()
    {
        this.commandWatchers.clear();
    }

    public boolean isSpying(final UUID uuid)
    {
        return this.commandWatchers.contains(uuid);
    }

    @EventHandler
    public void commandProcess(final PlayerCommandPreprocessEvent event)
    {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> isSpying(player.getUniqueId()))
                .forEach(player -> player.sendMessage(Component.text(event.getPlayer().getName(), NamedTextColor.GRAY)
                        .append(Component.text(": ", NamedTextColor.GRAY))
                        .append(Component.text(event.getMessage(), NamedTextColor.GRAY))
                )
        );
    }
}

package me.totalfreedom.display.adminchat;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.totalfreedom.base.Patchwork;
import me.totalfreedom.base.Shortcuts;
import me.totalfreedom.data.UserRegistry;
import me.totalfreedom.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AdminChatDisplay
{
    private final Map<UUID, AdminChatFormat> adminChatFormat = new HashMap<>();
    private final Set<UUID> toggledChat = new HashSet<>();

    public AdminChatDisplay() {
        new ACListener(this);
    }

    public void addPlayer(final Player player, final AdminChatFormat format)
    {
        adminChatFormat.put(player.getUniqueId(), format);
    }

    public void removePlayer(final Player player)
    {
        adminChatFormat.remove(player.getUniqueId());
    }

    public boolean hasPlayer(final Player player)
    {
        return adminChatFormat.containsKey(player.getUniqueId());
    }

    public void updateFormat(final Player player, final AdminChatFormat newFormat)
    {
        adminChatFormat.put(player.getUniqueId(), newFormat);
    }

    public AdminChatFormat getFormat(final Player player)
    {
        return adminChatFormat.get(player.getUniqueId());
    }

    public Set<UUID> getPlayers()
    {
        return adminChatFormat.keySet();
    }

    public Map<UUID, AdminChatFormat> getAdminChatFormat()
    {
        return adminChatFormat;
    }

    public static final class ACListener implements Listener
    {
        private final AdminChatDisplay display;

        public ACListener(final AdminChatDisplay display)
        {
            this.display = display;
            Bukkit.getPluginManager()
                  .registerEvents(this, Shortcuts.provideModule(Patchwork.class)
                                                 .getModule());
        }

        @EventHandler
        public void playerChat(final AsyncChatEvent event) {
            if (display.getPlayers().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);

            }
        }

        @EventHandler
        public void playerJoin(final PlayerJoinEvent event) {
            final Player player = event.getPlayer();
            if (player.hasPermission("patchwork.adminchat")) {
                final UserData data = Patchwork.getInstance().getRegistrations().getUserRegistry().fromPlayer(player);
                if (data.hasCustomACFormat()) {
                    display.addPlayer(player, data.getCustomACFormat());
                } else {
                    display.addPlayer(player, AdminChatFormat.DEFAULT);
                }
            }
        }
    }
}

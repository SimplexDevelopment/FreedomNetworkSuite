package me.totalfreedom.base;

import me.totalfreedom.api.Context;
import me.totalfreedom.user.User;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shortcuts
{
    private Shortcuts()
    {
        throw new AssertionError();
    }

    public static <T extends JavaPlugin> T provideModule(final Class<T> pluginClass)
    {
        return Patchwork.getInstance()
                        .getRegistrations()
                        .getModuleRegistry()
                        .getProvider(pluginClass)
                        .getModule();
    }

    public static User getUser(final Player player)
    {
        return Patchwork.getInstance()
                              .getRegistrations()
                              .getUserRegistry()
                              .getUser(player);
    }
}

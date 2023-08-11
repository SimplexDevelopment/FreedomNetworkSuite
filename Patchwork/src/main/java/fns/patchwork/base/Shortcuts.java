package fns.patchwork.base;

import fns.patchwork.user.User;
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
        return Registration.getModuleRegistry()
                              .getProvider(pluginClass)
                              .getModule();
    }

    public static User getUser(final Player player)
    {
        return Registration.getUserRegistry()
                              .getUser(player);
    }
}

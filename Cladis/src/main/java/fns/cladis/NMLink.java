package fns.cladis;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import nl.chimpgamer.networkmanager.api.NetworkManagerPlugin;
import nl.chimpgamer.networkmanager.api.NetworkManagerProvider;
import nl.chimpgamer.networkmanager.api.cache.CacheManager;
import nl.chimpgamer.networkmanager.api.models.permissions.Group;
import nl.chimpgamer.networkmanager.api.models.permissions.PermissionPlayer;
import nl.chimpgamer.networkmanager.api.models.player.Player;

public class NMLink
{
    private NetworkManagerPlugin networkManager;

    public NMLink(final Cladis plugin)
    {
        this.networkManager = NetworkManagerProvider.Companion.get();
        plugin.getSLF4JLogger().info("NetworkManager successfully linked!");
    }

    public CacheManager getCacheManager()
    {
        return this.networkManager.getCacheManager();
    }

    public Optional<Player> getPlayer(final UUID uuid)
    {
        return getCacheManager().getCachedPlayers().getPlayerSafe(uuid);
    }

    public PermissionPlayer getPermissionsPlayer(final UUID uuid)
    {
        return networkManager.getPermissionManager().getPermissionPlayer(uuid);
    }

    public List<Group> getPlayerGroups(final UUID uuid)
    {
        return getPermissionsPlayer(uuid).getGroups();
    }

    public Map<Integer, Group> getLoadedGroups()
    {
        return networkManager.getPermissionManager().getGroups();
    }

    public Optional<Group> locateGroup(final String name)
    {
        return Optional.of(networkManager.getPermissionManager().getGroup(name));
    }

    public Group opGroup()
    {
        return locateGroup("fake_op").orElse(null);
    }

    public Group deopGroup()
    {
        return locateGroup("fake_deop").orElse(null);
    }

    public Group masterBuilderGroup()
    {
        return locateGroup("master_builder").orElse(null);
    }

    public Group adminGroup()
    {
        return locateGroup("admin").orElse(null);
    }

    public Group seniorGroup()
    {
        return locateGroup("senior").orElse(null);
    }

    public Group devGroup()
    {
        return locateGroup("developer").orElse(null);
    }

    public Group execGroup()
    {
        return locateGroup("executive").orElse(null);
    }

    public boolean isOp(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(opGroup());
    }

    public boolean isDeop(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(deopGroup());
    }

    public boolean isAdmin(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(seniorGroup()) || getPlayerGroups(uuid).contains(adminGroup());
    }

    public boolean isSenior(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(seniorGroup());
    }

    public boolean isDev(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(devGroup());
    }

    public boolean isExec(final UUID uuid)
    {
        return getPlayerGroups(uuid).contains(execGroup());
    }
}

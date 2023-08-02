package fns.patchwork.data;

import fns.patchwork.user.User;
import fns.patchwork.user.UserData;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

/**
 * A registry for {@link UserData} objects.
 */
public class UserRegistry
{
    /**
     * A map of {@link User} objects to {@link UserData} objects.
     */
    private final Map<User, UserData> userDataMap;

    /**
     * Creates a new {@link UserRegistry}.
     */
    public UserRegistry()
    {
        this.userDataMap = new HashMap<>();
    }

    /**
     * Gets the {@link UserData} object for the given {@link User}.
     *
     * @param user The {@link User} to get the {@link UserData} for.
     * @return The {@link UserData} object for the given {@link User}.
     */
    public UserData getUserData(final User user)
    {
        return userDataMap.get(user);
    }

    /**
     * Gets the {@link UserData} object for the given {@link Player}.
     */
    public UserData fromPlayer(final Player player)
    {
        return userDataMap.entrySet()
                          .stream()
                          .filter(entry -> entry.getKey()
                                                .getUniqueId()
                                                .equals(player.getUniqueId()))
                          .findFirst()
                          .map(Map.Entry::getValue)
                          .orElse(null);
    }

    /**
     * Gets the {@link User} object for the given {@link Player}.
     *
     * @param player The {@link Player} to get the {@link User} for.
     * @return The {@link User} object for the given {@link Player}.
     */
    public User getUser(final Player player)
    {
        return userDataMap.entrySet()
                          .stream()
                          .filter(entry -> entry.getKey()
                                                .getUniqueId()
                                                .equals(player.getUniqueId()))
                          .findFirst()
                          .map(Map.Entry::getKey)
                          .orElse(null);
    }

    /**
     * Registers the given {@link User} and {@link UserData} objects.
     *
     * @param user     The {@link User} to register.
     * @param userData The {@link UserData} to register.
     */
    public void registerUserData(final User user, final UserData userData)
    {
        userDataMap.put(user, userData);
    }

    /**
     * Unregisters the given {@link User} and {@link UserData} objects.
     *
     * @param user The {@link User} to unregister.
     */
    public void unregisterUserData(final User user)
    {
        userDataMap.remove(user);
    }

    /**
     * Gets the map of {@link User} objects to {@link UserData} objects.
     *
     * @return The map of {@link User} objects to {@link UserData} objects.
     */
    public Map<User, UserData> getUserDataMap()
    {
        return userDataMap;
    }
}

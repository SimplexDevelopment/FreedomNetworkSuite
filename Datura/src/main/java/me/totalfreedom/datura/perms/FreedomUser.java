package me.totalfreedom.datura.perms;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.datura.Datura;
import me.totalfreedom.datura.user.SimpleUserData;
import me.totalfreedom.security.Node;
import me.totalfreedom.user.User;
import me.totalfreedom.user.UserData;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The superinterface User extends PermissionHolder,
 * which is an extension of {@link org.bukkit.permissions.Permissible}.
 * This means that our permission data can be interchanged with other permission plugins.
 */
public class FreedomUser implements User
{
    private final UUID uuid;
    private final Set<Node> permissions;
    private final Map<Node, PermissionAttachment> bukkitAttachments = new HashMap<>();
    private final Component displayName;
    private final String NOT_ONLINE = "Player is not online";
    private final UserData userData;

    public FreedomUser(final Player player)
    {
        this.uuid = player.getUniqueId();
        this.permissions = new HashSet<>();
        this.displayName = player.displayName();

        final Datura datura = CommonsBase.getInstance()
                                         .getRegistrations()
                                         .getModuleRegistry()
                                         .getModule(Datura.class)
                                         .getModule();

        UserData data = SimpleUserData.fromSQL(datura.getSQL(), uuid.toString());

        if (data == null)
        {
            data = new SimpleUserData(player);
        }

        this.userData = data;

        CommonsBase.getInstance()
                   .getRegistrations()
                   .getUserRegistry()
                   .registerUserData(this, userData);
    }

    @Override
    public UserData getUserData()
    {
        return userData;
    }

    @Override
    public Component getDisplayName()
    {
        return displayName;
    }

    @Override
    public boolean isOnline()
    {
        return Bukkit.getPlayer(uuid) != null;
    }

    @Override
    public UUID getUniqueId()
    {
        return uuid;
    }

    @Override
    public Set<Node> permissions()
    {
        return permissions;
    }

    @Override
    public boolean addPermission(final Node node)
    {
        final PermissionAttachment attachment = addAttachment(CommonsBase.getInstance(), node.key(), node.value());
        bukkitAttachments.put(node, attachment);
        return permissions().add(node);
    }

    @Override
    public boolean removePermission(final Node node)
    {
        removeAttachment(bukkitAttachments.get(node));
        bukkitAttachments.remove(node);
        return permissions.remove(node);
    }

    @Override
    public boolean isPermissionSet(@NotNull final String name)
    {
        final Player player = Bukkit.getPlayer(uuid);
        return player != null && player.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(@NotNull final Permission perm)
    {
        final Player player = Bukkit.getPlayer(uuid);
        return player != null && player.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(@NotNull final String name)
    {
        final Player player = Bukkit.getPlayer(uuid);
        return player != null && player.hasPermission(name);
    }

    @Override
    public boolean hasPermission(@NotNull final Permission perm)
    {
        final Player player = Bukkit.getPlayer(uuid);
        return player != null && player.hasPermission(perm);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
        final boolean value)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            return player.addAttachment(plugin, name, value);
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull final Plugin plugin)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            return player.addAttachment(plugin);
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
        final boolean value, final int ticks)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            return player.addAttachment(plugin, name, value, ticks);
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull final Plugin plugin, final int ticks)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            return player.addAttachment(plugin, ticks);
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public void removeAttachment(@NotNull final PermissionAttachment attachment)
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            player.removeAttachment(attachment);
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public void recalculatePermissions()
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            player.recalculatePermissions();
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions()
    {
        final Player player = Bukkit.getPlayer(uuid);
        if (player != null)
        {
            return player.getEffectivePermissions();
        }

        throw new IllegalStateException(NOT_ONLINE);
    }

    @Override
    public boolean isOp()
    {
        return permissions().contains(DefaultNodes.OP);
    }

    @Override
    public void setOp(final boolean value)
    {
        if (value)
        {
            permissions().add(DefaultNodes.OP);
        } else
        {
            permissions().remove(DefaultNodes.OP);
        }
    }
}

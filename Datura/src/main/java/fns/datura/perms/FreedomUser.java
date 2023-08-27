/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.datura.perms;

import fns.datura.Datura;
import fns.datura.user.SimpleUserData;
import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.permissible.Node;
import fns.patchwork.user.User;
import fns.patchwork.user.UserData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The superinterface User extends PermissionHolder, which is an extension of
 * {@link org.bukkit.permissions.Permissible}. This means that our permission data can be interchanged with other
 * permission plugins.
 */
public class FreedomUser implements User
{
    private final UUID uuid;
    private final Set<Node> permissions;
    private final Map<Node, PermissionAttachment> bukkitAttachments = new HashMap<>();
    private final Component displayName;
    private static final String NOT_ONLINE = "Player is not online";
    private final UserData userData;

    public FreedomUser(final Player player)
    {
        this.uuid = player.getUniqueId();
        this.permissions = new HashSet<>();
        this.displayName = player.displayName();

        final Datura datura = Shortcuts.provideModule(Datura.class);

        UserData data = SimpleUserData.fromSQL(datura.getSQL(), uuid.toString());

        if (data == null)
        {
            data = new SimpleUserData(player);
        }

        this.userData = data;

        Registration
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
        final boolean value = !node.isTemporary() || node.isExpired();
        final PermissionAttachment attachment = addAttachment(Shortcuts.provideModule(Patchwork.class), node.key(), value);
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

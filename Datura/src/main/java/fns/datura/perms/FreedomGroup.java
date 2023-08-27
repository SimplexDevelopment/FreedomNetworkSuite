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

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.permissible.Group;
import fns.patchwork.permissible.Node;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FreedomGroup implements Group
{
    private final Component name;
    private final Component prefix;
    private final Component abbreviation;
    private final int weight;
    private final boolean isDefault;
    private final boolean isHidden;
    private final Set<Node> permissions;
    private final PermissionAttachment attachment;

    public FreedomGroup(final Component name,
                        final Component prefix,
                        final Component abbreviation,
                        final int weight,
                        final boolean isDefault,
                        final boolean isHidden)
    {
        this.name = name;
        this.prefix = prefix;
        this.abbreviation = abbreviation;
        this.weight = weight;
        this.isDefault = isDefault;
        this.isHidden = isHidden;
        this.permissions = new HashSet<>();
        this.attachment = new PermissionAttachment(Shortcuts.provideModule(Patchwork.class), this);
    }

    @Override
    public UUID getUniqueId()
    {
        return UUID.nameUUIDFromBytes(getName().toString()
                                               .getBytes());
    }

    @Override
    public Component getName()
    {
        return name;
    }

    @Override
    public Component getPrefix()
    {
        return prefix;
    }

    @Override
    public Component getAbbreviation()
    {
        return abbreviation;
    }

    @Override
    public int getWeight()
    {
        return weight;
    }

    @Override
    public boolean isDefault()
    {
        return isDefault;
    }

    @Override
    public boolean isHidden()
    {
        return isHidden;
    }

    @Override
    public Set<Node> permissions()
    {
        return permissions;
    }

    @Override
    public boolean addPermission(final Node node)
    {
        return permissions().add(node);
    }

    @Override
    public boolean removePermission(final Node node)
    {
        return permissions().remove(node);
    }

    @Override
    public boolean isPermissionSet(@NotNull final String name)
    {
        final Node node = permissions().stream()
                                       .filter(n -> n.key()
                                                     .equalsIgnoreCase(name))
                                       .findFirst()
                                       .orElse(null);

        return node != null;
    }

    @Override
    public boolean isPermissionSet(@NotNull final Permission perm)
    {
        final Node node = permissions()
                .stream()
                .filter(n -> n.bukkit()
                              .equals(perm))
                .findFirst()
                .orElse(null);

        return node != null;
    }

    @Override
    public boolean hasPermission(@NotNull final String name)
    {
        final Node node = permissions().stream()
                                       .filter(n -> n.key()
                                                     .equalsIgnoreCase(name))
                                       .findFirst()
                                       .orElse(null);

        return node != null;
    }

    @Override
    public boolean hasPermission(@NotNull final Permission perm)
    {
        final Node node = permissions()
                .stream()
                .filter(n -> n.bukkit()
                              .equals(perm))
                .findFirst()
                .orElse(null);

        return node != null;
    }

    /**
     * Adds a permission to the relative PermissionAttachment for this group. This method is not thread-safe and should
     * not be called asynchronously.
     * <p>
     * This method is only here for compatibility with the Bukkit API.
     *
     * @param plugin The plugin responsible for this attachment. May not be null or disabled.
     * @param name   Name of the permission to attach
     * @param value  Value of the permission
     * @return This group's PermissionAttachment.
     */
    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
                                                       final boolean value)
    {
        attachment.setPermission(name, value);
        return attachment;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull final Plugin plugin)
    {
        return new PermissionAttachment(plugin, this);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
                                                        final boolean value, final int ticks)
    {
        attachment.setPermission(name, value);
        return attachment;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull final Plugin plugin, final int ticks)
    {
        return new PermissionAttachment(plugin, this);
    }

    @Override
    public void removeAttachment(@NotNull final PermissionAttachment attachment)
    {
        // This method shouldn't do anything, because we don't want to remove our attachment.
    }

    @Override
    public void recalculatePermissions()
    {
        // Not sure what this method should do, so I'm leaving it empty.
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions()
    {
        return permissions()
                .stream()
                .map(n -> new PermissionAttachmentInfo(
                        this,
                        n.key(),
                        attachment, true))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isOp()
    {
        final Node node = permissions()
                .stream()
                .filter(n -> n.equals(DefaultNodes.OP))
                .findFirst()
                .orElse(null);

        return node != null;
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

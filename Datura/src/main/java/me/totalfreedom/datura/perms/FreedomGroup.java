package me.totalfreedom.datura.perms;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.security.Group;
import me.totalfreedom.security.Node;
import net.kyori.adventure.text.Component;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public FreedomGroup(Component name,
                        Component prefix,
                        Component abbreviation,
                        int weight,
                        boolean isDefault,
                        boolean isHidden)
    {
        this.name = name;
        this.prefix = prefix;
        this.abbreviation = abbreviation;
        this.weight = weight;
        this.isDefault = isDefault;
        this.isHidden = isHidden;
        this.permissions = new HashSet<>();
        this.attachment = new PermissionAttachment(CommonsBase.getInstance(), this);
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
    public UUID getUniqueId()
    {
        return UUID.nameUUIDFromBytes(getName().toString().getBytes());
    }

    @Override
    public Set<Node> permissions()
    {
        return permissions;
    }

    @Override
    public boolean addPermission(Node node)
    {
        return permissions().add(node);
    }

    @Override
    public boolean removePermission(Node node)
    {
        return permissions().remove(node);
    }

    @Override
    public boolean isPermissionSet(@NotNull String name)
    {
        Node node = permissions().stream()
                .filter(n -> n.key().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        return node != null && node.value();
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm)
    {
        Node node = permissions()
                .stream()
                .filter(n -> n.bukkit().equals(perm))
                .findFirst()
                .orElse(null);

        return node != null && node.value();
    }

    @Override
    public boolean hasPermission(@NotNull String name)
    {
        Node node = permissions().stream()
                .filter(n -> n.key().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        return node != null && node.value();
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm)
    {
        Node node = permissions()
                .stream()
                .filter(n -> n.bukkit().equals(perm))
                .findFirst()
                .orElse(null);

        return node != null && node.value();
    }

    /**
     * Adds a permission to the relative PermissionAttachment for this group.
     * This method is not thread-safe and should not be called asynchronously.
     * <p>
     * This method is only here for compatibility with the Bukkit API.
     *
     * @param plugin The plugin responsible for this attachment. May not be null
     *               or disabled.
     * @param name   Name of the permission to attach
     * @param value  Value of the permission
     * @return This group's PermissionAttachment.
     */
    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value)
    {
        attachment.setPermission(name, value);
        return attachment;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin)
    {
        return new PermissionAttachment(plugin, this);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks)
    {
        attachment.setPermission(name, value);
        return attachment;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks)
    {
        return new PermissionAttachment(plugin, this);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment)
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
                        attachment,
                        n.value()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isOp()
    {
        Node node = permissions()
                .stream()
                .filter(n -> n.equals(DefaultNodes.OP))
                .findFirst()
                .orElse(null);

        return node != null && node.value();
    }

    @Override
    public void setOp(boolean value)
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

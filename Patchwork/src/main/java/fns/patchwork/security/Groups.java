package fns.patchwork.security;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Groups
{
    NON_OP("patchwork.group.non-op", "Non-OP"),
    OP("patchwork.group.op", "OP"),
    SUPER_ADMIN("patchwork.group.super", "Super Admin"),
    SENIOR_ADMIN("patchwork.group.senior", "Senior Admin"),
    DEVELOPER("patchwork.group.dev", "Developer"),
    EXECUTIVE("patchwork.group.exec", "Executive"),
    OWNER("patchwork.group.owner", "Owner");

    private final String permission;
    private final String name;

    Groups(final String permission, final String name)
    {
        this.permission = permission;
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public String toString()
    {
        return this.permission;
    }

    public static String fromPlayer(final Player player) {
        for (final Groups group : values()) {
            if (player.hasPermission(group.getPermission())) {
                return group.getName();
            }
        }
        return Groups.NON_OP.getName();
    }

    public static String fromSender(final CommandSender sender) {
        if (!(sender instanceof Player)) return "CONSOLE";

        return fromPlayer((Player) sender);
    }
}

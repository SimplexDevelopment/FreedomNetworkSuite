/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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

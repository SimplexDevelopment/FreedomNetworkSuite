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

import fns.patchwork.permissible.Node;
import fns.patchwork.permissible.NodeType;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

record PermissionNode(String key, long expiry, NodeType type, boolean wildcard) implements Node
{

    @Override
    public Permission bukkit()
    {
        return new Permission(key(), PermissionDefault.FALSE);
    }

    @Override
    public boolean compare(final Node node)
    {
        return node.key().equalsIgnoreCase(key()) && node.type().equals(type()) && !node.isExpired();
    }

    @Override
    public boolean isExpired()
    {
        if (!isTemporary())
        {
            return false;
        }

        return System.currentTimeMillis() > expiry();
    }

    @Override
    public boolean isTemporary()
    {
        return expiry() > -1;
    }
}

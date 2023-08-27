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

package fns.datura.perms;

import fns.patchwork.permissible.Node;
import fns.patchwork.permissible.NodeType;

public class DefaultNodes
{
    public static final Node OP = new PermissionNodeBuilder()
            .key("freedom.master_key")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(true)
            .build();
    public static final Node NON_OP = new PermissionNodeBuilder()
            .key("freedom.default")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(false)
            .build();
    public static final Node ALL = new PermissionNodeBuilder()
            .key("*")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(true)
            .build();
    public static final Node NONE = new PermissionNodeBuilder()
            .key("freedom.none")
            .expiry(-1)
            .type(NodeType.PERMISSION)
            .wildcard(false)
            .build();

    private DefaultNodes()
    {
        throw new AssertionError();
    }
}

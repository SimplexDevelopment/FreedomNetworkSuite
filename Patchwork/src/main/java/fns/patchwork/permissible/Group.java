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

package fns.patchwork.permissible;

import net.kyori.adventure.text.Component;

/**
 * Represents a permissible group which holds a set of permissions that can then be applied to a User / Player.
 */
public interface Group extends PermissionHolder
{
    /**
     * @return The name of the group.
     */
    Component getName();

    /**
     * @return The prefix of the group.
     */
    Component getPrefix();

    /**
     * @return The abbreviation of the group.
     */
    Component getAbbreviation();

    /**
     * @return The weight of the group.
     */
    int getWeight();

    /**
     * If more than one group is marked as default, the first retrieved default group will be used.
     *
     * @return Whether this is the default group.
     */
    boolean isDefault();

    /**
     * @return Whether the group is hidden.
     */
    boolean isHidden();
}

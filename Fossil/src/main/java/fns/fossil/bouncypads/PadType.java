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

package fns.fossil.bouncypads;

import org.bukkit.block.BlockFace;

/**
 * Represents a specific type of bouncy pad.
 */
public enum PadType
{
    /**
     * A normal pad, which will only bounce the player if the face is {@link BlockFace#UP}.
     */
    NORMAL,
    /**
     * A pad which will bounce the player on {@link BlockFace#NORTH}, {@link BlockFace#SOUTH}, {@link BlockFace#EAST} or
     * {@link BlockFace#WEST}.
     */
    SIDES,
    /**
     * A pad which will bounce the player if the face is {@link BlockFace#UP}, {@link BlockFace#NORTH},
     * {@link BlockFace#EAST}, {@link BlockFace#SOUTH} or {@link BlockFace#WEST}.
     */
    ALL,
    /**
     * A pad which will bounce the player with an extreme velocity
     */
    EXTREME,
    /**
     * A pad which will send the player to space.
     */
    SPACE_CADET;
}

package me.totalfreedom.fossil.bouncypads;

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
     * A pad which will bounce the player on {@link BlockFace#NORTH}, {@link BlockFace#SOUTH}, {@link BlockFace#EAST}
     * or {@link BlockFace#WEST}.
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

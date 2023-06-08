package me.totalfreedom.fossil.bouncypads;

import com.google.errorprone.annotations.Immutable;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.SplittableRandom;

/**
 * Represents a bouncy pad. Has a velocity and a type.
 */
@Immutable
public class BouncyPad
{
    /**
     * The velocity of the pad.
     */
    private final double velocity;
    /**
     * The type of the pad.
     */
    private final PadType padType;

    /**
     * Creates a new bouncy pad.
     *
     * @param velocity The velocity of the pad.
     * @param padType  The type of the pad.
     */
    public BouncyPad(final double velocity, final PadType padType)
    {
        this.velocity = velocity;
        this.padType = padType;
    }

    /**
     * Creates a new bouncy pad with a type of {@link PadType#NORMAL}.
     *
     * @param velocity The velocity of the pad.
     */
    public BouncyPad(final double velocity)
    {
        this(velocity, PadType.NORMAL);
    }

    /**
     * Creates a new bouncy pad with a velocity of 1.1 and a type of {@link PadType#NORMAL}.
     */
    public BouncyPad()
    {
        this(1.0 + 0.1F);
    }

    /**
     * This method will bounce the player based on the type of the pad.
     * <p>
     * The type of the pad, defined by {@link #padType}, will determine how the player is bounced.
     * <br>
     * For type {@link PadType#NORMAL}, the player will be bounced if the face is {@link BlockFace#UP}.
     * <br>
     * For type {@link PadType#SIDES}, the player will be bounced if the face is not {@link BlockFace#UP} or
     * {@link BlockFace#DOWN}.
     * <br>
     * For type {@link PadType#ALL}, the player will be bounced regardless of the face.
     * <br>
     * For type {@link PadType#EXTREME}, the player will be bounced with a velocity based on the formula:
     * <br>
     * <span color=#f07a21><code>(((173.31 + 0.5 * velocity) - (31.2 + 0.5 * Math.pow(velocity, 2.0)) + (0.5 *
     * Math.pow(velocity, 3.0))) - 173.31) / (velocity * (velocity - 1))</code></span>
     * <br>
     * For type {@link PadType#SPACE_CADET}, the player will be bounced with a velocity based on the formula:
     * <br>
     * <span color=#f07a21><code>Math.round(Math.abs((accel * 100.0) + Math.pow(y, Math.floor(accel)) /
     * Math.exp(accel)))</code></span>
     * <br>
     * where <span color=#f07a21><code>y = Math.abs(random.nextGaussian(12, 5) * 0.5 + 0.5)</code></span> and <span
     * color=#f07a21><code>accel = Math.sqrt(2 * 9.81 * y)</code></span>
     * <br>
     * <br>
     * <b>NOTE:</b> The velocity of the pad is added with the inverse velocity of the player. The inverse
     * velocity of the player is acquired by multiplying the velocity of the player by -1.
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    public void bouncePad(final Player player, final BlockFace face)
    {
        switch (padType)
        {
            case NORMAL -> bounceNormal(player, face);
            case SIDES -> bounceSides(player, face);
            case ALL -> bounceAll(player, face);
            case EXTREME -> bounceExtreme(player, face);
            case SPACE_CADET -> bounceSpaceCadet(player, face);
        }
    }

    /**
     * This method returns a vector based on the following:
     * <br>
     * <span color=#f07a21><code>(BlockFace direction + Player velocity * -1) * velocity</code></span>
     * <br>
     * <br>
     * We retrieve a vector representing the direction in which this block face is facing. This is then added with the
     * inverse velocity of the player, which is the direction and speed in which the player is moving multiplied by -1.
     * This is then multiplied by the velocity of the pad.
     *
     * @param player The moving player
     * @param face   The face of the block the player is bouncing on.
     * @return A vector representing the direction and speed in which the player should be bounced.
     */
    private Vector getVector(final Player player, final BlockFace face)
    {
        return face.getDirection()
                   .add(player.getVelocity()
                              .multiply(-1))
                   .multiply(velocity);
    }

    /**
     * This method will bounce the player if the face is {@link BlockFace#UP}.
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    private void bounceNormal(final Player player, final BlockFace face)
    {
        if (!face.equals(BlockFace.UP))
            return;

        player.setVelocity(getVector(player, face));
    }

    /**
     * This method will bounce the player if the face is not {@link BlockFace#UP} or {@link BlockFace#DOWN}.
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    private void bounceSides(final Player player, final BlockFace face)
    {
        if (face == BlockFace.UP || face == BlockFace.DOWN)
            return;

        player.setVelocity(getVector(player, face));
    }

    /**
     * This method will bounce the player regardless of the face.
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    private void bounceAll(final Player player, final BlockFace face)
    {
        player.setVelocity(getVector(player, face));
    }

    /**
     * This method will bounce the player with a velocity based on the formula:
     * <br>
     * <span color=#f07a21><code>(((173.31 + 0.5 * velocity) - (31.2 + 0.5 * Math.pow(velocity, 2.0)) + (0.5 *
     * Math.pow(velocity, 3.0))) - 173.31) / (velocity * (velocity - 1))</code></span>
     * <br>
     * <br>
     * <b>NOTE:</b> The velocity of the pad is added with the inverse velocity of the player. The inverse
     * velocity of the player is acquired by multiplying the velocity of the player by -1.
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    private void bounceExtreme(final Player player, final BlockFace face)
    {
        final double extremeVelocity = (((173.31 + 0.5 * velocity) - (31.2 + 0.5 * Math.pow(velocity, 2.0)) + (0.5 * Math.pow(velocity, 3.0))) - 173.31) / (velocity * (velocity - 1));
        player.setVelocity(face.getDirection()
                               .add(player.getVelocity()
                                          .multiply(-1))
                               .multiply(extremeVelocity * velocity));
    }

    /**
     * This method will bounce the player with a velocity based on the formula:
     * <br>
     * <span color=#f07a21><code>Math.round(Math.abs((accel * 100.0) + Math.pow(y, Math.floor(accel)) /
     * Math.exp(accel)))</code></span>
     * <br>
     * where <span color=#f07a21><code>y = Math.abs(random.nextGaussian(12, 5) * 0.5 + 0.5)</code></span> and
     * <span color=#f07a21><code>accel = Math.sqrt(2 * 9.81 * y)</code></span>
     *
     * @param player The player to bounce.
     * @param face   The face of the block the player is bouncing on.
     */
    private void bounceSpaceCadet(final Player player, final BlockFace face)
    {
        final SplittableRandom random = new SplittableRandom();
        final double y = Math.abs(random.nextGaussian(12, 5) * 0.5 + 0.5);
        final double accel = Math.sqrt(2 * 9.81 * y);
        final double spaceVelocity = Math.round(Math.abs((accel * 100.0) + Math.pow(y, Math.floor(accel)) / Math.exp(accel)));

        final Vector accelVector = new Vector(0, y + accel, 0);
        final Vector postVector = new Vector(0, spaceVelocity, 0);

        final Vector spaceVector = face.getDirection()
                                       .add(player.getVelocity()
                                                  .multiply(-1))
                                       .multiply(accelVector.multiply(postVector));

        player.setVelocity(spaceVector);
    }
}

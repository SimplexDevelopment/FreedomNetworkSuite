package fns.patchwork.particle;

import org.bukkit.Particle;
import org.bukkit.World;

/**
 * A utility class for the 24 different note colors available in Minecraft. Each note is represented as a double value
 * between 0 and 1. Furthermore, each note is a multiple of 1/24 within that range of 0 to 1.
 * <p>
 * For example, the note G is represented as 1/24, or 0.042. The note C is represented as 6/24, or 0.25.
 * <p>
 * When spawning particles, the count must be set to 0 and the extra value set between 0 and 1. The extra value is the
 * size of the note particle. To add a color, use one of the provided methods in this class for the xOffset value in
 * {@link World#spawnParticle(Particle, double, double, double, int, double, double, double, double)}. The xOffset value
 * is the note color, with the yOffset and zOffset values set to 0.
 */
public final class NoteColorUtil
{
    public static final double CYAN_NOTE_F_SHARP_LOW = 0;
    public static final double CYAN_NOTE_G = 0.042;
    public static final double GRAY_NOTE_G_SHARP = 0.083;
    public static final double GRAY_NOTE_A = 0.125;
    public static final double GRAY_NOTE_A_SHARP = 0.167;
    public static final double MAGENTA_NOTE_B = 0.208;
    public static final double RED_NOTE_C = 0.25;
    public static final double YELLOW_NOTE_C_SHARP = 0.292;
    public static final double YELLOW_NOTE_D = 0.333;
    public static final double YELLOW_NOTE_D_SHARP_LOW = 0.375;
    public static final double GRAY_NOTE_E = 0.417;
    public static final double GRAY_NOTE_F = 0.458;
    public static final double GRAY_NOTE_F_SHARP = 0.5;
    public static final double LIGHT_BLUE_NOTE_G = 0.542;
    public static final double BLUE_NOTE_G_SHARP = 0.583;
    public static final double PURPLE_NOTE_A = 0.625;
    public static final double PURPLE_NOTE_A_SHARP = 0.667;
    public static final double PURPLE_NOTE_B = 0.708;
    public static final double GRAY_NOTE_C = 0.75;
    public static final double GRAY_NOTE_C_SHARP = 0.792;
    public static final double GRAY_NOTE_D = 0.833;
    public static final double YELLOW_NOTE_D_SHARP_HIGH = 0.875;
    public static final double YELLOW_NOTE_E = 0.917;
    public static final double YELLOW_NOTE_F = 0.958;
    public static final double CYAN_NOTE_F_SHARP_HIGH = 1;
    public static final double BLACK_NOTE_NA = 32768;

    private NoteColorUtil()
    {
        throw new AssertionError();
    }
}

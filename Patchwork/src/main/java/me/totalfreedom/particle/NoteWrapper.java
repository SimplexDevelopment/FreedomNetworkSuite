package me.totalfreedom.particle;

import org.bukkit.Particle;
import org.bukkit.World;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
public final class NoteWrapper
{
    public static final double CYAN_NOTE_F_SHARP_LOW = 0;
    public static final double CYAN_NOTE_F_SHARP_HIGH = 1;

    private static final DecimalFormat FORMAT;

    static
    {
        FORMAT = new DecimalFormat("#.###");
        FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    }

    private NoteWrapper()
    {
        throw new AssertionError();
    }

    private static double round(final double value)
    {
        return Double.parseDouble(FORMAT.format(value));
    }

    public static double cyanNoteG()
    {
        return round(1 / 24D);
    }

    public static double grayNoteGSharp()
    {
        return round(2 / 24D);
    }

    public static double grayNoteA()
    {
        return round(3 / 24D);
    }

    public static double grayNoteASharp()
    {
        return round(4 / 24D);
    }

    public static double magentaNoteB()
    {
        return round(5 / 24D);
    }

    public static double redNoteC()
    {
        return round(6 / 24D);
    }

    public static double yellowNoteCSharp()
    {
        return round(7 / 24D);
    }

    public static double yellowNoteD()
    {
        return round(8 / 24D);
    }

    public static double yellowNoteDSharpLow()
    {
        return round(9 / 24D);
    }

    public static double grayNoteE()
    {
        return round(10 / 24D);
    }

    public static double grayNoteF()
    {
        return round(11 / 24D);
    }

    public static double grayNoteFSharp()
    {
        return round(12 / 24D);
    }

    public static double lightBlueNoteG()
    {
        return round(13 / 24D);
    }

    public static double blueNoteGSharp()
    {
        return round(14 / 24D);
    }

    public static double purpleNoteA()
    {
        return round(15 / 24D);
    }

    public static double purpleNoteASharp()
    {
        return round(16 / 24D);
    }

    public static double purpleNoteB()
    {
        return round(17 / 24D);
    }

    public static double grayNoteC()
    {
        return round(18 / 24D);
    }

    public static double grayNoteCSharp()
    {
        return round(19 / 24D);
    }

    public static double grayNoteD()
    {
        return round(20 / 24D);
    }

    public static double yellowNoteDSharpHigh()
    {
        return round(21 / 24D);
    }

    public static double greenNoteE()
    {
        return round(22 / 24D);
    }

    public static double lightBlueNoteF()
    {
        return round(23 / 24D);
    }
}

package me.totalfreedom.utils;

import me.totalfreedom.api.Interpolator;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;

import java.util.LinkedHashSet;
import java.util.Set;

public class InterpolationUtils
{
    public static Set<Color> rainbow(final int length)
    {
        final LinkedHashSet<Color> base = new LinkedHashSet<>();
        final Set<Color> redToOrange = hsvGradient(length, Color.RED, Color.ORANGE, InterpolationUtils::linear);
        final Set<Color> orangeToYellow = hsvGradient(length, Color.ORANGE, Color.YELLOW, InterpolationUtils::linear);
        final Set<Color> yellowToGreen = hsvGradient(length, Color.YELLOW, Color.GREEN, InterpolationUtils::linear);
        final Set<Color> greenToBlue = hsvGradient(length, Color.GREEN, Color.BLUE, InterpolationUtils::linear);
        final Set<Color> blueToPurple = hsvGradient(length, Color.BLUE, Color.PURPLE, InterpolationUtils::linear);
        final Set<Color> purpleToRed = hsvGradient(length, Color.PURPLE, Color.RED, InterpolationUtils::linear);
        base.addAll(redToOrange);
        base.addAll(orangeToYellow);
        base.addAll(yellowToGreen);
        base.addAll(greenToBlue);
        base.addAll(blueToPurple);
        base.addAll(purpleToRed);
        return base;
    }

    private static Set<Color> hsvGradient(final int length, final Color from, final Color to,
        final Interpolator interpolator)
    {
        // returns a float-array where hsv[0] = hue, hsv[1] = saturation, hsv[2] = value/brightness
        final float[] hsvFrom = java.awt.Color.RGBtoHSB(from.getRed(), from.getGreen(), from.getBlue(), null);
        final float[] hsvTo = java.awt.Color.RGBtoHSB(to.getRed(), to.getGreen(), to.getBlue(), null);

        final double[] h = interpolator.interpolate(hsvFrom[0], hsvTo[0], length);
        final double[] s = interpolator.interpolate(hsvFrom[1], hsvTo[1], length);
        final double[] v = interpolator.interpolate(hsvFrom[2], hsvTo[2], length);

        final LinkedHashSet<Color> gradient = new LinkedHashSet<>();

        for (int i = 0; i < length; i++)
        {
            final int rgb = java.awt.Color.HSBtoRGB((float) h[i], (float) s[i], (float) v[i]);
            final Color color = Color.fromRGB(rgb);
            gradient.add(color);
        }
        return gradient;
    }

    private static double[] linear(final double from, final double to, final int max)
    {
        final double[] res = new double[max];
        for (int i = 0; i < max; i++)
        {
            res[i] = from + i * ((to - from) / (max - 1));
        }
        return res;
    }

    public static Set<Color> rgbGradient(final int length, final Color from, final Color to,
        final Interpolator interpolator)
    {
        final double[] r = interpolator.interpolate(from.getRed(), to.getRed(), length);
        final double[] g = interpolator.interpolate(from.getGreen(), to.getGreen(), length);
        final double[] b = interpolator.interpolate(from.getBlue(), to.getBlue(), length);

        final LinkedHashSet<Color> gradient = new LinkedHashSet<>();

        for (int i = 0; i < length; i++)
        {
            final Color color = Color.fromRGB((int) r[i], (int) g[i], (int) b[i]);
            gradient.add(color);
        }
        return gradient;
    }

    public static Set<TextColor> rainbowComponent(final int length)
    {
        final LinkedHashSet<TextColor> base = new LinkedHashSet<>();
        final Set<TextColor> redToOrange = componentRGBGradient(length, NamedTextColor.RED,
                                                                NamedTextColor.GOLD, InterpolationUtils::linear);
        final Set<TextColor> orangeToYellow = componentRGBGradient(length, NamedTextColor.GOLD,
                                                                   NamedTextColor.YELLOW, InterpolationUtils::linear);
        final Set<TextColor> yellowToGreen = componentRGBGradient(length, NamedTextColor.YELLOW,
                                                                  NamedTextColor.GREEN, InterpolationUtils::linear);
        final Set<TextColor> greenToBlue = componentRGBGradient(length, NamedTextColor.GREEN,
                                                                NamedTextColor.BLUE, InterpolationUtils::linear);
        final Set<TextColor> blueToPurple = componentRGBGradient(length, NamedTextColor.BLUE,
                                                                 NamedTextColor.LIGHT_PURPLE, InterpolationUtils::linear);
        final Set<TextColor> purpleToRed = componentRGBGradient(length, TextColor.color(75, 0, 130),
                                                                TextColor.color(255, 0, 0), InterpolationUtils::linear);
        base.addAll(redToOrange);
        base.addAll(orangeToYellow);
        base.addAll(yellowToGreen);
        base.addAll(greenToBlue);
        base.addAll(blueToPurple);
        base.addAll(purpleToRed);
        return base;
    }

    public static Set<TextColor> componentRGBGradient(final int length, final TextColor from, final TextColor to,
        final Interpolator interpolator)
    {
        final double[] r = interpolator.interpolate(from.red(), to.red(), length);
        final double[] g = interpolator.interpolate(from.green(), to.green(), length);
        final double[] b = interpolator.interpolate(from.blue(), to.blue(), length);

        final LinkedHashSet<TextColor> gradient = new LinkedHashSet<>();

        for (int i = 0; i < length; i++)
        {
            final TextColor color = TextColor.color((int) r[i], (int) g[i], (int) b[i]);
            gradient.add(color);
        }
        return gradient;
    }
}

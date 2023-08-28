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

package fns.patchwork.utils;

import java.util.LinkedHashSet;
import java.util.Set;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;

public final class InterpolationUtils
{
    private InterpolationUtils()
    {
        throw new AssertionError();
    }

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
                NamedTextColor.LIGHT_PURPLE,
                InterpolationUtils::linear);
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

    private static Set<TextColor> componentRGBGradient(final int length, final TextColor from, final TextColor to,
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

    public static Set<Color> standardGradient(final int length, final Color from, final Color to)
    {
        return rgbGradient(length, from, to, InterpolationUtils::linear);
    }

    private static Set<Color> rgbGradient(final int length, final Color from, final Color to,
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

    public static Set<TextColor> standardComponentGradient(final int length, final TextColor from, final TextColor to)
    {
        return componentRGBGradient(length, from, to, InterpolationUtils::linear);
    }

    /**
     * Interpolates a range of values and returns the results in a {@link Double} array.
     * <br>
     * This is a functional interface, to allow for lambda expressions, but also for anonymous custom interpolation
     * implementations.
     */
    @FunctionalInterface
    public static interface Interpolator
    {
        /**
         * Interpolates a range of values and returns the results in a {@link Double} array.
         *
         * @param from The starting value.
         * @param to   The ending value.
         * @param max  The number of values to interpolate.
         * @return The interpolated values.
         */
        double[] interpolate(final double from, final double to, final int max);
    }
}

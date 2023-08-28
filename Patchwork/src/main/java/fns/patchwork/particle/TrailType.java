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

package fns.patchwork.particle;

import org.bukkit.Particle;

public enum TrailType
{
    /**
     * Default trail type. Uses {@link Particle#REDSTONE}. This trail is colorable. Use {@link Particle.DustOptions} to
     * set the particle properties.
     */
    DEFAULT(Particle.REDSTONE),
    /**
     * A trail that uses {@link Particle#HEART}. This is not modifiable and will always have the same size shape and
     * color.
     */
    HEART(Particle.HEART),
    /**
     * A trail that uses {@link Particle#FLAME}. This is not modifiable and will always have the same size shape and
     * color.
     */
    FLAME(Particle.FLAME),
    /**
     * A trail that uses {@link Particle#REDSTONE}. This particle however is rainbow-colored by default and cannot have
     * additional options set.
     */
    RAINBOW(Particle.REDSTONE),
    /**
     * A trail that uses {@link Particle#NOTE}. This is colorable, however you are limited to the 24 different note
     * colors available in Minecraft.
     */
    MUSIC(Particle.NOTE),
    SNOW(Particle.SNOWBALL),
    SPELL(Particle.SPELL_MOB),
    SPELL_AMBIENT(Particle.SPELL_MOB_AMBIENT),
    PORTAL(Particle.PORTAL),
    ENCHANTMENT(Particle.ENCHANTMENT_TABLE),
    STROBE(Particle.DUST_COLOR_TRANSITION),
    VIBRATION(Particle.VIBRATION),
    SPARK(Particle.ELECTRIC_SPARK);

    final Particle type;

    TrailType(final Particle type)
    {
        this.type = type;
    }

    public Particle getType()
    {
        return type;
    }
}

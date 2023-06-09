package me.totalfreedom.particle;

import org.bukkit.Note;
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

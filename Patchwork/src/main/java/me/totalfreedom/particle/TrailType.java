package me.totalfreedom.particle;

import org.bukkit.Particle;

public enum TrailType
{
    DEFAULT(Particle.REDSTONE),
    HEART(Particle.HEART),
    FLAME(Particle.FLAME),
    RAINBOW(Particle.REDSTONE),
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

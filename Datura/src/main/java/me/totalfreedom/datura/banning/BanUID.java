package me.totalfreedom.datura.banning;

import me.totalfreedom.security.ban.BanID;

import java.time.Instant;
import java.time.temporal.ChronoField;

public final class BanUID implements BanID
{
    private final char prefix;
    private final int numericalTag;

    private BanUID(final boolean permanent)
    {
        if (permanent)
        {
            prefix = 'P';
        } else
        {
            prefix = 'T';
        }

        final Instant instant = Instant.now();

        String stringBuilder = String.valueOf(instant.get(ChronoField.DAY_OF_YEAR)) + // The first three numbers between 001 -> 365
                instant.get(ChronoField.HOUR_OF_DAY) + // next two numbers between 00 -> 23
                instant.get(ChronoField.MINUTE_OF_HOUR) + // next two numbers between 00 -> 59
                instant.get(ChronoField.MILLI_OF_SECOND); // last three numbers between 000 -> 999

        numericalTag = Integer.parseInt(stringBuilder);
    }

    public static BanUID createTempID()
    {
        return new BanUID(false);
    }

    public static BanUID createPermID()
    {
        return new BanUID(true);
    }

    @Override
    public String getID()
    {
        return getIDPrefix() + "-" + getNumericalTag();
    }

    @Override
    public char getIDPrefix()
    {
        return prefix;
    }

    @Override
    public int getNumericalTag()
    {
        return numericalTag;
    }

    @Override
    public boolean isPermanent()
    {
        return getIDPrefix() == 'P';
    }
}

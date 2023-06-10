package me.totalfreedom.logging;

import com.google.errorprone.annotations.Immutable;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

@Immutable
public interface Interaction<T>
{
    @NotNull
    static String format(@NotNull final Interaction<?> interaction)
    {
        return new InteractionFormatter().formatInteraction(interaction);
    }

    @NotNull
    UUID getWhoClicked();

    @NotNull
    T getOriginalState();

    @NotNull
    T getNewState();

    @NotNull
    Instant getWhen();

    @NotNull
    Location getLocation();
}

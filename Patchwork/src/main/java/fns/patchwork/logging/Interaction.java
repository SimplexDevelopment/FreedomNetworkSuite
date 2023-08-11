package fns.patchwork.logging;

import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import java.util.UUID;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

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

package me.totalfreedom.audience;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A replacement for {@link net.kyori.adventure.audience.ForwardingAudience} that allows for audiences to be removed &
 * added at will. Not thread safe.
 * <p>
 * This is intended for use in toggleable logging systems, for example, potion spy.
 */
// TODO: Work on thread-safety (or thread-safe alternative)
public class MutableAudienceForwarder implements Audience
{
    /**
     * The audiences that this forwards to.
     */
    private final Set<Audience> audiences = new HashSet<>();

    /**
     * Creates a new {@link MutableAudienceForwarder} with the given audiences.
     *
     * @param audiences The audiences to forward to.
     * @return The new {@link MutableAudienceForwarder}.
     */
    public static MutableAudienceForwarder from(final Audience... audiences)
    {
        final MutableAudienceForwarder audienceForwarder = new MutableAudienceForwarder();

        for (final Audience audience : audiences)
        {
            audienceForwarder.addAudience(audience);
        }

        return audienceForwarder;
    }

    /**
     * Adds an audience to this forwarder.
     *
     * @param audience The audience to add.
     */
    public void addAudience(final Audience audience)
    {
        if (audiences.contains(audience) || audience == this /* Protect against honest self-referential calls */)
        {
            return;
        }

        audiences.add(audience);
    }

    /**
     * Removes an audience from this forwarder.
     *
     * @param audience The audience to remove.
     * @return Whether the audience was removed.
     */
    public boolean removeAudience(final Audience audience)
    {
        return audiences.remove(audience);
    }

    /**
     * Filters the audiences in the stream by the given predicate.
     *
     * @param filter a filter that determines if an audience should be included
     * @return The first Audience found that matches the filter.
     */
    @Override
    public @NotNull Audience filterAudience(@NotNull final Predicate<? super Audience> filter)
    {
        return audiences.stream()
                        .filter(filter)
                        .findFirst()
                        .orElseThrow();
    }

    /**
     * Applies a consumer to each audience in the stream.
     *
     * @param action the action to apply.
     */
    @Override
    public void forEachAudience(@NotNull final Consumer<? super Audience> action)
    {
        audiences.forEach(action);
    }

    /**
     * Sends a {@link ComponentLike} to every audience within the stream.
     *
     * @param message The message to send.
     * @see Audience#sendMessage(ComponentLike)
     * @see #forEachAudience(Consumer)
     */
    @Override
    public void sendMessage(@NotNull final ComponentLike message)
    {
        forEachAudience(a -> a.sendMessage(message));
    }

    /**
     * Sends a {@link Component} to every audience within the stream.
     *
     * @param message The message to send
     * @see Audience#sendMessage(Component)
     * @see #forEachAudience(Consumer)
     */
    @Override
    public void sendMessage(@NotNull final Component message)
    {
        forEachAudience(a -> a.sendMessage(message));
    }

    /**
     * Sends a {@link SignedMessage} to every audience within the stream.
     *
     * @param message       the component content of the message
     * @param boundChatType the bound chat type of the message
     * @see Audience#sendMessage(Component, ChatType.Bound)
     * @see #forEachAudience(Consumer)
     */
    @Override
    public void sendMessage(@NotNull final Component message, final ChatType.@NotNull Bound boundChatType)
    {
        forEachAudience(a -> a.sendMessage(message, boundChatType));
    }

    /**
     * Sends a {@link SignedMessage} to every audience within the stream.
     *
     * @param message       the component content of the message
     * @param boundChatType the bound chat type of the message
     * @see Audience#sendMessage(ComponentLike, ChatType.Bound)
     * @see #forEachAudience(Consumer)
     */
    @Override
    public void sendMessage(@NotNull final ComponentLike message, final ChatType.@NotNull Bound boundChatType)
    {
        forEachAudience(a -> a.sendMessage(message, boundChatType));
    }

    /**
     * Sends a {@link SignedMessage} to every audience within the stream.
     *
     * @param signedMessage the signed message data to send
     * @param boundChatType the bound chat type of the message
     */
    @Override
    public void sendMessage(@NotNull final SignedMessage signedMessage, final ChatType.@NotNull Bound boundChatType)
    {
        forEachAudience(a -> a.sendMessage(signedMessage, boundChatType));
    }

    /**
     * Deletes a signed message from the audiences chat.
     *
     * @param signedMessage the message to delete
     */
    @Override
    public void deleteMessage(@NotNull final SignedMessage signedMessage)
    {
        forEachAudience(a -> a.deleteMessage(signedMessage));
    }

    /**
     * Deletes a signed message from the audiences chat using the provided chat signature.
     *
     * @param signature the signature associated with the message to delete.
     */
    @Override
    public void deleteMessage(final SignedMessage.@NotNull Signature signature)
    {
        forEachAudience(a -> a.deleteMessage(signature));
    }

    // The methods below here will (probably) never be used, however it's good to keep them for completeness' sake.

    @Override
    public void sendActionBar(@NotNull final ComponentLike message)
    {
        forEachAudience(a -> a.sendActionBar(message));
    }

    @Override
    public void sendActionBar(@NotNull final Component message)
    {
        forEachAudience(a -> a.sendActionBar(message));
    }

    @Override
    public void sendPlayerListHeader(@NotNull final ComponentLike header)
    {
        forEachAudience(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListHeader(@NotNull final Component header)
    {
        forEachAudience(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListFooter(@NotNull final ComponentLike footer)
    {
        forEachAudience(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListFooter(@NotNull final Component footer)
    {
        forEachAudience(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull final ComponentLike header, @NotNull final ComponentLike footer)
    {
        forEachAudience(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull final Component header, @NotNull final Component footer)
    {
        forEachAudience(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void showTitle(@NotNull final Title title)
    {
        forEachAudience(a -> a.showTitle(title));
    }

    @Override
    public <T> void sendTitlePart(@NotNull final TitlePart<T> part, @NotNull final T value)
    {
        forEachAudience(a -> a.sendTitlePart(part, value));
    }

    @Override
    public void clearTitle()
    {
        forEachAudience(Audience::clearTitle);
    }

    @Override
    public void resetTitle()
    {
        forEachAudience(Audience::resetTitle);
    }

    @Override
    public void showBossBar(@NotNull final BossBar bar)
    {
        forEachAudience(a -> a.showBossBar(bar));
    }

    @Override
    public void hideBossBar(@NotNull final BossBar bar)
    {
        forEachAudience(a -> a.hideBossBar(bar));
    }

    @Override
    public void playSound(@NotNull final Sound sound)
    {
        forEachAudience(a -> a.playSound(sound));
    }

    @Override
    public void playSound(@NotNull final Sound sound, final double x, final double y, final double z)
    {

        forEachAudience(a -> a.playSound(sound, x, y, z));
    }

    @Override
    public void playSound(@NotNull final Sound sound, final Sound.@NotNull Emitter emitter)
    {
        forEachAudience(a -> a.playSound(sound, emitter));
    }

    @Override
    public void stopSound(@NotNull final Sound sound)
    {
        forEachAudience(a -> a.stopSound(sound));
    }

    @Override
    public void stopSound(@NotNull final SoundStop stop)
    {
        forEachAudience(a -> a.stopSound(stop));
    }

    @Override
    public void openBook(final Book.@NotNull Builder book)
    {
        forEachAudience(a -> a.openBook(book));
    }

    @Override
    public void openBook(@NotNull final Book book)
    {
        forEachAudience(a -> a.openBook(book));
    }
}

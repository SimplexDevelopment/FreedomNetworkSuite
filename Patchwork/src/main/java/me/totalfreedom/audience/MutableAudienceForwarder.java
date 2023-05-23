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
 * A replacement for {@link net.kyori.adventure.audience.ForwardingAudience} that allows for audiences to be removed & added at will. Not thread safe.
 * <p>
 * This is intended for use in toggleable logging systems, for example, potion spy.
 */
// TODO: Work on thread-safety (or thread-safe alternative)
public class MutableAudienceForwarder implements Audience
{
    private final Set<Audience> audiences = new HashSet<>();

    public static MutableAudienceForwarder from(final Audience... audiences)
    {
        final MutableAudienceForwarder audienceForwarder = new MutableAudienceForwarder();

        for (final Audience audience : audiences)
        {
            audienceForwarder.addAudience(audience);
        }

        return audienceForwarder;
    }

    public void addAudience(final Audience audience)
    {
        if (audiences.contains(audience) || audience == this /* Protect against honest self-referential calls */)
        {
            return;
        }

        audiences.add(audience);
    }

    public boolean removeAudience(final Audience audience)
    {
        return audiences.remove(audience);
    }


    @Override
    public @NotNull Audience filterAudience(@NotNull final Predicate<? super Audience> filter)
    {
        return audiences.stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void forEachAudience(@NotNull final Consumer<? super Audience> action)
    {
        audiences.forEach(action);
    }

    @Override
    public void sendMessage(@NotNull final ComponentLike message)
    {
        audiences.forEach(a -> a.sendMessage(message));
    }

    @Override
    public void sendMessage(@NotNull final Component message)
    {
        audiences.forEach(a -> a.sendMessage(message));
    }

    @Override
    public void sendMessage(@NotNull final Component message, final ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(message, boundChatType));
    }

    @Override
    public void sendMessage(@NotNull final ComponentLike message, final ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(message, boundChatType));
    }

    @Override
    public void sendMessage(@NotNull final SignedMessage signedMessage, final ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(signedMessage, boundChatType));
    }

    @Override
    public void deleteMessage(@NotNull final SignedMessage signedMessage)
    {
        audiences.forEach(a -> a.deleteMessage(signedMessage));
    }

    @Override
    public void deleteMessage(final SignedMessage.@NotNull Signature signature)
    {
        audiences.forEach(a -> a.deleteMessage(signature));
    }

    // The methods below here will (probably) never be used, however it's good to keep them for completeness' sake.

    @Override
    public void sendActionBar(@NotNull final ComponentLike message)
    {
        audiences.forEach(a -> a.sendActionBar(message));
    }

    @Override
    public void sendActionBar(@NotNull final Component message)
    {
        audiences.forEach(a -> a.sendActionBar(message));
    }

    @Override
    public void sendPlayerListHeader(@NotNull final ComponentLike header)
    {
        audiences.forEach(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListHeader(@NotNull final Component header)
    {
        audiences.forEach(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListFooter(@NotNull final ComponentLike footer)
    {
        audiences.forEach(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListFooter(@NotNull final Component footer)
    {
        audiences.forEach(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull final ComponentLike header, @NotNull final ComponentLike footer)
    {
        audiences.forEach(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull final Component header, @NotNull final Component footer)
    {
        audiences.forEach(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void showTitle(@NotNull final Title title)
    {
        audiences.forEach(a -> a.showTitle(title));
    }

    @Override
    public <T> void sendTitlePart(@NotNull final TitlePart<T> part, @NotNull final T value)
    {
        audiences.forEach(a -> a.sendTitlePart(part, value));
    }

    @Override
    public void clearTitle()
    {
        audiences.forEach(Audience::clearTitle);
    }

    @Override
    public void resetTitle()
    {
        audiences.forEach(Audience::resetTitle);
    }

    @Override
    public void showBossBar(@NotNull final BossBar bar)
    {
        audiences.forEach(a -> a.showBossBar(bar));
    }

    @Override
    public void hideBossBar(@NotNull final BossBar bar)
    {
        audiences.forEach(a -> a.hideBossBar(bar));
    }

    @Override
    public void playSound(@NotNull final Sound sound)
    {
        audiences.forEach(a -> a.playSound(sound));
    }

    @Override
    public void playSound(@NotNull final Sound sound, final double x, final double y, final double z)
    {

        audiences.forEach(a -> a.playSound(sound, x, y, z));
    }

    @Override
    public void playSound(@NotNull final Sound sound, final Sound.@NotNull Emitter emitter)
    {
        audiences.forEach(a -> a.playSound(sound, emitter));
    }

    @Override
    public void stopSound(@NotNull final Sound sound)
    {
        audiences.forEach(a -> a.stopSound(sound));
    }

    @Override
    public void stopSound(@NotNull final SoundStop stop)
    {
        audiences.forEach(a -> a.stopSound(stop));
    }

    @Override
    public void openBook(final Book.@NotNull Builder book)
    {
        audiences.forEach(a -> a.openBook(book));
    }

    @Override
    public void openBook(@NotNull final Book book)
    {
        audiences.forEach(a -> a.openBook(book));
    }
}

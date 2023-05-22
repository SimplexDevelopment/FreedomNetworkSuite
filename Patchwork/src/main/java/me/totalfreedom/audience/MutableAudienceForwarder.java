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

    public static MutableAudienceForwarder from(Audience... audiences)
    {
        MutableAudienceForwarder audienceForwarder = new MutableAudienceForwarder();

        for (Audience audience : audiences)
        {
            audienceForwarder.addAudience(audience);
        }

        return audienceForwarder;
    }

    public void addAudience(Audience audience)
    {
        if (audiences.contains(audience) || audience == this /* Protect against honest self-referential calls */)
        {
            return;
        }

        audiences.add(audience);
    }

    public boolean removeAudience(Audience audience)
    {
        return audiences.remove(audience);
    }


    @Override
    public @NotNull Audience filterAudience(@NotNull Predicate<? super Audience> filter)
    {
        return audiences.stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void forEachAudience(@NotNull Consumer<? super Audience> action)
    {
        audiences.forEach(action);
    }

    @Override
    public void sendMessage(@NotNull ComponentLike message)
    {
        audiences.forEach(a -> a.sendMessage(message));
    }

    @Override
    public void sendMessage(@NotNull Component message)
    {
        audiences.forEach(a -> a.sendMessage(message));
    }

    @Override
    public void sendMessage(@NotNull Component message, ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(message, boundChatType));
    }

    @Override
    public void sendMessage(@NotNull ComponentLike message, ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(message, boundChatType));
    }

    @Override
    public void sendMessage(@NotNull SignedMessage signedMessage, ChatType.@NotNull Bound boundChatType)
    {
        audiences.forEach(a -> a.sendMessage(signedMessage, boundChatType));
    }

    @Override
    public void deleteMessage(@NotNull SignedMessage signedMessage)
    {
        audiences.forEach(a -> a.deleteMessage(signedMessage));
    }

    @Override
    public void deleteMessage(SignedMessage.@NotNull Signature signature)
    {
        audiences.forEach(a -> a.deleteMessage(signature));
    }

    // The methods below here will (probably) never be used, however it's good to keep them for completeness' sake.

    @Override
    public void sendActionBar(@NotNull ComponentLike message)
    {
        audiences.forEach(a -> a.sendActionBar(message));
    }

    @Override
    public void sendActionBar(@NotNull Component message)
    {
        audiences.forEach(a -> a.sendActionBar(message));
    }

    @Override
    public void sendPlayerListHeader(@NotNull ComponentLike header)
    {
        audiences.forEach(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListHeader(@NotNull Component header)
    {
        audiences.forEach(a -> a.sendPlayerListHeader(header));
    }

    @Override
    public void sendPlayerListFooter(@NotNull ComponentLike footer)
    {
        audiences.forEach(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListFooter(@NotNull Component footer)
    {
        audiences.forEach(a -> a.sendPlayerListFooter(footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer)
    {
        audiences.forEach(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer)
    {
        audiences.forEach(a -> a.sendPlayerListHeaderAndFooter(header, footer));
    }

    @Override
    public void showTitle(@NotNull Title title)
    {
        audiences.forEach(a -> a.showTitle(title));
    }

    @Override
    public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value)
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
    public void showBossBar(@NotNull BossBar bar)
    {
        audiences.forEach(a -> a.showBossBar(bar));
    }

    @Override
    public void hideBossBar(@NotNull BossBar bar)
    {
        audiences.forEach(a -> a.hideBossBar(bar));
    }

    @Override
    public void playSound(@NotNull Sound sound)
    {
        audiences.forEach(a -> a.playSound(sound));
    }

    @Override
    public void playSound(@NotNull Sound sound, double x, double y, double z)
    {

        audiences.forEach(a -> a.playSound(sound, x, y, z));
    }

    @Override
    public void playSound(@NotNull Sound sound, Sound.@NotNull Emitter emitter)
    {
        audiences.forEach(a -> a.playSound(sound, emitter));
    }

    @Override
    public void stopSound(@NotNull Sound sound)
    {
        audiences.forEach(a -> a.stopSound(sound));
    }

    @Override
    public void stopSound(@NotNull SoundStop stop)
    {
        audiences.forEach(a -> a.stopSound(stop));
    }

    @Override
    public void openBook(Book.@NotNull Builder book)
    {
        audiences.forEach(a -> a.openBook(book));
    }

    @Override
    public void openBook(@NotNull Book book)
    {
        audiences.forEach(a -> a.openBook(book));
    }
}

package me.totalfreedom.display.adminchat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public final class AdminChatFormat
{
    public static final AdminChatFormat DEFAULT = ACFormatBuilder.format()
                                                                 .build();
    private final Component prefix;
    private final Component userName;
    private final Component rank;
    private final Component chatSplitter;
    private final Component fullFormat;


    AdminChatFormat(final ACFormatBuilder builder)
    {
        this.prefix = Component.text(builder.openBracket(), builder.bracketColor())
                               .append(Component.text(builder.prefix(), builder.prefixColor()))
                               .append(Component.text(builder.closeBracket(), builder.bracketColor()));
        this.userName = Component.text("%name%", builder.nameColor());
        this.rank = Component.text(builder.openBracket(), builder.bracketColor())
                             .append(Component.text("%rank%", builder.rankColor()))
                             .append(Component.text(builder.closeBracket(), builder.bracketColor()));

        // Nice formatting :(
        if (builder.chatSplitter()
                   .equals(":"))
        {
            this.chatSplitter = Component.text(":", NamedTextColor.WHITE);
        } else
        {
            this.chatSplitter = Component.space()
                                         .append(Component.text(builder.chatSplitter(), NamedTextColor.WHITE));
        }

        // Formatting because []: is cleaner than [] :, but anything else such as [] >> or [] -> looks better with the space between.
        this.fullFormat = prefix.appendSpace()
                                .append(userName)
                                .appendSpace()
                                .append(rank)
                                .append(chatSplitter)
                                .appendSpace();
    }

    public static AdminChatFormat deserialize(final String serialized)
    {
        final Component dez = LegacyComponentSerializer.legacyAmpersand()
                                                       .deserialize(serialized);
        final Component prefix = dez.children()
                                    .get(0);
        final Component userName = dez.children()
                                      .get(1);
        final Component rank = dez.children()
                                  .get(2);
        final Component chatSplitter = dez.children()
                                          .get(3);

        return ACFormatBuilder.format()
                              .prefix(((TextComponent) prefix).content())
                              .prefixColor(prefix.color())
                              .nameColor(userName.color())
                              .rankColor(rank.color())
                              .chatSplitter(((TextComponent) chatSplitter).content())
                              .build();
    }

    public Component getPrefix()
    {
        return prefix;
    }

    public Component getUserName()
    {
        return userName;
    }

    public Component getRank()
    {
        return rank;
    }

    public Component getFullFormat()
    {
        return fullFormat;
    }

    public Component format(final String name, final String rank)
    {
        return fullFormat.replaceText(b ->
        {
            b.matchLiteral("%name%")
             .replacement(name);
            b.matchLiteral("%rank%")
             .replacement(rank);
        });
    }

    public String serialize()
    {
        return LegacyComponentSerializer.legacyAmpersand()
                                        .serialize(fullFormat);
    }
}

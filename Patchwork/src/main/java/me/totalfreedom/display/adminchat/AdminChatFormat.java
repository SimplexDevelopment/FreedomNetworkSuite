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
        this.chatSplitter = Component.text(builder.chatSplitter(), NamedTextColor.WHITE);

        this.fullFormat = prefix.append(Component.space())
                                .append(userName)
                                .append(Component.space())
                                .append(rank)
                                .append(Component.space())
                                .append(chatSplitter)
                                .append(Component.space());
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

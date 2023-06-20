package me.totalfreedom.display.adminchat;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class ACFormatBuilder
{
    private char openTag = '[';
    private char closeTag = ']';
    private TextColor prefixColor = NamedTextColor.DARK_RED;
    private TextColor bracketColor = NamedTextColor.WHITE;
    private TextColor nameColor = NamedTextColor.AQUA;
    private TextColor rankColor = NamedTextColor.GOLD;
    private String prefix = "Admin";
    private String chatSplitter = ">>";

    private ACFormatBuilder()
    {

    }

    public static ACFormatBuilder format()
    {
        return new ACFormatBuilder();
    }

    public ACFormatBuilder openBracket(final char openTag)
    {
        this.openTag = openTag;
        return this;
    }

    public ACFormatBuilder closeBracket(final char closeTag)
    {
        this.closeTag = closeTag;
        return this;
    }

    public ACFormatBuilder prefixColor(final TextColor prefixColor)
    {
        this.prefixColor = prefixColor;
        return this;
    }

    public ACFormatBuilder bracketColor(final TextColor bracketColor)
    {
        this.bracketColor = bracketColor;
        return this;
    }

    public ACFormatBuilder prefix(final String prefix)
    {
        this.prefix = prefix;
        return this;
    }

    public ACFormatBuilder chatSplitter(final String chatSplitter)
    {
        this.chatSplitter = chatSplitter;
        return this;
    }

    public ACFormatBuilder nameColor(final TextColor nameColor)
    {
        this.nameColor = nameColor;
        return this;
    }

    public ACFormatBuilder rankColor(final TextColor rankColor)
    {
        this.rankColor = rankColor;
        return this;
    }

    String openBracket()
    {
        return String.valueOf(openTag);
    }

    String closeBracket()
    {
        return String.valueOf(closeTag);
    }

    TextColor prefixColor()
    {
        return prefixColor;
    }

    TextColor bracketColor()
    {
        return bracketColor;
    }

    TextColor nameColor()
    {
        return nameColor;
    }

    TextColor rankColor()
    {
        return rankColor;
    }

    String prefix()
    {
        return prefix;
    }

    String chatSplitter()
    {
        return chatSplitter;
    }

    public AdminChatFormat build()
    {
        return new AdminChatFormat(this);
    }
}

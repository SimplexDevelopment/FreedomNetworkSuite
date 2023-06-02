/*
 * Copyright (c) 2023 TotalFreedom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.totalfreedom.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

/**
 * This class contains a wrapper for a MiniMessage serializer.
 */
public class FreedomMiniMessage
{
    private FreedomMiniMessage()
    {
        throw new UnsupportedOperationException("Instantiation of a static utility class is not supported.");
    }

    private static final MiniMessage unsafe = MiniMessage.miniMessage();

    private static final MiniMessage safe = MiniMessage.builder().tags(TagResolver.resolver(
            StandardTags.color(),
            StandardTags.rainbow(),
            StandardTags.gradient(),
            StandardTags.newline(),
            StandardTags.decorations(TextDecoration.ITALIC),
            StandardTags.decorations(TextDecoration.BOLD),
            StandardTags.decorations(TextDecoration.STRIKETHROUGH),
            StandardTags.decorations(TextDecoration.UNDERLINED)
    )).build();

    /**
     * Deserializes an input string using an instance of MiniMessage that is either safe (resolves only a specific set of tags)
     *  or unsafe (resolves all tags).
     * @param safe          Whether to use a safe instance of MiniMessage
     * @param input         An input string formatted with MiniMessage's input
     * @param placeholders  Custom placeholders to use when processing the input
     * @return              A processed Component
     */
    public static Component deserialize(boolean safe, String input, TagResolver... placeholders)
    {
        return (safe ? FreedomMiniMessage.safe : unsafe).deserialize(input, placeholders);
    }

    /**
     * Serializes an input component using an instance of MiniMessage that is either safe (resolves only a specific set
     *  of tags) or unsafe (resolves all tags).
     * @param safe          Whether to use a safe instance of MiniMessage
     * @param input         An already processed component
     * @return              A processed Component
     */
    public static String serialize(boolean safe, Component input)
    {
        return (safe ? FreedomMiniMessage.safe : unsafe).serialize(input);
    }
}

/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.veritas.messaging;

import discord4j.core.spec.EmbedCreateSpec;
import java.util.ArrayList;
import java.util.List;

public class EmbedWrapper
{
    private final List<EmbedCreateSpec> embeds = new ArrayList<>();

    public List<EmbedCreateSpec> getEmbeds()
    {
        return embeds;
    }

    public void addEmbed(final EmbedCreateSpec embed)
    {
        this.embeds.add(embed);
    }

    public EmbedCreateSpec.Builder create()
    {
        return EmbedCreateSpec.builder();
    }

    public void quickEmbed(final String title,
                           final String description,
                           final List<Embed> content)
    {
        final EmbedCreateSpec.Builder builder = create()
            .title(title)
            .description(description);

        content.forEach(t -> builder.addField(t.fieldName(),
                                              t.value(),
                                              t.inline()));

        addEmbed(builder.build());
    }
}

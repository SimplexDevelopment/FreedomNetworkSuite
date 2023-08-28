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

import discord4j.core.object.component.LayoutComponent;
import discord4j.core.spec.MessageCreateFields;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.AllowedMentions;

public class SimpleMessageWrapper
{
    private final MessageCreateSpec.Builder spec;

    public SimpleMessageWrapper()
    {
        this.spec = MessageCreateSpec.builder();
    }

    public void setContent(final String content)
    {
        this.spec.content(content);
    }

    public void setEmbeds(final EmbedWrapper embed)
    {
        this.spec.addAllEmbeds(embed.getEmbeds());
    }

    public void setAttachments(final MessageCreateFields.File... files)
    {
        this.spec.addFiles(files);
    }

    public void setSpoilerAttachments(final MessageCreateFields.FileSpoiler... files)
    {
        this.spec.addFileSpoilers(files);
    }

    public void setAllowedMentions(final AllowedMentions allowedMentions)
    {
        this.spec.allowedMentions(allowedMentions);
    }

    public void setLayoutComponents(final LayoutComponent... components)
    {
        for (final LayoutComponent component : components)
        {
            this.spec.addComponent(component);
        }
    }
}

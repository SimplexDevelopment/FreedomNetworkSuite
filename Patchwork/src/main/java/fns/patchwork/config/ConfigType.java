/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
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

package fns.patchwork.config;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.json.FancyJsonWriter;
import com.electronwill.nightconfig.json.JsonFormat;
import com.electronwill.nightconfig.json.JsonParser;
import com.electronwill.nightconfig.json.MinimalJsonWriter;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.TomlWriter;

public enum ConfigType
{
    TOML(TomlFormat.instance(),
         ".toml",
         new TomlWriter(),
         new TomlParser()),
    JSON(JsonFormat.minimalInstance(),
         ".json",
         new MinimalJsonWriter(),
         new JsonParser()),
    JSON_FANCY(JsonFormat.fancyInstance(),
               ".json",
               new FancyJsonWriter(),
               new JsonParser());

    private final ConfigFormat<?> format;
    private final String fileExtension;
    private final ConfigWriter writer;
    private final ConfigParser<?> parser;

    ConfigType(final ConfigFormat<?> format,
               final String fileExtension,
               final ConfigWriter writer,
               final ConfigParser<?> parser)
    {
        this.format = format;
        this.fileExtension = fileExtension;
        this.writer = writer;
        this.parser = parser;
    }

    public ConfigFormat<?> getFormat()
    {
        return this.format;
    }

    public String getExtension()
    {
        return this.fileExtension;
    }

    public ConfigWriter getWriter()
    {
        return this.writer;
    }

    public ConfigParser<?> getParser()
    {
        return this.parser;
    }
}

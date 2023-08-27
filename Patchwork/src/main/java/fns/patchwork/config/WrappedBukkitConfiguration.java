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

import fns.patchwork.provider.ContextProvider;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class WrappedBukkitConfiguration implements Configuration
{
    private final ContextProvider contextProvider = new ContextProvider();
    private final FileConfiguration fileConfiguration;
    private final File file;

    public WrappedBukkitConfiguration(Function<File, FileConfiguration> configurationCreator, File file)
    {
        this.fileConfiguration = configurationCreator.apply(file);
        this.file = file;
    }

    public WrappedBukkitConfiguration(FileConfiguration fileConfiguration, File file)
    {
        this.fileConfiguration = fileConfiguration;
        this.file = file;
    }

    @Override
    public void save() throws IOException
    {
        this.fileConfiguration.save(this.file);
    }

    @Override
    public void load() throws IOException
    {
        try
        {
            this.fileConfiguration.load(this.file);
        } catch (InvalidConfigurationException e)
        {
            throw new IOException("Invalid configuration file on disk", e);
        }
    }

    @Override
    public String getFileName()
    {
        return this.file.getName();
    }

    @Override
    public File getConfigurationFile()
    {
        return this.file;
    }

    @Override
    public String getString(String path)
    {
        return this.fileConfiguration.getString(path);
    }

    @Override
    public boolean getBoolean(String path)
    {
        return this.fileConfiguration.getBoolean(path);
    }

    @Override
    public <T> List<T> getList(String path, Class<T> clazz)
    {
        return this.contextProvider.getList(this.getStringList(path), clazz);
    }

    @Override
    public List<String> getStringList(String path)
    {
        return this.fileConfiguration.getStringList(path);
    }

    @Override
    public int getInt(String path)
    {
        return this.fileConfiguration.getInt(path);
    }

    @Override
    public long getLong(String path)
    {
        return this.fileConfiguration.getLong(path);
    }

    @Override
    public double getDouble(String path)
    {
        return this.fileConfiguration.getDouble(path);
    }

    @Override
    public <T> void set(String path, T value)
    {
        this.fileConfiguration.set(path, value);
    }

    @Override
    public <T> Optional<T> get(String path, Class<T> clazz)
    {
        return Optional.ofNullable(this.contextProvider.fromString(path, clazz));
    }

    @Override
    public <T> T getOrDefault(String path, Class<T> clazz, T fallback)
    {
        return this.get(path, clazz).orElse(fallback);
    }
}

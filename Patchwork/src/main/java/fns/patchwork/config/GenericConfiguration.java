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
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import fns.patchwork.utils.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public final class GenericConfiguration implements Configuration
{
    private final File configFile;
    private final String fileName;
    private final Config config;
    private final ConfigType configType;

    public GenericConfiguration(@NotNull final ConfigType configType,
                                   @Nullable final JavaPlugin plugin,
                                   @NotNull final File dataFolder,
                                   @NotNull final String fileName,
                                   final boolean isConcurrent) throws IOException
    {
        if (!fileName.endsWith(configType.getExtension()))
            throw new IllegalArgumentException("File name must end with " + configType.getExtension() + "!");

        // Ternary just to piss off Allink :)
        final Optional<File> file = (plugin != null) ?
            FileUtils.getOrCreateFileWithResource(dataFolder, fileName, plugin) :
            FileUtils.getOrCreateFile(dataFolder, fileName);

        if (file.isEmpty())
            throw new FileNotFoundException();

        this.configFile = file.get();
        this.fileName = fileName;
        this.configType = configType;

        final ConfigFormat<?> format = configType.getFormat();

        // Another ternary just to piss off Allink :)
        this.config = isConcurrent ? format.createConcurrentConfig() : format.createConfig();

        this.load();
    }

    public GenericConfiguration(final ConfigType type, final File dataFolder, final String fileName)
        throws IOException
    {
        this(type, null, dataFolder, fileName, false);
    }

    public GenericConfiguration(final ConfigType type, final JavaPlugin plugin, final String fileName)
        throws IOException
    {
        this(type, plugin, plugin.getDataFolder(), fileName, false);
    }

    public GenericConfiguration(final ConfigType type, final File dataFolder, final String fileName,
                                   final boolean isConcurrent)
        throws IOException
    {
        this(type, null, dataFolder, fileName, isConcurrent);
    }

    @Override
    public void save() throws IOException
    {
        final File backup = new File(this.configFile.getParentFile(), this.fileName + ".bak");

        if (backup.exists())
            Files.delete(backup.toPath());

        Files.copy(this.configFile.toPath(), backup.toPath());

        try (final FileWriter writer = new FileWriter(this.configFile))
        {
            this.configType.getWriter().write(this.getConfig(), writer);
        }
    }

    @Override
    public void load() throws IOException {
        try (final FileReader reader = new FileReader(this.configFile)) {
            this.config.clear();

            final UnmodifiableConfig parsed = this.configType.getParser().parse(reader).unmodifiable();
            this.config.putAll(parsed);
        }
    }

    @Override
    public String getFileName()
    {
        return fileName;
    }

    @Override
    public File getConfigurationFile()
    {
        return configFile;
    }

    @Override
    public String getString(final String path)
    {
        if (!(this.getConfig().get(path) instanceof String))
            throw new IllegalArgumentException(String.format("Value at path %s is not a string!", path));

        return this.getConfig().get(path);
    }

    @Override
    public boolean getBoolean(String path)
    {
        if (!(this.getConfig().get(path) instanceof Boolean))
            throw new IllegalArgumentException(String.format("Value at path %s is not a boolean!", path));

        return this.getConfig().get(path);
    }

    @Override
    @ApiStatus.Internal
    public @Unmodifiable <T> List<T> getList(String path, Class<T> clazz)
    {
        // TODO: Figure out how to parse lists with Night Config.

        return new ArrayList<>();
    }

    @Override
    @ApiStatus.Internal
    public @Unmodifiable List<String> getStringList(String path)
    {
        // TODO: Figure out how to parse lists with Night Config.

        return new ArrayList<>();
    }

    @Override
    public int getInt(String path)
    {
        return this.getConfig().getInt(path);
    }

    @Override
    public long getLong(String path)
    {
        return this.getConfig().getLong(path);
    }

    @Override
    public double getDouble(String path)
    {
        if (!(this.getConfig().get(path) instanceof Double))
            throw new IllegalArgumentException(String.format("Value at path %s is not a double!", path));

        return this.getConfig().get(path);
    }

    @Override
    public <T> Optional<T> get(String path, Class<T> clazz)
    {
        // I love ternary statements, sorry Allink :)
        return clazz.isInstance(this.getConfig().get(path)) ?
            Optional.of(clazz.cast(this.getConfig().get(path))) :
            Optional.empty();
    }

    @Override
    public <T> T getOrDefault(String path, Class<T> clazz, T fallback)
    {
        return this.get(path, clazz).orElse(fallback);
    }

    @Override
    public <T> void set(final String path, final T value) {
        this.config.set(path, value);
    }

    private UnmodifiableConfig getConfig()
    {
        return config.unmodifiable();
    }

    public ConfigType getConfigType()
    {
        return configType;
    }
}

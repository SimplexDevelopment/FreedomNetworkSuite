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

package fns.patchwork.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Unmodifiable;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

// TODO: Finish implementation
public class WrappedTomlConfiguration implements Configuration
{
    private final Map<String, Object> previousValues = new HashMap<>();
    private final TomlParseResult toml;
    private final File file;


    public WrappedTomlConfiguration(final JavaPlugin plugin, final File file) throws IOException
    {
        if (!file.exists() && file.createNewFile())
        {
            plugin.saveResource(file.getName(), true);
        }

        this.toml = Toml.parse(Path.of(file.toURI()));
        this.file = file;
    }

    @Override
    public void save() throws IOException
    {
        // Create a backup file
        final File backup = new File(this.file.getParentFile(), this.file.getName() + ".bak");
        if (backup.exists() && !Files.deleteIfExists(Path.of(backup.toURI())))
        {
            throw new IOException("Failed to delete existing backup file: " + backup.getName());
        }

        // Serialize the current configuration to a temporary file
        final File tempFile = new File(this.file.getParentFile(), this.file.getName() + ".temp");
        try (FileWriter tempFileWriter = new FileWriter(tempFile))
        {
            // Convert the updated TomlTable to TOML format and write it to the temporary file
            String tomlString = this.toml.toToml();
            tempFileWriter.write(tomlString);
        }

        // Compare the new configuration with the previous one
        TomlParseResult newToml = Toml.parse(Path.of(tempFile.toURI()));
        for (Map.Entry<String, Object> entry : newToml.toMap().entrySet())
        {
            String key = entry.getKey();
            Object newValue = entry.getValue();
            Object oldValue = previousValues.get(key);

            if (oldValue == null || !oldValue.equals(newValue))
            {
                // Value has changed, update it
                this.toml.toMap().replace(key, newValue);
                previousValues.put(key, newValue);
            }
        }

        // Save the updated configuration to the original file
        try (FileWriter fileWriter = new FileWriter(this.file))
        {
            // Convert the updated TomlTable to TOML format and write it to the original file
            String tomlString = this.toml.toToml();
            fileWriter.write(tomlString);
        }

        // Delete the temporary file and the backup file
        Files.delete(Path.of(tempFile.toURI()));
        Files.delete(Path.of(backup.toURI()));
    }

    @Override
    public void load() throws IOException
    {
        // TODO: Implement
    }

    @Override
    public String getFileName()
    {
        return null;
    }

    @Override
    public File getConfigurationFile()
    {
        return null;
    }

    @Override
    public String getString(String path)
    {
        return null;
    }

    @Override
    public boolean getBoolean(String path)
    {
        return false;
    }

    @Override
    public @Unmodifiable <T> List<T> getList(String path, Class<T> clazz)
    {
        return null;
    }

    @Override
    public @Unmodifiable List<String> getStringList(String path)
    {
        return null;
    }

    @Override
    public int getInt(String path)
    {
        return 0;
    }

    @Override
    public long getLong(String path)
    {
        return 0;
    }

    @Override
    public double getDouble(String path)
    {
        return 0;
    }

    @Override
    public <T> void set(String path, T value)
    {
        // TODO: Implement
    }

    @Override
    public <T> Optional<T> get(String path, Class<T> clazz)
    {
        return Optional.empty();
    }

    @Override
    public <T> T getOrDefault(String path, Class<T> clazz, T fallback)
    {
        return null;
    }
}

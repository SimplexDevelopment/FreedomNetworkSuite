package me.totalfreedom.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class WrappedBukkitConfiguration implements Configuration
{
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
        // TODO: Implement when the ContextProvider system is fully fleshed out and flexible enough to deal with configuration types
        return Collections.emptyList();
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
        // TODO: Implement when the ContextProvider system is fully fleshed out and flexible enough to deal with configuration types
        return Optional.empty();
    }

    @Override
    public <T> T getOrDefault(String path, Class<T> clazz, T fallback)
    {
        return this.get(path, clazz).orElse(fallback);
    }
}

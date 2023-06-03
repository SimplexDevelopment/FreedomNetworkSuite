package me.totalfreedom.datura.data;

import me.totalfreedom.api.data.DataSettings;

import java.nio.file.Path;

public class SimpleDataSettings implements DataSettings
{
    private final Path dataFolder;

    public SimpleDataSettings(final Path dataFolder)
    {
        this.dataFolder = dataFolder;
    }

    public Path getDataFolder()
    {
        return this.dataFolder;
    }
}

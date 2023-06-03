package me.totalfreedom.api.backup;

import me.totalfreedom.api.data.DataSettings;

import java.nio.file.Path;
import java.util.function.Function;

public interface BackupSettings
{
    Function<Object, String> getSerializationFunction();

    Function<String, Object> getDeserializationFunction();

    default Path getBackupPath()
    {
        return getDataSettings().getDataFolder().resolve("backups");
    }

    DataSettings getDataSettings();

    String getFileExtension();
}

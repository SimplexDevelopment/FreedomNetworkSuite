package me.totalfreedom.api.backup;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface Backupper<T>
{
    BackupSettings getBackupSettings();

    Optional<T> loadFromBackup(final Path path) throws IOException;

    Path saveToBackup(final T instance) throws IOException;

    String getFileName(final T instance);
}

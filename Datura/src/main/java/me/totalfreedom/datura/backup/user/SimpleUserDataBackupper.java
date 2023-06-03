package me.totalfreedom.datura.backup.user;

import me.totalfreedom.api.backup.BackupSettings;
import me.totalfreedom.api.backup.Backupper;
import me.totalfreedom.datura.user.SimpleUserData;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class SimpleUserDataBackupper implements Backupper<SimpleUserData>
{
    private final BackupSettings backupSettings;

    public SimpleUserDataBackupper(final BackupSettings backupSettings)
    {
        this.backupSettings = backupSettings;
    }

    @Override
    public BackupSettings getBackupSettings()
    {
        return this.backupSettings;
    }

    @Override
    public Optional<SimpleUserData> loadFromBackup(final Path path) throws IOException
    {
        this.createBackupPathIfNotExists();

        if (!Files.exists(path))
        {
            return Optional.empty();
        }

        final byte[] bytes;

        try (final InputStream inputStream = Files.newInputStream(path))
        {
            bytes = inputStream.readAllBytes();
        }

        final String string = new String(bytes, StandardCharsets.UTF_8);
        return Optional.of((SimpleUserData) this.backupSettings.getDeserializationFunction().apply(string));
    }

    private void createBackupPathIfNotExists() throws IOException
    {
        final Path backupPath = this.backupSettings.getBackupPath();

        if (!Files.exists(backupPath))
        {
            Files.createDirectories(backupPath);
        }
    }

    @Override
    public Path saveToBackup(final SimpleUserData instance) throws IOException
    {
        this.createBackupPathIfNotExists();

        final String serialized = this.backupSettings.getSerializationFunction().apply(instance);
        final byte[] serializedBytes = serialized.getBytes(StandardCharsets.UTF_8);
        final String fileName = this.getFileName(instance) + this.backupSettings.getFileExtension();
        final Path path = this.backupSettings.getBackupPath().resolve(fileName);
        final OutputStream outputStream = Files.newOutputStream(path);

        try (final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream))
        {
            bufferedOutputStream.write(serializedBytes);
            bufferedOutputStream.flush();
        }

        return path;
    }

    @Override
    public String getFileName(final SimpleUserData instance)
    {
        return instance.getUniqueId().toString();
    }
}

package me.totalfreedom.datura.test;

import me.totalfreedom.api.backup.Backupper;
import me.totalfreedom.datura.backup.YAMLBackupSettings;
import me.totalfreedom.datura.backup.user.SimpleUserDataBackupper;
import me.totalfreedom.datura.data.SimpleDataSettings;
import me.totalfreedom.datura.user.SimpleUserData;
import me.totalfreedom.security.perm.Group;
import me.totalfreedom.user.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackupperTests
{
    String generateUsername(final SecureRandom random)
    {
        final char[] charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_".toCharArray();
        final int chars = charset.length;
        final int length = random.nextInt(3, 16);
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            final int index = random.nextInt(chars);
            final char character = charset[index];

            builder.append(character);
        }

        return builder.toString();
    }

    SimpleUserData getRandomUserData(final Backupper<SimpleUserData> backupper, final SecureRandom random) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        final Class<SimpleUserData> userDataClass = SimpleUserData.class;
        final Constructor<SimpleUserData> theConstructorIWant = userDataClass.getDeclaredConstructor(UUID.class, String.class, User.class, Group.class, long.class, boolean.class, boolean.class, boolean.class, long.class, boolean.class, Backupper.class);

        theConstructorIWant.setAccessible(true);

        final UUID uuid = UUID.randomUUID();
        final String username = this.generateUsername(random);
        final long playtime = random.nextLong();
        final boolean frozen = random.nextBoolean();
        final boolean canInteract = random.nextBoolean();
        final boolean caged = random.nextBoolean();
        final long balance = random.nextLong();
        final boolean transactionsFrozen = random.nextBoolean();

        return theConstructorIWant.newInstance(uuid, username, null, null, playtime, frozen, canInteract, caged, balance, transactionsFrozen, backupper);
    }

    @Test
    void testYamlDeserializationAndSerialization() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        final Path tempFolder = Files.createTempDirectory("fns_datura_test");
        final SimpleDataSettings simpleDataSettings = new SimpleDataSettings(tempFolder);
        final YAMLBackupSettings yamlBackupSettings = new YAMLBackupSettings(simpleDataSettings);
        final SimpleUserDataBackupper backupper = new SimpleUserDataBackupper(yamlBackupSettings);
        final SimpleUserData randomUserData = this.getRandomUserData(backupper, new SecureRandom());
        final Path backupPath = backupper.saveToBackup(randomUserData);
        Logger.getAnonymousLogger().info(() -> backupPath.toAbsolutePath().toString());
        final Optional<SimpleUserData> backedUpDataOptional = backupper.loadFromBackup(backupPath);
        assertTrue(backedUpDataOptional.isPresent());
        final SimpleUserData backedUpData = backedUpDataOptional.get();
        assertEquals(randomUserData, backedUpData);
    }
}

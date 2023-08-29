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

package fns.patchwork.utils;

import fns.patchwork.utils.logging.FNS4J;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public final class FileUtils
{
    @NonNls
    private static final String CREATED_DIRECTORY = "Created directory ";

    private FileUtils()
    {
        throw new AssertionError();
    }

    public static String getExtension(@NotNull final File file)
    {
        return file.getName()
                   .substring(file.getName()
                                  .lastIndexOf('.'));
    }

    public static Optional<File> getOrCreateDirectory(final File parentDirectory, final String directoryName)
    {
        if (parentDirectory.mkdirs())
            FNS4J.PATCHWORK.info(CREATED_DIRECTORY + parentDirectory.getAbsolutePath());

        final File directory = new File(parentDirectory, directoryName);
        if (directory.mkdirs())
            FNS4J.PATCHWORK.info(CREATED_DIRECTORY + directory.getAbsolutePath());

        if (directory.exists())
            return Optional.of(directory);

        return Optional.empty();
    }

    public static Optional<File> getOrCreateDirectory(final Path directoryPath)
    {
        Optional<File> directory = Optional.empty();

        if (directoryPath.toFile().mkdirs())
            directory = Optional.of(directoryPath.toFile());

        if (directory.isPresent())
            FNS4J.PATCHWORK.info(CREATED_DIRECTORY + directoryPath.toAbsolutePath());

        return directory;
    }

    public static Optional<File> getOrCreateFile(final File parentDirectory, final String fileName)
    {
        if (parentDirectory.mkdirs())
            FNS4J.PATCHWORK.info(CREATED_DIRECTORY + parentDirectory.getAbsolutePath());

        final File file = new File(parentDirectory, fileName);
        try
        {
            if (file.createNewFile())
                FNS4J.PATCHWORK.info("Created file " + file.getAbsolutePath());

            return Optional.of(file);
        }
        catch (final IOException ex)
        {
            FNS4J.PATCHWORK.error("Failed to create file " + fileName + ": " + ex.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<File> getOrCreateFileWithResource(final File parentDirectory,
                                                final String fileName,
                                                final JavaPlugin plugin)
    {
        if (parentDirectory.mkdirs())
            FNS4J.PATCHWORK.info(CREATED_DIRECTORY + parentDirectory.getAbsolutePath());

        final File file = new File(parentDirectory, fileName);
        try
        {
            if (file.createNewFile())
            {
                FNS4J.PATCHWORK.info("Created file " + file.getAbsolutePath());
                FNS4J.PATCHWORK.info(
                    "Copying default file from resources/" + fileName + " to " + file.getAbsolutePath());

                plugin.saveResource(fileName, true);
                FNS4J.PATCHWORK.info(
                    "Successfully copied default file from resources/" + fileName + " to " + file.getAbsolutePath());
            }

            return Optional.of(file);
        }
        catch (final IOException ex)
        {
            FNS4J.PATCHWORK.error("Failed to create file " + fileName + ": " + ex.getMessage());
            return Optional.empty();
        }
    }
}
